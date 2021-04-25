package xueqiao.quotation.access;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;

import xueqiao.quotation.access.core.AccountManager;
import xueqiao.quotation.access.core.AccountProcessor;
import xueqiao.quotation.access.core.IConfProvider;
import xueqiao.quotation.access.core.OCManager;
import xueqiao.quotation.access.core.impl.ConfProviderImpl;

public class DaemonMain {
    
    public static void main(String[] args) throws IOException {
        String apiBaseUrl = System.getenv("API_BASEURL");
        if (StringUtils.isEmpty(apiBaseUrl)) {
            AppLog.e("Env API_BASEURL is not set!");
            System.exit(-1);
        }
        String deployNamespace = System.getenv("DEPLOY_NAMESPACE");
        if (StringUtils.isEmpty(deployNamespace)) {
            deployNamespace = "xueqiao-quotation-access";
        }
        String token = FileUtils.readFileToString(new File("/run/secrets/kubernetes.io/serviceaccount/token"));
        
        OCManager ocManager = new OCManager(apiBaseUrl, token, deployNamespace);
        IConfProvider confProvider = new ConfProviderImpl();
        
        new AccountManager(new AccountProcessor(confProvider, ocManager)).checkLoop();
    }
    
}
