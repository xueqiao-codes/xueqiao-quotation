/*
 * base_resp.h
 *
 *  Created on: 2017年4月14日
 *      Author: wileywang
 */

#ifndef QUOTATION_ACCESS_COMMON_BASE_RESP_H_
#define QUOTATION_ACCESS_COMMON_BASE_RESP_H_

#include <string>

namespace xueqiao {
namespace quotation {
namespace access {

struct BaseResp {
    int errorCode = 0;
    std::string errorMsg;
};

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_COMMON_BASE_RESP_H_ */
