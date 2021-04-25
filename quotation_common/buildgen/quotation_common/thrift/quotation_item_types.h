/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#ifndef quotation_item_TYPES_H
#define quotation_item_TYPES_H

#include <thrift/Thrift.h>
#include <thrift/TApplicationException.h>
#include <thrift/protocol/TProtocol.h>
#include <thrift/transport/TTransport.h>

#include <thrift/cxxfunctional.h>


namespace xueqiao { namespace quotation {

typedef struct _QuotationItem__isset {
  _QuotationItem__isset() : platform(false), contractSymbol(false), openPrice(false), highPrice(false), lowPrice(false), preClosePrice(false), preSettlementPrice(false), preOpenInterest(false), openInterest(false), volumn(false), turnover(false), updateTimestampMs(false), lastPrice(false), lastQty(false), upperLimitPrice(false), lowerLimitPrice(false), averagePrice(false), bidPrice(false), bidQty(false), askPrice(false), askQty(false), receivedTimestampMs(false), receivedHostName(false), receivedProcessId(false), raceTimestampMs(false), sledExchangeCode(false), sledCommodityType(false), sledCommodityCode(false), sledContractCode(false) {}
  bool platform;
  bool contractSymbol;
  bool openPrice;
  bool highPrice;
  bool lowPrice;
  bool preClosePrice;
  bool preSettlementPrice;
  bool preOpenInterest;
  bool openInterest;
  bool volumn;
  bool turnover;
  bool updateTimestampMs;
  bool lastPrice;
  bool lastQty;
  bool upperLimitPrice;
  bool lowerLimitPrice;
  bool averagePrice;
  bool bidPrice;
  bool bidQty;
  bool askPrice;
  bool askQty;
  bool receivedTimestampMs;
  bool receivedHostName;
  bool receivedProcessId;
  bool raceTimestampMs;
  bool sledExchangeCode;
  bool sledCommodityType;
  bool sledCommodityCode;
  bool sledContractCode;
} _QuotationItem__isset;

class QuotationItem {
 public:

  static const char* ascii_fingerprint; // = "B2373482E6B2469F55C9492DB79030FB";
  static const uint8_t binary_fingerprint[16]; // = {0xB2,0x37,0x34,0x82,0xE6,0xB2,0x46,0x9F,0x55,0xC9,0x49,0x2D,0xB7,0x90,0x30,0xFB};

  QuotationItem() : platform(), contractSymbol(), openPrice(0), highPrice(0), lowPrice(0), preClosePrice(0), preSettlementPrice(0), preOpenInterest(0), openInterest(0), volumn(0), turnover(0), updateTimestampMs(0), lastPrice(0), lastQty(0), upperLimitPrice(0), lowerLimitPrice(0), averagePrice(0), receivedTimestampMs(0), receivedHostName(), receivedProcessId(0), raceTimestampMs(0), sledExchangeCode(), sledCommodityType(0), sledCommodityCode(), sledContractCode() {
  }

  virtual ~QuotationItem() throw() {}

  std::string platform;
  std::string contractSymbol;
  double openPrice;
  double highPrice;
  double lowPrice;
  double preClosePrice;
  double preSettlementPrice;
  int64_t preOpenInterest;
  int64_t openInterest;
  int64_t volumn;
  double turnover;
  int64_t updateTimestampMs;
  double lastPrice;
  int64_t lastQty;
  double upperLimitPrice;
  double lowerLimitPrice;
  double averagePrice;
  std::vector<double>  bidPrice;
  std::vector<int64_t>  bidQty;
  std::vector<double>  askPrice;
  std::vector<int64_t>  askQty;
  int64_t receivedTimestampMs;
  std::string receivedHostName;
  int16_t receivedProcessId;
  int64_t raceTimestampMs;
  std::string sledExchangeCode;
  int16_t sledCommodityType;
  std::string sledCommodityCode;
  std::string sledContractCode;

  _QuotationItem__isset __isset;

  void __set_platform(const std::string& val) {
    platform = val;
    __isset.platform = true;
  }

  void __set_contractSymbol(const std::string& val) {
    contractSymbol = val;
    __isset.contractSymbol = true;
  }

