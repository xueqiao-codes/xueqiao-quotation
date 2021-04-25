/*
 * race_store_memcacheq_quotation_handler.cpp
 *
 *  Created on: 2017年9月21日
 *      Author: wileywang
 */
#include "quotation/race/server/race_store_memcacheq_quotation_handler.h"

#include <boost/lexical_cast.hpp>
#include "attr/attr_reporter_factory.h"
#include "base/base64.h"
#include "base/code_defense.h"
#include "base/hash.h"
#include "thrift/protocol/TCompactProtocol.h"

namespace xueqiao {
namespace quotation {

RaceStoreMemcacheqQuotationHandler::RaceStoreMemcacheqQuotationHandler()
    : store_queue_(1000000)
     , write_buffer_(new apache::thrift::transport::TMemoryBuffer(10*1024))
     , attr_reporter_(soldier::attr::AttrReporterFactory::Global().thirtySecs()){

    attr_store_memcacheq_dropped_count_key_ = attr_reporter_.requireKey("quotation.race.store.memcacheq.dropped.count", {});
    attr_store_memcacheq_queue_count_key_ = attr_reporter_.requireKey("quotation.race.store.memcacheq.queued.count", {});
    attr_store_memcacheq_send_total_count_key_ = attr_reporter_.requireKey("quotation.race.store.memcacheq.send.total.count", {});
    attr_store_memcached_send_failed_count_key_ = attr_reporter_.requireKey("quotation.race.store.memcacheq.send.failed.count", {});
    attr_store_memcached_send_timens_key_ = attr_reporter_.requireKey("quotation.race.store.memcacheq.send.timens", {});
    attr_store_memcached_send_item_size_ = attr_reporter_.requireKey("quotation.race.store.memcacheq.send.item.size", {});

    const char* host_addr = getenv("HOST_ADDR");
    std::string config = "--SERVER=";
    if (host_addr) {
        config.append(host_addr);
        config.append(":22201");
    } else {
        config.append("127.0.0.1:22201");
    }

    mc_ = memcached(config.c_str(), config.length());
    CHECK(mc_ != NULL);
    store_thread_.reset(new std::thread(&RaceStoreMemcacheqQuotationHandler::onWorkingStore, this));
}

RaceStoreMemcacheqQuotationHandler::~RaceStoreMemcacheqQuotationHandler() {
    ending_ = true;
    store_thread_->join();

    while(true) {
        QueueStoreItem* item = NULL;
        store_queue_.pop(item);
        if (item == NULL) {
            break;
        }
        delete item;
    }

    memcached_free(mc_);
}

void RaceStoreMemcacheqQuotationHandler::handleQuotation(const std::shared_ptr<QuotationItem>& item) noexcept {
    std::unique_ptr<QueueStoreItem> queue_item(new QueueStoreItem(item));
    if (!store_queue_.bounded_push(queue_item.get())) {
        attr_reporter_.inc(attr_store_memcacheq_dropped_count_key_, 1);
        APPLOG_INFO("store quotation item platform={}, contractSymbols={} dropped!", item->platform, item->contractSymbol);
    } else {
        store_queued_count_ += 1;
        queue_item.release();
    }
}

void RaceStoreMemcacheqQuotationHandler::onWorkingStore() {
    while(!ending_) {
        QueueStoreItem* item = NULL;
        if (!store_queue_.pop(item)) {
            std::this_thread::sleep_for(std::chrono::milliseconds(10));
            continue;
        }

        store_queued_count_ -= 1;

        std::unique_ptr<QueueStoreItem> current_store_item(item);
        while(true) {
            if (ending_) {
                return ;
            }

            attr_reporter_.keep(attr_store_memcacheq_queue_count_key_, store_queued_count_);
            std::chrono::high_resolution_clock::time_point start = std::chrono::high_resolution_clock::now();
            attr_reporter_.inc(attr_store_memcacheq_send_total_count_key_, 1);
            if (storeItem(*current_store_item)) {
                attr_reporter_.average(attr_store_memcached_send_timens_key_
                        , std::chrono::duration_cast<std::chrono::nanoseconds>(
                                std::chrono::high_resolution_clock::now() - start).count());
                break;
            } else {
                attr_reporter_.inc(attr_store_memcached_send_failed_count_key_, 1);
                std::this_thread::sleep_for(std::chrono::milliseconds(100));
                continue;
            }
        }
    }
}

bool RaceStoreMemcacheqQuotationHandler::storeItem(const QueueStoreItem& item) {
    static const std::string queue_key = "quotation";

    apache::thrift::protocol::TCompactProtocolT<apache::thrift::transport::TMemoryBuffer> protocol(write_buffer_);
    DCHECK(item.quotation_item.get() != NULL);
    item.quotation_item->write(&protocol);

    uint8_t* buf = NULL;
    uint32_t size = 0;
    write_buffer_->getBuffer(&buf, &size);

    std::unique_ptr<char[]> resultBuffer(new char[Base64encode_len((int)size) + 1]);
    int resultSize = Base64encode(resultBuffer.get(), (const char*)buf, (int)size);
    CHECK(resultSize >= 0);

    resultBuffer[resultSize] = '\0';
    write_buffer_->resetBuffer();

    attr_reporter_.average(attr_store_memcached_send_item_size_, resultSize);

    memcached_return rc = memcached_set(mc_
            , queue_key.c_str()
            , queue_key.length()
            , resultBuffer.get()
            , resultSize
            , 0
            , 0);
    if (rc != MEMCACHED_SUCCESS) {
        APPLOG_ERROR("store memcacheq platform={} contractSymbols={} raceTimestamp={} queue_key={} size={} failed, rc={} errormsg={}"
                , item.quotation_item->platform
                , item.quotation_item->contractSymbol
                , item.quotation_item->raceTimestampMs
                , queue_key
                , resultSize
                , rc
                , memcached_strerror(mc_, rc));
        return false;
    }
    APPLOG_DEBUG("store quotation platform={} contractSymbols={} raceTimestamp={} queue_key={} success!"
            , item.quotation_item->platform
            , item.quotation_item->contractSymbol
            , item.quotation_item->raceTimestampMs
            , queue_key);

    return true;
}


} // end namespace quotation
} // end namespace xueqiao



