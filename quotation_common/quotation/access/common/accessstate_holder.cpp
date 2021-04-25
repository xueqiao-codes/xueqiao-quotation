/*
 * accessstate_holder.cpp
 *
 *  Created on: 2018年12月23日
 *      Author: 44385
 */

#include "accessstate_holder.h"

using namespace xueqiao::quotation::access;

static std::unique_ptr<QuotationAccessStateHolder> S_INSTANCE;

QuotationAccessStateHolder& QuotationAccessStateHolder::Global() {
    if (!S_INSTANCE) {
        static std::mutex lock;
        lock.lock();
        if (!S_INSTANCE){
            S_INSTANCE.reset(new QuotationAccessStateHolder());
        }
        lock.unlock();
    }
    return *S_INSTANCE;
}

void QuotationAccessStateHolder::setAccessState(const QuotationAccessState& state) {
    lock_.lock();
    state_ = state;
    lock_.unlock();
}

void QuotationAccessStateHolder::getAccessState(QuotationAccessState& state) {
    lock_.lock();
    state = state_;
    lock_.unlock();
}
