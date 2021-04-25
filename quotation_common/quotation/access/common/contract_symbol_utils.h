/*
 * contract_symbol_utils.h
 *
 *  Created on: 2018年3月7日
 *      Author: wangli
 */

#ifndef QUOTATION_ACCESS_COMMON_CONTRACT_SYMBOL_UTILS_H_
#define QUOTATION_ACCESS_COMMON_CONTRACT_SYMBOL_UTILS_H_

#include "contract_standard_types.h"

namespace xueqiao {
namespace quotation {
namespace access {

class ContractSymbolUtils {
public:
    static std::string joinContractSymbols(
            const std::string& sledExchangeCode
            , ::xueqiao::contract::standard::SledCommodityType::type sledCommodityType
            , const std::string& sledCommodityCode
            , const std::string& sledContractCode);

};

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_COMMON_CONTRACT_SYMBOL_UTILS_H_ */
