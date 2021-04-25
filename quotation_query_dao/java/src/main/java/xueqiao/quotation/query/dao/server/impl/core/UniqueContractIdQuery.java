package xueqiao.quotation.query.dao.server.impl.core;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.commons.lang.math.NumberUtils;
import org.soldier.base.HashAlgorithms;
import org.soldier.base.RunningEnvironment;
import org.soldier.base.logger.AppLog;

import com.google.common.base.Preconditions;

import net.qihoo.qconf.Qconf;

public class UniqueContractIdQuery {
	private static final String CONTRACT_PATH_SUFFIX = "xueqiao/quotation/contract/ids";
	
	private static String getQConfName() {
		if (RunningEnvironment.isInDev()) {
			return "dev_quotation";
		} else if (RunningEnvironment.isInGamma()) {
			return "gamma_quotation";
		} 
		return "idc_quotation"; 
	}
	
	public static long getUniqueContractId(final String platform, final String contractSymbols) throws Exception {
		Preconditions.checkArgument(platform != null);
		Preconditions.checkArgument(contractSymbols != null);
		
		String qconfName = getQConfName();
		
		StringBuilder pathBuilder = new StringBuilder(128);
		pathBuilder.append(CONTRACT_PATH_SUFFIX);
		
		if (AppLog.debugEnabled()) {
			AppLog.d("query keys qconf " + qconfName + ", path=" + pathBuilder.toString());
		}
		ArrayList<String> platformKeys = Qconf.getBatchKeys(pathBuilder.toString(), qconfName);
		if (platformKeys == null || !platformKeys.contains(platform)) {
			return 0;
		}
		
		int sliceNumIndex = HashAlgorithms.JSHash(contractSymbols) % 1024;
		pathBuilder.append("/").append(platform);
		
		if (AppLog.debugEnabled()) {
			AppLog.d("query keys qconf " + qconfName + ", path=" + pathBuilder.toString());
		}
		ArrayList<String> sliceKeys = Qconf.getBatchKeys(pathBuilder.toString(), qconfName);
		if (platformKeys == null || !sliceKeys.contains(String.valueOf(sliceNumIndex))) {
			return 0;
		}
		
		pathBuilder.append("/").append(sliceNumIndex);
		
		if (AppLog.debugEnabled()) {
			AppLog.d("query keys qconf " + qconfName + ", path=" + pathBuilder.toString());
		}
		ArrayList<String> contractKeys = Qconf.getBatchKeys(pathBuilder.toString(), qconfName);
		
		String contractUrlEncodedSymbols = URLEncoder.encode(contractSymbols, "UTF-8");
		if (contractKeys == null || !contractKeys.contains(contractUrlEncodedSymbols)) {
			return 0;
		}
		
		pathBuilder.append("/").append(contractUrlEncodedSymbols);
		return NumberUtils.toLong(Qconf.getConf(pathBuilder.toString(), qconfName));
	}
}
