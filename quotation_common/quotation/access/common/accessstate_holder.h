/*
 * acessstate_holder.h
 *
 *  Created on: 2018年12月23日
 *      Author: 44385
 */

#ifndef QUOTATION_ACCESS_COMMON_ACCESSSTATE_HOLDER_H_
#define QUOTATION_ACCESS_COMMON_ACCESSSTATE_HOLDER_H_

#include <mutex>
#include "quotation_access_service_types.h"

namespace xueqiao {
namespace quotation {
namespace access {

class QuotationAccessStateHolder {
public:
    ~QuotationAccessStateHolder() = default;
    static QuotationAccessStateHolder& Global();

    void setAccessState(const QuotationAccessState& state);
    void setAccessState(QuotationAccountAccessState::type stateType, const std::string& stateMsg) {
        QuotationAccessState state;
        state.__set_state(stateType);
        state.__set_stateMsg(stateMsg);
        setAccessState(state);
    }
    void setAccessStateInvalid(const std::string& invalidMsg) {
        setAccessState(QuotationAccountAccessState::ACCOUNT_INVALID, invalidMsg);
    }
    void setAccessStateValid() {
        setAccessState(QuotationAccountAccessState::ACCOUNT_ACTIVE, "");
    }

    void getAccessState(QuotationAccessState& state);

private:
    QuotationAccessStateHolder() = default;

    std::mutex lock_;
    QuotationAccessState state_;
};


} // end namespace access
} // end namespace xueqiao
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_COMMON_ACCESSSTATE_HOLDER_H_ */
