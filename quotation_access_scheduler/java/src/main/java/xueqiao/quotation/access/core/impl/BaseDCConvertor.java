package xueqiao.quotation.access.core.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.thrift.protocol.TJSONProtocol;
import org.jboss.dmr.ModelNode;
import org.soldier.platform.svr_platform.util.ProtocolUtil;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.longsheng.xueqiao.broker.thriftapi.BrokerAccessEntry;
import com.openshift.internal.restclient.model.DeploymentConfig;
import com.openshift.internal.util.JBossDmrExtentions;
import com.openshift.restclient.IClient;
import com.openshift.restclient.images.DockerImageURI;
import com.openshift.restclient.model.IContainer;

import net.qihoo.qconf.Qconf;
import net.qihoo.qconf.QconfException;
import xueqiao.quotation.access.core.IConfProvider;
import xueqiao.quotation.access.core.IDCConvertor;
import xueqiao.quotation.account.thriftapi.QuotationAccount;

public abstract class BaseDCConvertor implements IDCConvertor {
    
    protected QuotationAccount mAccount;
    
    protected BaseDCConvertor(QuotationAccount account) {
        this.mAccount = account;
        if (!mAccount.isSetAccountProperties()) {
            mAccount.setAccountProperties(new HashMap<>());
        }
    }
    
    public DeploymentConfig convert(IConfProvider conf, IClient client) throws Exception {
        DeploymentConfig dc = client.getResourceFactory().create("v1", "DeploymentConfig");
        
        String name = getPlatform().toLowerCase() + "-" + getAccount().getAccountId();
        
        dc.setName(name);
        dc.setReplicas(1);
        dc.setReplicaSelector("name", name);
        dc.getTemplateLabels().put("name", name);
        dc.addTrigger("ConfigChange");
        
        JBossDmrExtentions.set(dc.getNode(), dc.getPropertyKeys(), "spec.strategy.type", "Recreate");
        
        JBossDmrExtentions.set(dc.getNode(), dc.getPropertyKeys(), "spec.template.spec.hostIPC", true);
        JBossDmrExtentions.set(dc.getNode(), dc.getPropertyKeys(), "spec.template.spec.nodeSelector"
                , ImmutableMap.of("node-role.xueqiao.quotation/access", "true"
                        , "node-role.xueqiao.quotation/zone" + String.valueOf(getAccount().getDeploySet().getValue()), "true"));
        
        ModelNode tolerationsNode = JBossDmrExtentions.get(dc.getNode(), dc.getPropertyKeys(), "spec.template.spec.tolerations");
        ModelNode tolerationNode = tolerationsNode.add();
        tolerationNode.get("key").set("xueqiao.quotation.access");
        tolerationNode.get("operator").set("Equal");
        tolerationNode.get("value").set("true");
        tolerationNode.get("effect").set("NoExecute");
        
        
        ModelNode volumesNode = 
                JBossDmrExtentions.get(dc.getNode(), dc.getPropertyKeys(), "spec.template.spec.volumes");
        ModelNode qconfScriptsVolumeNode = volumesNode.add();
        qconfScriptsVolumeNode.get("name").set("qconf-scripts");
        JBossDmrExtentions.get(qconfScriptsVolumeNode, dc.getPropertyKeys(), "hostPath.path").set("/usr/local/soldier/qconf_agent/script");
        JBossDmrExtentions.get(qconfScriptsVolumeNode, dc.getPropertyKeys(), "hostPath.type").set("Directory");
        
        ModelNode qconfConfigsVolumeNode = volumesNode.add();
        qconfConfigsVolumeNode.get("name").set("qconf-configs");
        JBossDmrExtentions.get(qconfConfigsVolumeNode, dc.getPropertyKeys(), "hostPath.path").set("/data/configs/qconf");
        JBossDmrExtentions.get(qconfConfigsVolumeNode, dc.getPropertyKeys(), "hostPath.type").set("Directory");
        
        ModelNode qconfDevVolumeNode = volumesNode.add();
        qconfDevVolumeNode.get("name").set("dev");
        JBossDmrExtentions.get(qconfDevVolumeNode, dc.getPropertyKeys(), "hostPath.path").set("/dev");
        JBossDmrExtentions.get(qconfDevVolumeNode, dc.getPropertyKeys(), "hostPath.type").set("Directory");
        
        IContainer container = dc.addContainer(getContainerNodeName());
        container.setImage(new DockerImageURI(conf.getImage(getAccount().getPlatform())));
        container.setLimitsMemory(conf.getLimitsMemory(getAccount().getPlatform()));
        container.setLimitsCPU(conf.getLimitsCpu(getAccount().getPlatform()));
        container.setRequestsCPU(conf.getRequestsCpu(getAccount().getPlatform()));
        container.setRequestsMemory(conf.getRequestsMemory(getAccount().getPlatform()));
        container.addVolumeMount("qconf-scripts").setMountPath("/usr/local/soldier/qconf_agent/script");
        container.addVolumeMount("qconf-configs").setMountPath("/data/configs/qconf");
        container.addVolumeMount("dev").setMountPath("/dev");
        
        container.addEnvVar(getAccount().getPlatform() + "_SCHEDULER", conf.getSchedulerVersion(getAccount().getPlatform()));
        container.addEnvVar("APPLOG_STDOUT_MODE", "true");
        container.addEnvVar("ACCOUNT_ID", String.valueOf(getAccount().getAccountId()));
        container.addEnvVar("PLATFORM", getPlatform());
        container.addEnvVar("USER_NAME", getRealUserName());
        container.addEnvVar("USER_PASSWD", getAccount().getAccountpwd());
        container.addEnvVar("DEPLOY_ZONE", String.valueOf(getAccount().getDeploySet().getValue()));
        
        String restartClocks = getAccount().getAccountProperties().get("RESTART_CLOCKS");
        if (restartClocks == null) {
            container.addEnvVar("RESTART_CLOCKS"
                    , conf.getDefaultRestartClocks(getAccount().getPlatform(), getAccount().getDeploySet()).trim());
        } else {
            if ("empty".equalsIgnoreCase(restartClocks.trim())) {
                container.addEnvVar("RESTART_CLOCKS", "");
            } else {
                container.addEnvVar("RESTART_CLOCKS", restartClocks.trim());
            }
        }
        
        ModelNode containers = JBossDmrExtentions.get(dc.getNode(), dc.getPropertyKeys(), "spec.template.spec.containers");
        
        Optional<ModelNode> containerNode = containers.asList().stream()
                .filter(n -> getContainerNodeName().equals(JBossDmrExtentions.asString(n, dc.getPropertyKeys(), "name"))).findFirst();
        Preconditions.checkState(containerNode.isPresent());
        
        ModelNode hostAddrEnvNode = JBossDmrExtentions.get(containerNode.get(), dc.getPropertyKeys(), "env").add();
        hostAddrEnvNode.get("name").set("HOST_ADDR");
        JBossDmrExtentions.set(hostAddrEnvNode, dc.getPropertyKeys(), "valueFrom.fieldRef.fieldPath", "status.hostIP");
        
        //启动容器赋予特权模式
        JBossDmrExtentions.set(containerNode.get(), dc.getPropertyKeys(), "securityContext.privileged", true);
        
        BrokerAccessEntry broker = getBrokerAccessEntry(getAccount().getBrokerId(), getAccount().getBrokerAccessId());
        if (broker == null) {
            return dc; // 会导致发布信息不全, 通过状态上报引发关注
        }
        
        Map<String, String> extEnvs = new HashMap<>();
        getEnvs(extEnvs, broker);
        
        for (Entry<String, String> e : extEnvs.entrySet()) {
            container.addEnvVar(e.getKey(), e.getValue());
        }
        
        return dc;
    }
    
