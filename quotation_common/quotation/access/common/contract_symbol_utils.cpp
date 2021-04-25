/*
 * contract_symbol_utils.cc
 *
 *  Created on: 2018年3月7日
 *      Author: wangli
 */
#include "contract_symbol_utils.h"

#include <boost/lexical_cast.hpp>
#include "base/url_util.h"

namespace xueqiao {
namespace quotation {
namespace access {

std::string ContractSymbolUtils::joinContractSymbols(
            const std::string& sledExchangeCode
            , ::xueqiao::contract::standard::SledCommodityType::type sledCommodityType
            , const std::string& sledCommodityCode
            , const std::string& sledContractCode){
    std::string contract_symbol;
    contract_symbol.append(sledExchangeCode)
                   .append("|").append(boost::lexical_cast<std::string>(sledCommodityType))
                   .append("|").append(sledCommodityCode)
                   .append("|").append(sledContractCode);
    return soldier::base::urlEncode(contract_symbol);
}

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao
