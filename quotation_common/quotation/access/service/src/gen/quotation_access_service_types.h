/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#ifndef quotation_access_service_TYPES_H
#define quotation_access_service_TYPES_H

#include <thrift/Thrift.h>
#include <thrift/TApplicationException.h>
#include <thrift/protocol/TProtocol.h>
#include <thrift/transport/TTransport.h>

#include <thrift/cxxfunctional.h>
#include "comm_types.h"
#include "quotation_account_types.h"


namespace xueqiao { namespace quotation { namespace access {

typedef struct _QuotationAccessState__isset {
  _QuotationAccessState__isset() : state(false), stateMsg(false) {}
  bool state;
  bool stateMsg;
} _QuotationAccessState__isset;

class QuotationAccessState {
 public:

  static const char* ascii_fingerprint; // = "8F91EF8DF0BD202ABA85195A6109549D";
  static const uint8_t binary_fingerprint[16]; // = {0x8F,0x91,0xEF,0x8D,0xF0,0xBD,0x20,0x2A,0xBA,0x85,0x19,0x5A,0x61,0x09,0x54,0x9D};

  QuotationAccessState() : state(( ::QuotationAccountAccessState::type)0), stateMsg() {
  }

  virtual ~QuotationAccessState() throw() {}

   ::QuotationAccountAccessState::type state;
  std::string stateMsg;

  _QuotationAccessState__isset __isset;

  void __set_state(const  ::QuotationAccountAccessState::type val) {
    state = val;
    __isset.state = true;
  }

  void __set_stateMsg(const std::string& val) {
    stateMsg = val;
    __isset.stateMsg = true;
  }

  bool operator == (const QuotationAccessState & rhs) const
  {
    if (__isset.state != rhs.__isset.state)
      return false;
    else if (__isset.state && !(state == rhs.state))
      return false;
    if (__isset.stateMsg != rhs.__isset.stateMsg)
      return false;
    else if (__isset.stateMsg && !(stateMsg == rhs.stateMsg))
      return false;
    return true;
  }
  bool operator != (const QuotationAccessState &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const QuotationAccessState & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

};

void swap(QuotationAccessState &a, QuotationAccessState &b);

}}} // namespace

#endif
