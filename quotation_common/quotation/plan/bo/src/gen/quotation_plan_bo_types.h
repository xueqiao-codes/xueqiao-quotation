/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#ifndef quotation_plan_bo_TYPES_H
#define quotation_plan_bo_TYPES_H

#include <thrift/Thrift.h>
#include <thrift/TApplicationException.h>
#include <thrift/protocol/TProtocol.h>
#include <thrift/transport/TTransport.h>

#include <thrift/cxxfunctional.h>
#include "comm_types.h"
#include "page_types.h"
#include "quotation_account_types.h"


namespace xueqiao { namespace quotation { namespace plan { namespace bo {

struct EQuotationPlanBoErrorCode {
  enum type {
    ERROR_GENPREVIEW_ISHANDING = 1001,
    ERROR_PREVIEW_NOTEXIST = 1002
  };
};

extern const std::map<int, const char*> _EQuotationPlanBoErrorCode_VALUES_TO_NAMES;

struct EGenPreviewStatus {
  enum type {
    PREVIEW_EMPTY = 0,
    PREVIEW_GENTASK_RUNNING = 1,
    PREVIEW_FINISHED = 2,
    PREVIEW_GENFAILED = 3
  };
};

extern const std::map<int, const char*> _EGenPreviewStatus_VALUES_TO_NAMES;

typedef struct _SubscribeAccountClass__isset {
  _SubscribeAccountClass__isset() : subscribeNum(false), quotationAccountId(false) {}
  bool subscribeNum;
  bool quotationAccountId;
} _SubscribeAccountClass__isset;

class SubscribeAccountClass {
 public:

  static const char* ascii_fingerprint; // = "3DE42C2949E8B0483B2A45A3FA3DE623";
  static const uint8_t binary_fingerprint[16]; // = {0x3D,0xE4,0x2C,0x29,0x49,0xE8,0xB0,0x48,0x3B,0x2A,0x45,0xA3,0xFA,0x3D,0xE6,0x23};

  SubscribeAccountClass() : subscribeNum(0), quotationAccountId(0) {
  }

  virtual ~SubscribeAccountClass() throw() {}

  int32_t subscribeNum;
  int64_t quotationAccountId;

  _SubscribeAccountClass__isset __isset;

  void __set_subscribeNum(const int32_t val) {
    subscribeNum = val;
    __isset.subscribeNum = true;
  }

  void __set_quotationAccountId(const int64_t val) {
    quotationAccountId = val;
    __isset.quotationAccountId = true;
  }

