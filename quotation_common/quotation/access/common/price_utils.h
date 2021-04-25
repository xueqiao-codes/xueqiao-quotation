/*
 * price_utils.h
 *
 *  Created on: 2018年10月24日
 *      Author: wangli
 */

#ifndef QUOTATION_ACCESS_COMMON_PRICE_UTILS_H_
#define QUOTATION_ACCESS_COMMON_PRICE_UTILS_H_


namespace xueqiao {
namespace quotation {
namespace access {

class PriceUtils {
public:
    static bool isAppropriatePrice(double price);

    static bool isZeroPrice(double price);
};


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao


#endif /* QUOTATION_ACCESS_COMMON_PRICE_UTILS_H_ */
