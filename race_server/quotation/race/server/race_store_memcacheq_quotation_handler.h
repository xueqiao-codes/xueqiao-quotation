/*
 * race_store_memcacheq_quotation_handler.h
 *
 *  Created on: 2017年9月21日
 *      Author: wileywang
 */

#ifndef QUOTATION_RACE_SERVER_RACE_STORE_MEMCACHEQ_QUOTATION_HANDLER_H_
#define QUOTATION_RACE_SERVER_RACE_STORE_MEMCACHEQ_QUOTATION_HANDLER_H_

#include <atomic>
#include <boost/lockfree/queue.hpp>
#include <boost/shared_ptr.hpp>
#include <memory>
#include <thread>

#include "attr/attr_reporter.h"
#include "libmemcached/memcached.h"
#include "quotation/race/server/race_quotation_handler.h"
#include "thrift/transport/TBufferTransports.h"

namespace xueqiao {
namespace quotation {

class RaceStoreMemcacheqQuotationHandler : public IRaceQuotationHandler {
public:
    RaceStoreMemcacheqQuotationHandler();
    virtual ~RaceStoreMemcacheqQuotationHandler();

    virtual void handleQuotation(const std::shared_ptr<QuotationItem>& item) noexcept;

private:
    void onWorkingStore();

    struct QueueStoreItem {
        QueueStoreItem(const std::shared_ptr<QuotationItem>& item)
            : quotation_item(item) {
        }
        ~QueueStoreItem() = default;

        std::shared_ptr<QuotationItem> quotation_item;
    };

    bool storeItem(const QueueStoreItem& item);

    boost::lockfree::queue<QueueStoreItem*> store_queue_;
    std::atomic_int_fast64_t store_queued_count_ = {0};

    memcached_st* mc_ = nullptr;
    std::atomic_bool ending_ = {false};
    std::unique_ptr<std::thread> store_thread_;

    boost::shared_ptr<apache::thrift::transport::TMemoryBuffer> write_buffer_;

    soldier::attr::IAttrReporter& attr_reporter_;
    int64_t attr_store_memcacheq_dropped_count_key_ = -1;
    int64_t attr_store_memcacheq_queue_count_key_ = -1;
    int64_t attr_store_memcacheq_send_total_count_key_ = -1;
    int64_t attr_store_memcached_send_failed_count_key_ = -1;
    int64_t attr_store_memcached_send_timens_key_ = -1;
    int64_t attr_store_memcached_send_item_size_ = -1;
};


} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_RACE_SERVER_RACE_STORE_MEMCACHEQ_QUOTATION_HANDLER_H_ */
