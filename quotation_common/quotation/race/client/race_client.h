/*
 * race_client.h
 *
 *  Created on: 2017年3月4日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_CLIENT_RACE_CLIENT_H_
#define QUOTATION_RACE_CLIENT_RACE_CLIENT_H_

#include <memory>

#include "quotation_common/thrift/quotation_item_types.h"

namespace xueqiao {
namespace quotation {

class IRaceClient {
public:
	virtual ~IRaceClient() = default;

	virtual void send2Race(const std::shared_ptr<QuotationItem>& item) noexcept = 0;

protected:
	IRaceClient() = default;

};





} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_RACE_CLIENT_RACE_CLIENT_H_ */
