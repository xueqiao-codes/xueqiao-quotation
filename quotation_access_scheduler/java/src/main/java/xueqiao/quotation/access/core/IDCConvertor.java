package xueqiao.quotation.access.core;

import com.openshift.internal.restclient.model.DeploymentConfig;
import com.openshift.restclient.IClient;

public interface IDCConvertor {
    DeploymentConfig convert(IConfProvider conf, IClient client) throws Exception;
}