  void __set_openPrice(const double val) {
    openPrice = val;
    __isset.openPrice = true;
  }

  void __set_highPrice(const double val) {
    highPrice = val;
    __isset.highPrice = true;
  }

  void __set_lowPrice(const double val) {
    lowPrice = val;
    __isset.lowPrice = true;
  }

  void __set_preClosePrice(const double val) {
    preClosePrice = val;
    __isset.preClosePrice = true;
  }

  void __set_preSettlementPrice(const double val) {
    preSettlementPrice = val;
    __isset.preSettlementPrice = true;
  }

  void __set_preOpenInterest(const int64_t val) {
    preOpenInterest = val;
    __isset.preOpenInterest = true;
  }

  void __set_openInterest(const int64_t val) {
    openInterest = val;
    __isset.openInterest = true;
  }

  void __set_volumn(const int64_t val) {
    volumn = val;
    __isset.volumn = true;
  }

  void __set_turnover(const double val) {
    turnover = val;
    __isset.turnover = true;
  }

  void __set_updateTimestampMs(const int64_t val) {
    updateTimestampMs = val;
    __isset.updateTimestampMs = true;
  }

  void __set_lastPrice(const double val) {
    lastPrice = val;
    __isset.lastPrice = true;
  }

  void __set_lastQty(const int64_t val) {
    lastQty = val;
    __isset.lastQty = true;
  }

  void __set_upperLimitPrice(const double val) {
    upperLimitPrice = val;
    __isset.upperLimitPrice = true;
  }

  void __set_lowerLimitPrice(const double val) {
    lowerLimitPrice = val;
    __isset.lowerLimitPrice = true;
  }

  void __set_averagePrice(const double val) {
    averagePrice = val;
    __isset.averagePrice = true;
  }

  void __set_bidPrice(const std::vector<double> & val) {
    bidPrice = val;
    __isset.bidPrice = true;
  }

  void __set_bidQty(const std::vector<int64_t> & val) {
    bidQty = val;
    __isset.bidQty = true;
  }

  void __set_askPrice(const std::vector<double> & val) {
    askPrice = val;
    __isset.askPrice = true;
  }

  void __set_askQty(const std::vector<int64_t> & val) {
    askQty = val;
    __isset.askQty = true;
  }

  void __set_receivedTimestampMs(const int64_t val) {
    receivedTimestampMs = val;
    __isset.receivedTimestampMs = true;
  }

  void __set_receivedHostName(const std::string& val) {
    receivedHostName = val;
    __isset.receivedHostName = true;
  }

  void __set_receivedProcessId(const int16_t val) {
    receivedProcessId = val;
    __isset.receivedProcessId = true;
  }

  void __set_raceTimestampMs(const int64_t val) {
    raceTimestampMs = val;
    __isset.raceTimestampMs = true;
  }

  void __set_sledExchangeCode(const std::string& val) {
    sledExchangeCode = val;
    __isset.sledExchangeCode = true;
  }

  void __set_sledCommodityType(const int16_t val) {
    sledCommodityType = val;
    __isset.sledCommodityType = true;
  }

  void __set_sledCommodityCode(const std::string& val) {
    sledCommodityCode = val;
    __isset.sledCommodityCode = true;
  }

  void __set_sledContractCode(const std::string& val) {
    sledContractCode = val;
    __isset.sledContractCode = true;
  }