  bool operator == (const SubscribeAccountClass & rhs) const
  {
    if (__isset.subscribeNum != rhs.__isset.subscribeNum)
      return false;
    else if (__isset.subscribeNum && !(subscribeNum == rhs.subscribeNum))
      return false;
    if (__isset.quotationAccountId != rhs.__isset.quotationAccountId)
      return false;
    else if (__isset.quotationAccountId && !(quotationAccountId == rhs.quotationAccountId))
      return false;
    return true;
  }
  bool operator != (const SubscribeAccountClass &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const SubscribeAccountClass & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

void swap(SubscribeAccountClass &a, SubscribeAccountClass &b);

typedef struct _SubscribeCommodityClass__isset {
  _SubscribeCommodityClass__isset() : classId(false), sledExchangeMic(false), sledCommodityId(false), sledCommodityType(false), sledCommodityCode(false), platformEnv(false), activeMonths(false), inactiveMonths(false), activeSubscribeNum(false), inActiveSubscribeNum(false), subscribeAccounts(false), createTimestampMs(false) {}
  bool classId;
  bool sledExchangeMic;
  bool sledCommodityId;
  bool sledCommodityType;
  bool sledCommodityCode;
  bool platformEnv;
  bool activeMonths;
  bool inactiveMonths;
  bool activeSubscribeNum;
  bool inActiveSubscribeNum;
  bool subscribeAccounts;
  bool createTimestampMs;
} _SubscribeCommodityClass__isset;

class SubscribeCommodityClass {
 public:

  static const char* ascii_fingerprint; // = "D1C90CF7F5B0240FE1BBB9D48989103C";
  static const uint8_t binary_fingerprint[16]; // = {0xD1,0xC9,0x0C,0xF7,0xF5,0xB0,0x24,0x0F,0xE1,0xBB,0xB9,0xD4,0x89,0x89,0x10,0x3C};

  SubscribeCommodityClass() : classId(0), sledExchangeMic(), sledCommodityId(0), sledCommodityType(0), sledCommodityCode(), platformEnv(( ::QuotationPlatformEnv::type)0), activeSubscribeNum(0), inActiveSubscribeNum(0), createTimestampMs(0) {
  }

  virtual ~SubscribeCommodityClass() throw() {}

  int64_t classId;
  std::string sledExchangeMic;
  int64_t sledCommodityId;
  int16_t sledCommodityType;
  std::string sledCommodityCode;
   ::QuotationPlatformEnv::type platformEnv;
  std::vector<int32_t>  activeMonths;
  std::vector<int32_t>  inactiveMonths;
  int32_t activeSubscribeNum;
  int32_t inActiveSubscribeNum;
  std::vector<std::vector<SubscribeAccountClass> >  subscribeAccounts;
  int64_t createTimestampMs;

  _SubscribeCommodityClass__isset __isset;

  void __set_classId(const int64_t val) {
    classId = val;
    __isset.classId = true;
  }

  void __set_sledExchangeMic(const std::string& val) {
    sledExchangeMic = val;
    __isset.sledExchangeMic = true;
  }

  void __set_sledCommodityId(const int64_t val) {
    sledCommodityId = val;
    __isset.sledCommodityId = true;
  }

  void __set_sledCommodityType(const int16_t val) {
    sledCommodityType = val;
    __isset.sledCommodityType = true;
  }

  void __set_sledCommodityCode(const std::string& val) {
    sledCommodityCode = val;
    __isset.sledCommodityCode = true;
  }

  void __set_platformEnv(const  ::QuotationPlatformEnv::type val) {
    platformEnv = val;
    __isset.platformEnv = true;
  }

  void __set_activeMonths(const std::vector<int32_t> & val) {
    activeMonths = val;
    __isset.activeMonths = true;
  }

  void __set_inactiveMonths(const std::vector<int32_t> & val) {
    inactiveMonths = val;
    __isset.inactiveMonths = true;
  }

  void __set_activeSubscribeNum(const int32_t val) {
    activeSubscribeNum = val;
    __isset.activeSubscribeNum = true;
  }

  void __set_inActiveSubscribeNum(const int32_t val) {
    inActiveSubscribeNum = val;
    __isset.inActiveSubscribeNum = true;
  }

  void __set_subscribeAccounts(const std::vector<std::vector<SubscribeAccountClass> > & val) {
    subscribeAccounts = val;
    __isset.subscribeAccounts = true;
  }

  void __set_createTimestampMs(const int64_t val) {
    createTimestampMs = val;
    __isset.createTimestampMs = true;
  }

  bool operator == (const SubscribeCommodityClass & rhs) const
  {
    if (__isset.classId != rhs.__isset.classId)
      return false;
    else if (__isset.classId && !(classId == rhs.classId))
      return false;
    if (__isset.sledExchangeMic != rhs.__isset.sledExchangeMic)
      return false;
    else if (__isset.sledExchangeMic && !(sledExchangeMic == rhs.sledExchangeMic))
      return false;
    if (__isset.sledCommodityId != rhs.__isset.sledCommodityId)
      return false;
    else if (__isset.sledCommodityId && !(sledCommodityId == rhs.sledCommodityId))
      return false;
    if (__isset.sledCommodityType != rhs.__isset.sledCommodityType)
      return false;
    else if (__isset.sledCommodityType && !(sledCommodityType == rhs.sledCommodityType))
      return false;
    if (__isset.sledCommodityCode != rhs.__isset.sledCommodityCode)
      return false;
    else if (__isset.sledCommodityCode && !(sledCommodityCode == rhs.sledCommodityCode))
      return false;
    if (__isset.platformEnv != rhs.__isset.platformEnv)
      return false;
    else if (__isset.platformEnv && !(platformEnv == rhs.platformEnv))
      return false;
    if (__isset.activeMonths != rhs.__isset.activeMonths)
      return false;
    else if (__isset.activeMonths && !(activeMonths == rhs.activeMonths))
      return false;
    if (__isset.inactiveMonths != rhs.__isset.inactiveMonths)
      return false;
    else if (__isset.inactiveMonths && !(inactiveMonths == rhs.inactiveMonths))
      return false;
    if (__isset.activeSubscribeNum != rhs.__isset.activeSubscribeNum)
      return false;
    else if (__isset.activeSubscribeNum && !(activeSubscribeNum == rhs.activeSubscribeNum))
      return false;
    if (__isset.inActiveSubscribeNum != rhs.__isset.inActiveSubscribeNum)
      return false;
    else if (__isset.inActiveSubscribeNum && !(inActiveSubscribeNum == rhs.inActiveSubscribeNum))
      return false;
    if (__isset.subscribeAccounts != rhs.__isset.subscribeAccounts)
      return false;
    else if (__isset.subscribeAccounts && !(subscribeAccounts == rhs.subscribeAccounts))
      return false;
    if (__isset.createTimestampMs != rhs.__isset.createTimestampMs)
      return false;
    else if (__isset.createTimestampMs && !(createTimestampMs == rhs.createTimestampMs))
      return false;
    return true;
  }
  bool operator != (const SubscribeCommodityClass &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const SubscribeCommodityClass & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

void swap(SubscribeCommodityClass &a, SubscribeCommodityClass &b);

typedef struct _SubscribeContractItem__isset {
  _SubscribeContractItem__isset() : itemId(false), classId(false), sledExchangeMic(false), sledCommodityId(false), sledCommodityType(false), sledCommodityCode(false), sledContractCode(false), sledContractId(false), platformEnv(false), quotationAccountId(false), isForActive(false), createTimestampMs(false) {}
  bool itemId;
  bool classId;
  bool sledExchangeMic;
  bool sledCommodityId;
  bool sledCommodityType;
  bool sledCommodityCode;
  bool sledContractCode;
  bool sledContractId;
  bool platformEnv;
  bool quotationAccountId;
  bool isForActive;
  bool createTimestampMs;
} _SubscribeContractItem__isset;

class SubscribeContractItem {
 public:

  static const char* ascii_fingerprint; // = "C42C22C830D8257131D9729D38398B27";
  static const uint8_t binary_fingerprint[16]; // = {0xC4,0x2C,0x22,0xC8,0x30,0xD8,0x25,0x71,0x31,0xD9,0x72,0x9D,0x38,0x39,0x8B,0x27};

  SubscribeContractItem() : itemId(0), classId(0), sledExchangeMic(), sledCommodityId(0), sledCommodityType(0), sledCommodityCode(), sledContractCode(), sledContractId(0), platformEnv(( ::QuotationPlatformEnv::type)0), quotationAccountId(0), isForActive(0), createTimestampMs(0) {
  }

  virtual ~SubscribeContractItem() throw() {}

  int64_t itemId;
  int64_t classId;
  std::string sledExchangeMic;
  int64_t sledCommodityId;
  int16_t sledCommodityType;
  std::string sledCommodityCode;
  std::string sledContractCode;
  int64_t sledContractId;
   ::QuotationPlatformEnv::type platformEnv;
  int64_t quotationAccountId;
  bool isForActive;
  int64_t createTimestampMs;

  _SubscribeContractItem__isset __isset;

  void __set_itemId(const int64_t val) {
    itemId = val;
    __isset.itemId = true;
  }

  void __set_classId(const int64_t val) {
    classId = val;
    __isset.classId = true;
  }

  void __set_sledExchangeMic(const std::string& val) {
    sledExchangeMic = val;
    __isset.sledExchangeMic = true;
  }

  void __set_sledCommodityId(const int64_t val) {
    sledCommodityId = val;
    __isset.sledCommodityId = true;
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

  void __set_sledContractId(const int64_t val) {
    sledContractId = val;
    __isset.sledContractId = true;
  }

  void __set_platformEnv(const  ::QuotationPlatformEnv::type val) {
    platformEnv = val;
    __isset.platformEnv = true;
  }

  void __set_quotationAccountId(const int64_t val) {
    quotationAccountId = val;
    __isset.quotationAccountId = true;
  }

  void __set_isForActive(const bool val) {
    isForActive = val;
    __isset.isForActive = true;
  }

  void __set_createTimestampMs(const int64_t val) {
    createTimestampMs = val;
    __isset.createTimestampMs = true;
  }

  bool operator == (const SubscribeContractItem & rhs) const
  {
    if (__isset.itemId != rhs.__isset.itemId)
      return false;
    else if (__isset.itemId && !(itemId == rhs.itemId))
      return false;
    if (__isset.classId != rhs.__isset.classId)
      return false;
    else if (__isset.classId && !(classId == rhs.classId))
      return false;
    if (__isset.sledExchangeMic != rhs.__isset.sledExchangeMic)
      return false;
    else if (__isset.sledExchangeMic && !(sledExchangeMic == rhs.sledExchangeMic))
      return false;
    if (__isset.sledCommodityId != rhs.__isset.sledCommodityId)
      return false;
    else if (__isset.sledCommodityId && !(sledCommodityId == rhs.sledCommodityId))
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
    if (__isset.sledContractId != rhs.__isset.sledContractId)
      return false;
    else if (__isset.sledContractId && !(sledContractId == rhs.sledContractId))
      return false;
    if (__isset.platformEnv != rhs.__isset.platformEnv)
      return false;
    else if (__isset.platformEnv && !(platformEnv == rhs.platformEnv))
      return false;
    if (__isset.quotationAccountId != rhs.__isset.quotationAccountId)
      return false;
    else if (__isset.quotationAccountId && !(quotationAccountId == rhs.quotationAccountId))
      return false;
    if (__isset.isForActive != rhs.__isset.isForActive)
      return false;
    else if (__isset.isForActive && !(isForActive == rhs.isForActive))
      return false;
    if (__isset.createTimestampMs != rhs.__isset.createTimestampMs)
      return false;
    else if (__isset.createTimestampMs && !(createTimestampMs == rhs.createTimestampMs))
      return false;
    return true;
  }
  bool operator != (const SubscribeContractItem &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const SubscribeContractItem & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

void swap(SubscribeContractItem &a, SubscribeContractItem &b);

typedef struct _GenPreviewState__isset {
  _GenPreviewState__isset() : status(false), stateMsg(false), lastUpdateTimestampMs(false) {}
  bool status;
  bool stateMsg;
  bool lastUpdateTimestampMs;
} _GenPreviewState__isset;

class GenPreviewState {
 public:

  static const char* ascii_fingerprint; // = "AB7F6A8DAC27E2BAE524A3CBB66C8F8A";
  static const uint8_t binary_fingerprint[16]; // = {0xAB,0x7F,0x6A,0x8D,0xAC,0x27,0xE2,0xBA,0xE5,0x24,0xA3,0xCB,0xB6,0x6C,0x8F,0x8A};

  GenPreviewState() : status((EGenPreviewStatus::type)0), stateMsg(), lastUpdateTimestampMs(0) {
  }

  virtual ~GenPreviewState() throw() {}

  EGenPreviewStatus::type status;
  std::string stateMsg;
  int64_t lastUpdateTimestampMs;

  _GenPreviewState__isset __isset;

  void __set_status(const EGenPreviewStatus::type val) {
    status = val;
    __isset.status = true;
  }

  void __set_stateMsg(const std::string& val) {
    stateMsg = val;
    __isset.stateMsg = true;
  }

  void __set_lastUpdateTimestampMs(const int64_t val) {
    lastUpdateTimestampMs = val;
    __isset.lastUpdateTimestampMs = true;
  }

  bool operator == (const GenPreviewState & rhs) const
  {
    if (__isset.status != rhs.__isset.status)
      return false;
    else if (__isset.status && !(status == rhs.status))
      return false;
    if (__isset.stateMsg != rhs.__isset.stateMsg)
      return false;
    else if (__isset.stateMsg && !(stateMsg == rhs.stateMsg))
      return false;
    if (__isset.lastUpdateTimestampMs != rhs.__isset.lastUpdateTimestampMs)
      return false;
    else if (__isset.lastUpdateTimestampMs && !(lastUpdateTimestampMs == rhs.lastUpdateTimestampMs))
      return false;
    return true;
  }
  bool operator != (const GenPreviewState &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const GenPreviewState & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

void swap(GenPreviewState &a, GenPreviewState &b);

typedef struct _QuerySubscribeContractItemOption__isset {
  _QuerySubscribeContractItemOption__isset() : quotationAccountIds(false), sledCommodityIds(false), platformEnv(false) {}
  bool quotationAccountIds;
  bool sledCommodityIds;
  bool platformEnv;
} _QuerySubscribeContractItemOption__isset;

class QuerySubscribeContractItemOption {
 public:

  static const char* ascii_fingerprint; // = "8536E51FD315FF015A827B9CFBEDAE16";
  static const uint8_t binary_fingerprint[16]; // = {0x85,0x36,0xE5,0x1F,0xD3,0x15,0xFF,0x01,0x5A,0x82,0x7B,0x9C,0xFB,0xED,0xAE,0x16};

  QuerySubscribeContractItemOption() : platformEnv(( ::QuotationPlatformEnv::type)0) {
  }

  virtual ~QuerySubscribeContractItemOption() throw() {}

  std::set<int64_t>  quotationAccountIds;
  std::set<int64_t>  sledCommodityIds;
   ::QuotationPlatformEnv::type platformEnv;

  _QuerySubscribeContractItemOption__isset __isset;

  void __set_quotationAccountIds(const std::set<int64_t> & val) {
    quotationAccountIds = val;
    __isset.quotationAccountIds = true;
  }

  void __set_sledCommodityIds(const std::set<int64_t> & val) {
    sledCommodityIds = val;
    __isset.sledCommodityIds = true;
  }

  void __set_platformEnv(const  ::QuotationPlatformEnv::type val) {
    platformEnv = val;
    __isset.platformEnv = true;
  }

  bool operator == (const QuerySubscribeContractItemOption & rhs) const
  {
    if (__isset.quotationAccountIds != rhs.__isset.quotationAccountIds)
      return false;
    else if (__isset.quotationAccountIds && !(quotationAccountIds == rhs.quotationAccountIds))
      return false;
    if (__isset.sledCommodityIds != rhs.__isset.sledCommodityIds)
      return false;
    else if (__isset.sledCommodityIds && !(sledCommodityIds == rhs.sledCommodityIds))
      return false;
    if (__isset.platformEnv != rhs.__isset.platformEnv)
      return false;
    else if (__isset.platformEnv && !(platformEnv == rhs.platformEnv))
      return false;
    return true;
  }
  bool operator != (const QuerySubscribeContractItemOption &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const QuerySubscribeContractItemOption & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

void swap(QuerySubscribeContractItemOption &a, QuerySubscribeContractItemOption &b);

typedef struct _SubscribeContractItemPage__isset {
  _SubscribeContractItemPage__isset() : totalCount(false), resultList(false) {}
  bool totalCount;
  bool resultList;
} _SubscribeContractItemPage__isset;

class SubscribeContractItemPage {
 public:

  static const char* ascii_fingerprint; // = "9C4683036F1B22F52396ECC6DACD232C";
  static const uint8_t binary_fingerprint[16]; // = {0x9C,0x46,0x83,0x03,0x6F,0x1B,0x22,0xF5,0x23,0x96,0xEC,0xC6,0xDA,0xCD,0x23,0x2C};

  SubscribeContractItemPage() : totalCount(0) {
  }

  virtual ~SubscribeContractItemPage() throw() {}

  int32_t totalCount;
  std::vector<SubscribeContractItem>  resultList;

  _SubscribeContractItemPage__isset __isset;

  void __set_totalCount(const int32_t val) {
    totalCount = val;
    __isset.totalCount = true;
  }

  void __set_resultList(const std::vector<SubscribeContractItem> & val) {
    resultList = val;
    __isset.resultList = true;
  }

  bool operator == (const SubscribeContractItemPage & rhs) const
  {
    if (__isset.totalCount != rhs.__isset.totalCount)
      return false;
    else if (__isset.totalCount && !(totalCount == rhs.totalCount))
      return false;
    if (__isset.resultList != rhs.__isset.resultList)
      return false;
    else if (__isset.resultList && !(resultList == rhs.resultList))
      return false;
    return true;
  }
  bool operator != (const SubscribeContractItemPage &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const SubscribeContractItemPage & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

void swap(SubscribeContractItemPage &a, SubscribeContractItemPage &b);

}}}} // namespace

#endif