    public QuotationAccount getAccount() {
        return mAccount;
    }
    
    private BrokerAccessEntry getBrokerAccessEntry(long brokerId, long brokerAccessId)
            throws QconfException, UnsupportedEncodingException {
        StringBuilder pathBuilder = new StringBuilder(64);
        pathBuilder.append("xueqiao/broker/access");
      
        List<String> brokerIDKeys = Qconf.getBatchKeys(pathBuilder.toString());
        if (brokerIDKeys == null || !brokerIDKeys.contains(String.valueOf(brokerId))) {
            return null;
        }
      
        pathBuilder.append("/").append(brokerId);
        List<String> brokerAccessIDKeys = Qconf.getBatchKeys(pathBuilder.toString());
        if (brokerAccessIDKeys == null || !brokerAccessIDKeys.contains(String.valueOf(brokerAccessId))) {
            return null;
        }
      
        pathBuilder.append("/").append(brokerAccessId);
        
        return ProtocolUtil.unSerialize(new TJSONProtocol.Factory()
                , Qconf.getConf(pathBuilder.toString()).getBytes("UTF-8")
                , BrokerAccessEntry.class);
    }
    
    protected String getRealUserName() {
        return mAccount.getAccountName();
    }
     
    protected abstract String getContainerNodeName();
    
    protected abstract String getPlatform();
    
    protected abstract void getEnvs(Map<String, String> envs, BrokerAccessEntry broker);
}
