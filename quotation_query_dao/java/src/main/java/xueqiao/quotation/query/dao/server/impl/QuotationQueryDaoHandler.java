package xueqiao.quotation.query.dao.server.impl;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import org.soldier.watcher.file.FileWatcherModule;
import org.soldier.watcher.file.IFileWatcherListener;

import com.antiy.error_code.ErrorCodeInner;
import com.google.gson.Gson;

import xueqiao.quotation.query.dao.server.QuotationQueryDaoAdaptor;
import xueqiao.quotation.query.dao.server.impl.core.HBaseQuotationSearcher;
import xueqiao.quotation.query.dao.server.impl.core.IQuotationSearcher;
import xueqiao.quotation.query.dao.server.impl.core.UniqueContractIdQuery;
import xueqiao.quotation.race.common.RaceStoreConfig;
import xueqiao.quotation.KLineQuotationMinuteItem;
import xueqiao.quotation.QuotationItem;
import xueqiao.quotation.query.dao.ContractBasicInfo;
import xueqiao.quotation.query.dao.QueryKLineMinuteOption;
import xueqiao.quotation.query.dao.QueryTickOption;
import xueqiao.quotation.query.dao.QuotationQueryDaoErrorCode;
import xueqiao.quotation.query.dao.quotation_query_daoConstants;

public class QuotationQueryDaoHandler extends QuotationQueryDaoAdaptor implements IFileWatcherListener {
	private static File CONFIG_FILE = new File("/data/configs/qconf/xueqiao/quotation/race/store");
	
	private IQuotationSearcher searcher;
	
	private RaceStoreConfig readConfig() {
		Gson gson = new Gson();
		
		int retryTimes = 3;
		while((retryTimes--) > 0) {
			FileReader reader = null;
			try {
				reader = new FileReader(CONFIG_FILE);
				return gson.fromJson(reader, RaceStoreConfig.class);
			} catch (Throwable e) {
				AppLog.e(e.getMessage(), e);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					break;
				}
			} finally {
				IOUtils.closeQuietly(reader);
			}
		}
		return null;
	}
	
	@Override
	public void onHandleFileChanged(File filePath) {
		if (CONFIG_FILE.getAbsolutePath().equals(filePath.getAbsolutePath())) {
			synchronized(this) {
				RaceStoreConfig config = readConfig();
				if (config == null) {
					AppLog.e("read config failed for " + filePath.getAbsolutePath());
					return ;
				}
				
				try {
					searcher.onConfigChanged(config);
				} catch (Exception e) {
					AppLog.e(e.getMessage(), e);
				}
			}
		}
	}
	
	@Override
	public int InitApp(Properties props) {
		RaceStoreConfig config = null;
		synchronized(this) {
			FileWatcherModule.Instance().addWatchFile(CONFIG_FILE, this);
			config = readConfig();
			if (config == null) {
				AppLog.e("read race store config failed!");
				return -1;
			}
			
			if (RaceStoreConfig.HBASE_STORE_TYPE.equalsIgnoreCase(config.getStoreType())) {
				searcher = new HBaseQuotationSearcher();
			} else {
				AppLog.e("no searcher for storeType=" + config.getStoreType() + " found!");
				return -1;
			}
			
			try {
				searcher.init(config);
			} catch (Throwable e) {
				AppLog.e(e.getMessage(), e);
				return -1;
			}
		}
		return 0;
	}

	private void checkContractBasicInfo(ContractBasicInfo contractBasic) throws ErrorInfo {
		if (contractBasic == null) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "contractBasic should not be null");
		}
		
		if (!contractBasic.isSetPlatform() || contractBasic.getPlatform().isEmpty()) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "contractBasic's platform should not be null or empty");
		}
		
		if (!contractBasic.isSetContractSymbols() || contractBasic.getContractSymbols().isEmpty()) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "contractBasic's contractSymbols should not be null or empty");
		}
	}
	
	@Override
	protected List<QuotationItem> getTicks(TServiceCntl oCntl, QueryTickOption option) throws ErrorInfo, TException {
		if (option == null) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "option should not be null");
		}
		checkContractBasicInfo(option.getContractBasic());
		
		if (option.getEndTimestampS() <= option.getStartTimestampS()) {
			return new ArrayList<QuotationItem>();
		}
		
		if (option.getEndTimestampS() - option.getStartTimestampS() > quotation_query_daoConstants.MAX_QUERYTICK_SECONDS) {
			throw new ErrorInfo(QuotationQueryDaoErrorCode.QUERY_LIMITED.getValue(), "query period is too big");
		}
		
		long contractId = 0;
		try {
			contractId = UniqueContractIdQuery.getUniqueContractId(option.getContractBasic().getPlatform()
					, option.getContractBasic().getContractSymbols());
		} catch(Throwable e) {
			AppLog.e(e.getMessage(), e);
			throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode()
					, ErrorCodeInner.SERVER_INNER_ERROR.getErrorMsg());
		}
			
		if (contractId <= 0) {
			throw new ErrorInfo(QuotationQueryDaoErrorCode.CONTRACT_NOT_FOUND.getValue(), "contract is not found for store");
		}
		
		try {
			return searcher.getTicks(contractId, option.getStartTimestampS(), option.getEndTimestampS());
		} catch (Throwable e) {
			AppLog.e(e.getMessage(), e);
			throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode()
					, ErrorCodeInner.SERVER_INNER_ERROR.getErrorMsg());
		}
	}

	@Override
	protected List<KLineQuotationMinuteItem> getKLineMinutes(TServiceCntl oCntl, QueryKLineMinuteOption option)
			throws ErrorInfo, TException {
		if (option == null) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), "option should not be null");
		}
		checkContractBasicInfo(option.getContractBasic());
		
		long minuteStartTimestampS = (option.getStartMinuteTimestampS()/60)*60;
		long minuteEndTimestampS = (option.getEndMinuteTimestampS()/60)*60;
		
		if (minuteEndTimestampS <= minuteStartTimestampS) {
			return new ArrayList<KLineQuotationMinuteItem>();
		}
		
		if (minuteEndTimestampS - minuteStartTimestampS > (quotation_query_daoConstants.MAX_QUERYKLINE_COUNT * 60)) {
			throw new ErrorInfo(QuotationQueryDaoErrorCode.QUERY_LIMITED.getValue(), "query minutes is too big");
		}
		
		long contractId = 0;
		try {
			contractId = UniqueContractIdQuery.getUniqueContractId(option.getContractBasic().getPlatform()
					, option.getContractBasic().getContractSymbols());
		} catch(Throwable e) {
			AppLog.e(e.getMessage(), e);
			throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode()
					, ErrorCodeInner.SERVER_INNER_ERROR.getErrorMsg());
		}
			
		if (contractId <= 0) {
			throw new ErrorInfo(QuotationQueryDaoErrorCode.CONTRACT_NOT_FOUND.getValue(), "contract is not found for store");
		}
		
		try {
			return searcher.getKLineMinutes(contractId, minuteStartTimestampS, minuteEndTimestampS);
		} catch (Throwable e) {
			AppLog.e(e.getMessage(), e);
			throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode()
					, ErrorCodeInner.SERVER_INNER_ERROR.getErrorMsg());
		}
	}

	@Override
	public void destroy() {
	}

}
