namespace * xueqiao.quotation

const string PLATFORM_CTP = "CTP" // CTP正式行情
const string PLATFORM_SIMU_CTP = "SCTP" // CTP模拟行情
const string PLATFORM_SP = "SP"      // SP正式行情
const string PLATFORM_SIMU_SP = "ESP"  // SP模拟行情
const string PLATFORM_ESUNNY = "ES" // 易盛正式行情
const string PLATFROM_SIMY_ESUNNY = "SES" // 易盛模拟行情
const string PLATFORM_XUEQIAO = "XQ"  // 雪橇正式行情
const string PLATFORM_SIMU_XUEQIAO = "SXQ" // 雪橇模拟行情

// 单笔行情
struct QuotationItem {
	// 基础信息
	1:optional string platform;  // 来源平台，CTP，SP，esunny等
	2:optional string contractSymbol; // 合约代码
	3:optional double openPrice;   // 开盘价
	4:optional double highPrice;   // 最高价
	5:optional double lowPrice;    // 最低价
	6:optional double preClosePrice; // 昨日收盘价
	7:optional double preSettlementPrice; // 昨日结算价
	8:optional i64 preOpenInterest;   // 昨日持仓量
	9:optional i64 openInterest;   // 持仓量
	10:optional i64 volumn;        // 成交量
	11:optional double turnover;      // 总成交金额
	12:optional i64 updateTimestampMs; // 平台携带的更新时间，0表示平台未携带更新时间
	13:optional double lastPrice; // 最新成交價
	14:optional i64 lastQty;   // 最新成交量
	15:optional double upperLimitPrice; // 涨停价
	16:optional double lowerLimitPrice; // 跌停价
	17:optional double averagePrice; //成交均价
	
	// 买几和卖几
	21:optional list<double> bidPrice;  //买入价党位
	22:optional list<i64>    bidQty;    //卖入量档位 
	23:optional list<double> askPrice;  //卖出价档位
	24:optional list<i64>    askQty;    //卖出量挡位
	
	100:optional i64    receivedTimestampMs; // 接入层接收到的时间戳
	101:optional string receivedHostName;    // 接入机的hostname
	102:optional i16    receivedProcessId;   // 接入机的进程ID
	
	120:optional i64    raceTimestampMs;   // 竞速的时间戳
	
	//====以下字段废弃,但是IDL中保留,不要重复出现, 对应于老版统一合约系统=======
    //129:optional i64    platformContractId;  // 对应的雪橇合约系统中，存储的雪橇合约系统中的平台合约ID
    //130:optional string sledPlatform;        // 对应的雪橇平台(分为雪橇模拟环境和实盘环境)
    //131:optional i64    sledContractId;      // 对应的雪橇合约ID
    //====废弃结束===========================================================
    
    135:optional string sledExchangeCode;  // 雪橇交易所编码           
    136:optional i16    sledCommodityType;  // 雪橇商品类型
    137:optional string sledCommodityCode;  // 雪橇商品编码
    138:optional string sledContractCode;   // 雪橇合约月份编码
	  
}

// K线详情
struct KLineQuotationDetail {
    1:optional double kLineOpenPrice; //开盘价,K线周期内开始的第一笔行情的成交价格
    2:optional double kLineHighPrice; //最高价,K线周期内价格最高的一笔成交价格
    3:optional double kLineLowPrice;   //最低价,K线周期内价格最低的一笔成交价格
    4:optional double kLineClosePrice; //收盘价,K线周期内最后的一笔行情的成交价格
    5:optional i64    kLineQty;        //成交量,K线周期内开始的持仓量（周期内的第一笔行情数据）
    6:optional double kLineSettlementPrice; //结算价,K线周期内所有成交的加权平均价
    7:optional i64    kLineOpenInterest;  //持仓量,K线周期内开始的持仓量（周期内的第一笔行情数据）
}

// K线分钟行情
struct KLineQuotationMinuteItem {
    1:optional string platform;
    2:optional string contractSymbol; 
    
    3:optional i64 kMinuteStartTimestampS;  // 分钟开始的时间戳
    4:optional i16 kMinutePeriod;          // 分钟周期的间隔
    5:optional KLineQuotationDetail detail; 
}