  bool operator == (const QuotationItem & rhs) const
  {
    if (__isset.platform != rhs.__isset.platform)
      return false;
    else if (__isset.platform && !(platform == rhs.platform))
      return false;
    if (__isset.contractSymbol != rhs.__isset.contractSymbol)
      return false;
    else if (__isset.contractSymbol && !(contractSymbol == rhs.contractSymbol))
      return false;
    if (__isset.openPrice != rhs.__isset.openPrice)
      return false;
    else if (__isset.openPrice && !(openPrice == rhs.openPrice))
      return false;
    if (__isset.highPrice != rhs.__isset.highPrice)
      return false;
    else if (__isset.highPrice && !(highPrice == rhs.highPrice))
      return false;
    if (__isset.lowPrice != rhs.__isset.lowPrice)
      return false;
    else if (__isset.lowPrice && !(lowPrice == rhs.lowPrice))
      return false;
    if (__isset.preClosePrice != rhs.__isset.preClosePrice)
      return false;
    else if (__isset.preClosePrice && !(preClosePrice == rhs.preClosePrice))
      return false;
    if (__isset.preSettlementPrice != rhs.__isset.preSettlementPrice)
      return false;
    else if (__isset.preSettlementPrice && !(preSettlementPrice == rhs.preSettlementPrice))
      return false;
    if (__isset.preOpenInterest != rhs.__isset.preOpenInterest)
      return false;
    else if (__isset.preOpenInterest && !(preOpenInterest == rhs.preOpenInterest))
      return false;
    if (__isset.openInterest != rhs.__isset.openInterest)
      return false;
    else if (__isset.openInterest && !(openInterest == rhs.openInterest))
      return false;
    if (__isset.volumn != rhs.__isset.volumn)
      return false;
    else if (__isset.volumn && !(volumn == rhs.volumn))
      return false;
    if (__isset.turnover != rhs.__isset.turnover)
      return false;
    else if (__isset.turnover && !(turnover == rhs.turnover))
      return false;
    if (__isset.updateTimestampMs != rhs.__isset.updateTimestampMs)
      return false;
    else if (__isset.updateTimestampMs && !(updateTimestampMs == rhs.updateTimestampMs))
      return false;
    if (__isset.lastPrice != rhs.__isset.lastPrice)
      return false;
    else if (__isset.lastPrice && !(lastPrice == rhs.lastPrice))
      return false;
    if (__isset.lastQty != rhs.__isset.lastQty)
      return false;
    else if (__isset.lastQty && !(lastQty == rhs.lastQty))
      return false;
    if (__isset.upperLimitPrice != rhs.__isset.upperLimitPrice)
      return false;
    else if (__isset.upperLimitPrice && !(upperLimitPrice == rhs.upperLimitPrice))
      return false;
    if (__isset.lowerLimitPrice != rhs.__isset.lowerLimitPrice)
      return false;
    else if (__isset.lowerLimitPrice && !(lowerLimitPrice == rhs.lowerLimitPrice))
      return false;
    if (__isset.averagePrice != rhs.__isset.averagePrice)
      return false;
    else if (__isset.averagePrice && !(averagePrice == rhs.averagePrice))
      return false;
    if (__isset.bidPrice != rhs.__isset.bidPrice)
      return false;
    else if (__isset.bidPrice && !(bidPrice == rhs.bidPrice))
      return false;
    if (__isset.bidQty != rhs.__isset.bidQty)
      return false;
    else if (__isset.bidQty && !(bidQty == rhs.bidQty))
      return false;
    if (__isset.askPrice != rhs.__isset.askPrice)
      return false;
    else if (__isset.askPrice && !(askPrice == rhs.askPrice))
      return false;
    if (__isset.askQty != rhs.__isset.askQty)
      return false;
    else if (__isset.askQty && !(askQty == rhs.askQty))
      return false;
    if (__isset.receivedTimestampMs != rhs.__isset.receivedTimestampMs)
      return false;
    else if (__isset.receivedTimestampMs && !(receivedTimestampMs == rhs.receivedTimestampMs))
      return false;
    if (__isset.receivedHostName != rhs.__isset.receivedHostName)
      return false;
    else if (__isset.receivedHostName && !(receivedHostName == rhs.receivedHostName))
      return false;
    if (__isset.receivedProcessId != rhs.__isset.receivedProcessId)
      return false;
    else if (__isset.receivedProcessId && !(receivedProcessId == rhs.receivedProcessId))
      return false;
    if (__isset.raceTimestampMs != rhs.__isset.raceTimestampMs)
      return false;
    else if (__isset.raceTimestampMs && !(raceTimestampMs == rhs.raceTimestampMs))
      return false;
    if (__isset.sledExchangeCode != rhs.__isset.sledExchangeCode)
      return false;
    else if (__isset.sledExchangeCode && !(sledExchangeCode == rhs.sledExchangeCode))
      return false;
    if (__isset.sledCommodityType != rhs.__isset.sledCommodityType)
      return false;
    else if (__isset.sledCommodityType && !(sledCommodityType == rhs.sledCommodityType))
      return false;
    if (__isset.sledCommodityCode != rhs.__isset.sledCommodityCode)
      return false;
    else if (__isset.sledCommodityCode && !(sledCommodityCode == rhs.sledCommodityCode))
      return false;
    if (__isset.sledContractCode != rhs.__isset.sledContractCode)
      return false;
    else if (__isset.sledContractCode && !(sledContractCode == rhs.sledContractCode))
      return false;
    return true;
  }
  bool operator != (const QuotationItem &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const QuotationItem & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

void swap(QuotationItem &a, QuotationItem &b);

typedef struct _KLineQuotationDetail__isset {
  _KLineQuotationDetail__isset() : kLineOpenPrice(false), kLineHighPrice(false), kLineLowPrice(false), kLineClosePrice(false), kLineQty(false), kLineSettlementPrice(false), kLineOpenInterest(false) {}
  bool kLineOpenPrice;
  bool kLineHighPrice;
  bool kLineLowPrice;
  bool kLineClosePrice;
  bool kLineQty;
  bool kLineSettlementPrice;
  bool kLineOpenInterest;
} _KLineQuotationDetail__isset;

class KLineQuotationDetail {
 public:

  static const char* ascii_fingerprint; // = "0BB11F4FF86A93359E7D7EA6F02F6840";
  static const uint8_t binary_fingerprint[16]; // = {0x0B,0xB1,0x1F,0x4F,0xF8,0x6A,0x93,0x35,0x9E,0x7D,0x7E,0xA6,0xF0,0x2F,0x68,0x40};

  KLineQuotationDetail() : kLineOpenPrice(0), kLineHighPrice(0), kLineLowPrice(0), kLineClosePrice(0), kLineQty(0), kLineSettlementPrice(0), kLineOpenInterest(0) {
  }

  virtual ~KLineQuotationDetail() throw() {}

  double kLineOpenPrice;
  double kLineHighPrice;
  double kLineLowPrice;
  double kLineClosePrice;
  int64_t kLineQty;
  double kLineSettlementPrice;
  int64_t kLineOpenInterest;

  _KLineQuotationDetail__isset __isset;

  void __set_kLineOpenPrice(const double val) {
    kLineOpenPrice = val;
    __isset.kLineOpenPrice = true;
  }

  void __set_kLineHighPrice(const double val) {
    kLineHighPrice = val;
    __isset.kLineHighPrice = true;
  }

  void __set_kLineLowPrice(const double val) {
    kLineLowPrice = val;
    __isset.kLineLowPrice = true;
  }

  void __set_kLineClosePrice(const double val) {
    kLineClosePrice = val;
    __isset.kLineClosePrice = true;
  }

  void __set_kLineQty(const int64_t val) {
    kLineQty = val;
    __isset.kLineQty = true;
  }

  void __set_kLineSettlementPrice(const double val) {
    kLineSettlementPrice = val;
    __isset.kLineSettlementPrice = true;
  }

  void __set_kLineOpenInterest(const int64_t val) {
    kLineOpenInterest = val;
    __isset.kLineOpenInterest = true;
  }

  bool operator == (const KLineQuotationDetail & rhs) const
  {
    if (__isset.kLineOpenPrice != rhs.__isset.kLineOpenPrice)
      return false;
    else if (__isset.kLineOpenPrice && !(kLineOpenPrice == rhs.kLineOpenPrice))
      return false;
    if (__isset.kLineHighPrice != rhs.__isset.kLineHighPrice)
      return false;
    else if (__isset.kLineHighPrice && !(kLineHighPrice == rhs.kLineHighPrice))
      return false;
    if (__isset.kLineLowPrice != rhs.__isset.kLineLowPrice)
      return false;
    else if (__isset.kLineLowPrice && !(kLineLowPrice == rhs.kLineLowPrice))
      return false;
    if (__isset.kLineClosePrice != rhs.__isset.kLineClosePrice)
      return false;
    else if (__isset.kLineClosePrice && !(kLineClosePrice == rhs.kLineClosePrice))
      return false;
    if (__isset.kLineQty != rhs.__isset.kLineQty)
      return false;
    else if (__isset.kLineQty && !(kLineQty == rhs.kLineQty))
      return false;
    if (__isset.kLineSettlementPrice != rhs.__isset.kLineSettlementPrice)
      return false;
    else if (__isset.kLineSettlementPrice && !(kLineSettlementPrice == rhs.kLineSettlementPrice))
      return false;
    if (__isset.kLineOpenInterest != rhs.__isset.kLineOpenInterest)
      return false;
    else if (__isset.kLineOpenInterest && !(kLineOpenInterest == rhs.kLineOpenInterest))
      return false;
    return true;
  }
  bool operator != (const KLineQuotationDetail &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const KLineQuotationDetail & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

void swap(KLineQuotationDetail &a, KLineQuotationDetail &b);

typedef struct _KLineQuotationMinuteItem__isset {
  _KLineQuotationMinuteItem__isset() : platform(false), contractSymbol(false), kMinuteStartTimestampS(false), kMinutePeriod(false), detail(false) {}
  bool platform;
  bool contractSymbol;
  bool kMinuteStartTimestampS;
  bool kMinutePeriod;
  bool detail;
} _KLineQuotationMinuteItem__isset;

class KLineQuotationMinuteItem {
 public:

  static const char* ascii_fingerprint; // = "944F7578ED97BB1D83F291B762346E56";
  static const uint8_t binary_fingerprint[16]; // = {0x94,0x4F,0x75,0x78,0xED,0x97,0xBB,0x1D,0x83,0xF2,0x91,0xB7,0x62,0x34,0x6E,0x56};

  KLineQuotationMinuteItem() : platform(), contractSymbol(), kMinuteStartTimestampS(0), kMinutePeriod(0) {
  }

  virtual ~KLineQuotationMinuteItem() throw() {}

  std::string platform;
  std::string contractSymbol;
  int64_t kMinuteStartTimestampS;
  int16_t kMinutePeriod;
  KLineQuotationDetail detail;

  _KLineQuotationMinuteItem__isset __isset;

  void __set_platform(const std::string& val) {
    platform = val;
    __isset.platform = true;
  }

  void __set_contractSymbol(const std::string& val) {
    contractSymbol = val;
    __isset.contractSymbol = true;
  }

  void __set_kMinuteStartTimestampS(const int64_t val) {
    kMinuteStartTimestampS = val;
    __isset.kMinuteStartTimestampS = true;
  }

  void __set_kMinutePeriod(const int16_t val) {
    kMinutePeriod = val;
    __isset.kMinutePeriod = true;
  }

  void __set_detail(const KLineQuotationDetail& val) {
    detail = val;
    __isset.detail = true;
  }

  bool operator == (const KLineQuotationMinuteItem & rhs) const
  {
    if (__isset.platform != rhs.__isset.platform)
      return false;
    else if (__isset.platform && !(platform == rhs.platform))
      return false;
    if (__isset.contractSymbol != rhs.__isset.contractSymbol)
      return false;
    else if (__isset.contractSymbol && !(contractSymbol == rhs.contractSymbol))
      return false;
    if (__isset.kMinuteStartTimestampS != rhs.__isset.kMinuteStartTimestampS)
      return false;
    else if (__isset.kMinuteStartTimestampS && !(kMinuteStartTimestampS == rhs.kMinuteStartTimestampS))
      return false;
    if (__isset.kMinutePeriod != rhs.__isset.kMinutePeriod)
      return false;
    else if (__isset.kMinutePeriod && !(kMinutePeriod == rhs.kMinutePeriod))
      return false;
    if (__isset.detail != rhs.__isset.detail)
      return false;
    else if (__isset.detail && !(detail == rhs.detail))
      return false;
    return true;
  }
  bool operator != (const KLineQuotationMinuteItem &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const KLineQuotationMinuteItem & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

void swap(KLineQuotationMinuteItem &a, KLineQuotationMinuteItem &b);

}} // namespace

#endif
