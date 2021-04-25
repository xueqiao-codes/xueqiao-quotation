/*
 * price_utils.cpp
 *
 *  Created on: 2018年10月24日
 *      Author: wangli
 */

#include "price_utils.h"

#include <limits>
#include <cmath>

namespace xueqiao {
namespace quotation {
namespace access {

bool PriceUtils::isAppropriatePrice(double price) {
    return !std::isnan(price) && !std::isinf(price);
}

bool PriceUtils::isZeroPrice(double price) {
    return isAppropriatePrice(price) && std::abs(price) <= std::numeric_limits<double>::epsilon();
}

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao
