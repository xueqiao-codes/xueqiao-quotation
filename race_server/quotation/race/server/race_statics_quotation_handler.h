/*
 * race_statics_quotation_handler.h
 *
 *  Created on: 2017年9月27日
 *      Author: wileywang
 */

#ifndef QUOTATION_RACE_SERVER_RACE_STATICS_QUOTATION_HANDLER_H_
#define QUOTATION_RACE_SERVER_RACE_STATICS_QUOTATION_HANDLER_H_

#include <atomic>
#include <boost/lockfree/queue.hpp>
#include <boost/shared_ptr.hpp>
#include <memory>
#include <thread>
#include <cmath>

#include "attr/attr_reporter.h"
#include "spdlog/fmt/ostr.h"
#include "libmemcached/memcached.h"
#include "quotation/race/server/race_quotation_handler.h"
#include "thrift/transport/TBufferTransports.h"


namespace xueqiao {
namespace quotation {

struct StaticsItem {
    std::string platform;
    std::string contract_symbols;

    int64_t kline_minute_start_timestampS = 0;

    double kline_open_price = NAN;
    double kline_close_price = NAN;
    double kline_low_price = NAN;
    double kline_high_price = NAN;

    int64_t kline_qty = 0;
    int64_t kline_open_interest = 0;

    double kline_settlement_turnover = 0.0;

    int64_t kline_volumn = 0;

    template<typename OStream>
    friend OStream& operator<<(OStream& os, const StaticsItem &item) {
         os << "StaticsItem(platform=" << item.platform
            << ", contract_symbols=" << item.contract_symbols
            << ", kline_minute_start_timestampS=" << item.kline_minute_start_timestampS
            << ", kline_open_price=";
         if (std::isnan(item.kline_open_price)) {
             os << "NAN";
         } else {
             os << item.kline_open_price;
         }

         os << ", kline_close_price=";
         if (std::isnan(item.kline_close_price)){
             os << "NAN";
         } else {
             os << item.kline_close_price;
         }

         os << ", kline_low_price=";
         if (std::isnan(item.kline_low_price)) {
             os << "NAN";
         } else {
             os << item.kline_low_price;
         }

         os << ", kline_high_price=";
         if (std::isnan(item.kline_high_price)) {
             os << "NAN";
         } else {
             os << item.kline_high_price;
         }

         os << ", kline_qty=" << item.kline_qty
            << ", kline_open_interest=" << item.kline_open_interest
            << ", kline_settlement_turnover=" << item.kline_settlement_turnover
            << ", kline_volumn=" << item.kline_volumn
            << ")";

         return os;
    }

};

class IStaticsAnalyzer {
public:
    virtual ~IStaticsAnalyzer() = default;

    // 第一笔统计行情到来
    virtual void onNewStaticsItem(const QuotationItem& new_quotation, StaticsItem& new_statics) = 0;

    // 相同分钟行情刷新
    virtual void onUpdateStaticsItem(const QuotationItem& update_quotation, StaticsItem& update_statics) = 0;

    // 下一分钟行情到来，完成当前统计，同时初始化下一笔统计
    virtual void onNextStaticsItem(const QuotationItem& next_quotation
            , StaticsItem& pre_statics
            , StaticsItem& next_statics) = 0;

    // 当前分钟行情完成, 完成当前统计，同时初始化下一笔统计
    virtual bool onFinishStaticsItem(
            StaticsItem& pre_statics
            , StaticsItem& next_statics) = 0;

    virtual const std::string& name() = 0;

protected:
    IStaticsAnalyzer() = default;
};


class RaceStaticsQuotationHandler : public IRaceQuotationHandler {
public:
    RaceStaticsQuotationHandler();
    virtual ~RaceStaticsQuotationHandler();

    virtual void handleQuotation(const std::shared_ptr<QuotationItem>& item) noexcept;

private:
    void onWorkingStatics();

    struct QuotationQueueItem {
        QuotationQueueItem(const std::shared_ptr<QuotationItem>& item)
            : quotation_item(item) {
        }
        ~QuotationQueueItem() = default;

        std::shared_ptr<QuotationItem> quotation_item;
    };

    struct StaticsEntry {
        std::shared_ptr<StaticsItem> statics_item;
        std::shared_ptr<IStaticsAnalyzer> statics_analyzer;
    };

    void onProcessQuotationItem(const std::shared_ptr<QuotationItem>& quotation_item);
    void storeStaticsItem(const StaticsItem& item);
    void refreshStaticsItems(const std::string& ext_key, int64_t minute_timestampS);
    bool storeMemcacheq(const std::string& queue_key
            , const StaticsItem& item
            , const char* itemstr
            , int item_size);

    boost::lockfree::queue<QuotationQueueItem*> quotation_queue_;
    std::atomic_int_fast64_t quotation_queue_count_ = {0};

    memcached_st* mc_ = nullptr;
    std::atomic_bool ending_ = {false};
    std::unique_ptr<std::thread> statics_thread_;

    boost::shared_ptr<apache::thrift::transport::TMemoryBuffer> write_buffer_;

    std::map<std::string, StaticsEntry> statics_entry_map_;

    soldier::attr::IAttrReporter& attr_reporter_;
    int64_t attr_store_statics_dropped_count_key_ = -1;
    int64_t attr_store_statics_queue_count_key_ = -1;
    int64_t attr_store_statics_memcacheq_send_total_count_key_ = -1;
    int64_t attr_store_statics_memcacheq_send_failed_count_key_ = -1;
    int64_t attr_store_statics_memcached_send_timens_key_ = -1;
    int64_t attr_store_statics_memcached_send_item_size_ = -1;
};


} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_RACE_SERVER_RACE_STATICS_QUOTATION_HANDLER_H_ */
