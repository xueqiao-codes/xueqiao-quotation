package xueqiao.quotation.access.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jboss.dmr.ModelNode;
import org.soldier.base.logger.AppLog;

import com.openshift.internal.restclient.model.DeploymentConfig;
import com.openshift.restclient.model.IEnvironmentVariable;

import xueqiao.quotation.access.core.impl.CTPDCConvertor;
import xueqiao.quotation.access.core.impl.ESunnyDCConvertor;
import xueqiao.quotation.account.thriftapi.QuotationAccount;
import xueqiao.quotation.account.thriftapi.QuotationAccountAccessState;
import xueqiao.quotation.account.thriftapi.QuotationAccountState;
import xueqiao.quotation.account.thriftapi.QuotationTechPlatform;
import xueqiao.quotation.account.thriftapi.client.QuotationAccountDaoStub;

public class AccountProcessor implements AccountManager.IProcessor {
    
    private IConfProvider mProvider;
    private OCManager mOcManager;
    
    public AccountProcessor(IConfProvider provider, OCManager ocManager) {
        this.mProvider = provider;
        this.mOcManager = ocManager;
    }
    
    private IDCConvertor createDCConvertor(QuotationAccount account) {
        if (account.getPlatform() == QuotationTechPlatform.CTP) {
            return new CTPDCConvertor(account);
        } else if (account.getPlatform() == QuotationTechPlatform.ESUNNY) {
            return new ESunnyDCConvertor(account);
        }
        
        return null;
    }
    
    private Map<String, String> getDcEnvironments(DeploymentConfig dc) {
        Map<String, String> resultMap = new HashMap<String, String>();
        for (IEnvironmentVariable env : dc.getEnvironmentVariables()) {
            resultMap.put(env.getName(), env.getValue());
        }
        return resultMap;
    }
    
    private boolean shouldUpdateDc(DeploymentConfig currentDc, DeploymentConfig shouldDc) {
        // 镜像和环境变量
        if (!getDcEnvironments(currentDc).equals(getDcEnvironments(shouldDc))) {
            return true;
        }
        return false;
    }
    
    private void setAcccountAccessState(long quotationAccountId
            , QuotationAccountAccessState state, String invalidMsg) {
        AppLog.i("setAcccountAccessState quotationAccountId=" + quotationAccountId
                    + ", accessState=" + state + ", invalidMsg=" + invalidMsg);
        
        int retryCount = 3;
        while(retryCount-- > 0) {
            try {
                new QuotationAccountDaoStub().updateQuotationAccount(
                        new QuotationAccount().setAccountId(quotationAccountId)
                            .setAccessState(state)
                            .setInvalidReason(invalidMsg));
                break;
            } catch (Throwable e) {
                AppLog.e(e.getMessage(), e);
            }
        }
    }
    
    private long getQuotationAccountId(DeploymentConfig dc){
        for (IEnvironmentVariable env :dc.getEnvironmentVariables()) {
            if ("ACCOUNT_ID".equalsIgnoreCase(env.getName())) {
                return Long.parseLong(env.getValue());
            }
        }
        throw new RuntimeException("No ACCOUNT_ID is found for DeploymentConfig " + dc.getName());
    }
    
    @Override
    public void onCheckAccounts(List<QuotationAccount> accounts) throws Exception {
        Map<String, DeploymentConfig> currentDcMap = mOcManager.getAllDC();
        
        Map<String, DeploymentConfig> shouldDcMap = new HashMap<>();
        for (QuotationAccount account : accounts) {
            IDCConvertor dcConvertor = createDCConvertor(account);
            if (dcConvertor == null) {
                continue;
            }
            
            DeploymentConfig dc = dcConvertor.convert(mProvider, mOcManager.getClient());
            if (dc != null) {
                if (account.getAccountState() == QuotationAccountState.ACCOUNT_DISABLED) {
                    if (!currentDcMap.containsKey(dc.getName())) {
                        // 确保已经移除，把这个状态通知到账号中心
                        setAcccountAccessState(account.getAccountId()
                                , QuotationAccountAccessState.ACCOUNT_NOT_ACCESS, "");
                    }
                    continue;
                }
                
                shouldDcMap.put(dc.getName(), dc);
            }
        }
        
        // 判定要添加的DC
        for (String dcName : shouldDcMap.keySet()) {
            if (!currentDcMap.containsKey(dcName)) {
                DeploymentConfig createdDc = shouldDcMap.get(dcName);
                mOcManager.createDC(createdDc);
                setAcccountAccessState(getQuotationAccountId(createdDc)
                        , QuotationAccountAccessState.ACCOUNT_INVALID, "部署创建中...");
            }
        }
        
        // 首先判定需要删除的DC
        for (String dcName : currentDcMap.keySet()) {
            if (!shouldDcMap.containsKey(dcName)) {
                DeploymentConfig deletedDc = currentDcMap.get(dcName);
                mOcManager.deleteDC(deletedDc);
                setAcccountAccessState(getQuotationAccountId(deletedDc)
                        , QuotationAccountAccessState.ACCOUNT_INVALID, "部署移除中...");
            }
        }
        
        // 最后进行更新检测
        for (String dcName : currentDcMap.keySet()) {
            if (shouldDcMap.containsKey(dcName)) {
                DeploymentConfig currentDc = currentDcMap.get(dcName);
                DeploymentConfig updatedDc = shouldDcMap.get(dcName);
                if (shouldUpdateDc(currentDc, updatedDc)) {
                    ModelNode metaNode = updatedDc.getNode().get("metadata");
                    for (Entry<String, String> metaEntry : currentDc.getMetadata().entrySet()) {
                        metaNode.get(metaEntry.getKey()).set(metaEntry.getValue());
                    }
                    
                    mOcManager.updateDC(updatedDc);
                    setAcccountAccessState(getQuotationAccountId(updatedDc)
                            , QuotationAccountAccessState.ACCOUNT_INVALID, "更新部署中...");
                    
                    // 更新放缓，让k8s有一定的处理时间
                    Thread.sleep(30000);
                }
            }
        }
        
    }
    
}
