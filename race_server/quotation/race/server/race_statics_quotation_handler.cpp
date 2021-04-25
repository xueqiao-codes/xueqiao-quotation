/*
 * race_statics_quotation_handler.cpp
 *
 *  Created on: 2017年9月27日
 *      Author: wileywang
 */

#include "quotation/race/server/race_statics_quotation_handler.h"

#include <boost/lexical_cast.hpp>
#include "attr/attr_reporter_factory.h"
#include "base/base64.h"
#include "base/code_defense.h"
#include "base/hash.h"
#include "thrift/protocol/TCompactProtocol.h"

namespace xueqiao {
namespace quotation {

static const std::string VOLUMN_STATICS_ANALYZER = "VolumnStaticsAnalyzer";
static const std::string LASTQTY_STATICS_ANALYZER = "LastQtyStaticsAnalyzer";

class VolumnStaticsAnalyzer : public IStaticsAnalyzer {
public:
    VolumnStaticsAnalyzer() = default;
    virtual ~VolumnStaticsAnalyzer() = default;

    virtual void onNewStaticsItem(const QuotationItem& new_quotation, StaticsItem& new_statics) {
        new_statics.kline_volumn = new_quotation.volumn;
    }

    virtual void onUpdateStaticsItem(const QuotationItem& update_quotation, StaticsItem& update_statics) {
        int64_t last_qty = update_quotation.volumn - update_statics.kline_volumn;
        if (last_qty <= 0) {
            update_statics.kline_volumn = update_quotation.volumn;
            return ;
        }

        update_statics.kline_qty += last_qty;
        update_statics.kline_settlement_turnover += update_quotation.lastPrice * last_qty;

        if (std::isnan(update_statics.kline_open_price)) {
            update_statics.kline_open_price = update_quotation.lastPrice;
            update_statics.kline_open_interest = update_quotation.openInterest;
        }
        update_statics.kline_close_price = update_quotation.lastPrice;

        if (std::isnan(update_statics.kline_high_price)) {
            update_statics.kline_high_price = update_quotation.lastPrice;
        } else {
            if (update_quotation.lastPrice > update_statics.kline_high_price) {
                update_statics.kline_high_price = update_quotation.lastPrice;
            }
        }

        if (std::isnan(update_statics.kline_low_price)) {
            update_statics.kline_low_price = update_quotation.lastPrice;
        } else {
            if (update_quotation.lastPrice < update_statics.kline_low_price) {
                update_statics.kline_low_price = update_quotation.lastPrice;
            }
        }

        update_statics.kline_volumn = update_quotation.volumn;
    }

    virtual void onNextStaticsItem(const QuotationItem& next_quotation
                , StaticsItem& pre_statics
                , StaticsItem& next_statics) {
        long last_qty = next_quotation.volumn - pre_statics.kline_volumn;
        if (last_qty > 0) {
            next_statics.kline_qty = last_qty;
            next_statics.kline_settlement_turnover = next_quotation.lastPrice * last_qty;
            next_statics.kline_open_price = next_quotation.lastPrice;
            next_statics.kline_close_price = next_quotation.lastPrice;
            next_statics.kline_high_price = next_quotation.lastPrice;
            next_statics.kline_low_price = next_quotation.lastPrice;
            next_statics.kline_open_interest = next_quotation.openInterest;
        }
        next_statics.kline_volumn = next_quotation.volumn;
    }

    virtual bool onFinishStaticsItem(
                StaticsItem& pre_statics
                , StaticsItem& next_statics) {
        if (next_statics.kline_minute_start_timestampS <= pre_statics.kline_minute_start_timestampS) {
            return false;
        }

        next_statics.kline_volumn = pre_statics.kline_volumn;
        return true;
    }

    virtual const std::string& name() { return VOLUMN_STATICS_ANALYZER; }
};

class LastQtyStaticsAnalyzer : public IStaticsAnalyzer {
public:
    LastQtyStaticsAnalyzer() = default;
    virtual ~LastQtyStaticsAnalyzer() = default;

    virtual void onNewStaticsItem(const QuotationItem& new_quotation, StaticsItem& new_statics) {
        if (new_quotation.lastQty <= 0) {
            return ;
        }

        new_statics.kline_qty = new_quotation.lastQty;
        new_statics.kline_settlement_turnover = new_quotation.lastPrice*new_quotation.lastQty;

        new_statics.kline_open_price = new_quotation.lastPrice;
        new_statics.kline_close_price = new_quotation.lastPrice;
        new_statics.kline_high_price = new_quotation.lastPrice;
        new_statics.kline_low_price = new_quotation.lastPrice;
        new_statics.kline_open_interest = new_quotation.openInterest;
    }

    virtual void onUpdateStaticsItem(const QuotationItem& update_quotation, StaticsItem& update_statics) {
        if (update_quotation.lastQty <= 0) {
            return ;
        }

        update_statics.kline_qty += update_quotation.lastQty;
        update_statics.kline_settlement_turnover += update_quotation.lastPrice * update_quotation.lastQty;

        if (std::isnan(update_statics.kline_open_price)) {
            update_statics.kline_open_price = update_quotation.lastPrice;
            update_statics.kline_open_interest = update_quotation.openInterest;
        }
        update_statics.kline_close_price = update_quotation.lastPrice;

        if(std::isnan(update_statics.kline_high_price)) {
            update_statics.kline_high_price = update_quotation.lastPrice;
        } else {
            if (update_quotation.lastPrice > update_statics.kline_high_price) {
                update_statics.kline_high_price = update_quotation.lastPrice;
            }
        }

        if (std::isnan(update_statics.kline_low_price)) {
            update_statics.kline_low_price = update_quotation.lastPrice;
        } else {
            if (update_quotation.lastPrice < update_statics.kline_low_price) {
                update_statics.kline_low_price = update_quotation.lastPrice;
            }
        }
     }

    virtual void onNextStaticsItem(const QuotationItem& next_quotation
                , StaticsItem& pre_statics
                , StaticsItem& next_statics)  {
        if (next_quotation.lastQty > 0) {
            onNewStaticsItem(next_quotation, next_statics);
        }
    }

    virtual bool onFinishStaticsItem(
                StaticsItem& pre_statics
                , StaticsItem& next_statics) {
        if (next_statics.kline_minute_start_timestampS <= pre_statics.kline_minute_start_timestampS) {
            return false;
        }
        return true;
    }

    virtual const std::string& name() { return LASTQTY_STATICS_ANALYZER; }
};


RaceStaticsQuotationHandler::RaceStaticsQuotationHandler()
    : quotation_queue_(1000000)
     , write_buffer_(new apache::thrift::transport::TMemoryBuffer(10*1024))
     , attr_reporter_(soldier::attr::AttrReporterFactory::Global().thirtySecs()) {

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

    attr_store_statics_dropped_count_key_ = attr_reporter_.requireKey("quotation.race.statics.dropped.count", {});
    attr_store_statics_queue_count_key_ = attr_reporter_.requireKey("quotation.race.statics.queued.count", {});
    attr_store_statics_memcacheq_send_total_count_key_ = attr_reporter_.requireKey("quotation.race.statics.memcacheq.send.total.count", {});
    attr_store_statics_memcacheq_send_failed_count_key_ = attr_reporter_.requireKey("quotation.race.statics.memcacheq.send.failed.count", {});
    attr_store_statics_memcached_send_timens_key_ = attr_reporter_.requireKey("quotation.race.statics.memcacheq.send.timens", {});
    attr_store_statics_memcached_send_item_size_ = attr_reporter_.requireKey("quotation.race.statics.memcacheq.send.item.size", {});

    statics_thread_.reset(new std::thread(&RaceStaticsQuotationHandler::onWorkingStatics, this));
}

RaceStaticsQuotationHandler::~RaceStaticsQuotationHandler() {
    ending_ = true;

    statics_thread_->join();
    while(true) {
        QuotationQueueItem* item = NULL;
        quotation_queue_.pop(item);
        if (item == NULL) {
            break;
        }
        delete item;
    }

    memcached_free(mc_);
}

void RaceStaticsQuotationHandler::handleQuotation(const std::shared_ptr<QuotationItem>& item) noexcept {
    std::unique_ptr<QuotationQueueItem> queue_item(new QuotationQueueItem(item));
    if (!quotation_queue_.bounded_push(queue_item.get())) {
//            attr_reporter_.inc(attr_store_memcacheq_dropped_count_key_, 1);
        APPLOG_INFO("store quotation item platform={}, contractSymbols={} dropped!", item->platform, item->contractSymbol);
    } else {
        quotation_queue_count_ += 1;
        queue_item.release();
    }
}

void RaceStaticsQuotationHandler::onWorkingStatics() {
    while(!ending_) {
        QuotationQueueItem* queue_item = NULL;
        if (!quotation_queue_.pop(queue_item)) {
            std::this_thread::sleep_for(std::chrono::milliseconds(10));
            continue;
        }

        quotation_queue_count_ -= 1;

        std::unique_ptr<QuotationQueueItem> current_quotation_item(queue_item);
        onProcessQuotationItem(current_quotation_item->quotation_item);
    }
}

static std::string getStaticsKey(const QuotationItem& quotation_item) {
    std::string key("/");
    key.append(quotation_item.platform);
    key.append("/");
    key.append(quotation_item.contractSymbol);
    return key;
}

static int64_t getMinuteTimestampS(const int64_t& timestamp_ms) {
    int64_t timestampS = timestamp_ms / 1000;
    return timestampS - (timestampS%60);
}

static StaticsItem* createNewStaticsItem(const QuotationItem& quotation_item, long minute_timestampS) {
    std::unique_ptr<StaticsItem> new_item(new StaticsItem());

    new_item->platform = quotation_item.platform;
    new_item->contract_symbols = quotation_item.contractSymbol;
    new_item->kline_minute_start_timestampS = minute_timestampS;

    return new_item.release();
}

void RaceStaticsQuotationHandler::onProcessQuotationItem(const std::shared_ptr<QuotationItem>& quotation_item) {
    std::string statics_key = getStaticsKey(*quotation_item);
    int64_t minute_timestampS = getMinuteTimestampS(quotation_item->raceTimestampMs);

    auto statics_entry_it = statics_entry_map_.find(statics_key);
    if (statics_entry_it == statics_entry_map_.end()) {
        StaticsEntry new_statics_entry;

        new_statics_entry.statics_item.reset(createNewStaticsItem(*quotation_item, minute_timestampS));
//        if (quotation_item->volumn == 0) {
//            if (quotation_item->lastQty > 0) {
//                new_statics_entry.statics_analyzer.reset(new LastQtyStaticsAnalyzer());
//            } else {
//                // 很有可能是初始化行情，但是不确定分析器用哪一种
//                // 先当做Volumn分析，用做初始化行情
//                new_statics_entry.statics_analyzer.reset(new VolumnStaticsAnalyzer());
//            }
//        } else {
            new_statics_entry.statics_analyzer.reset(new VolumnStaticsAnalyzer());
//        }

        new_statics_entry.statics_analyzer->onNewStaticsItem(*quotation_item, *(new_statics_entry.statics_item));
        statics_entry_map_[statics_key] = new_statics_entry;

        APPLOG_DEBUG("create statics key={}, {}", statics_key, *(new_statics_entry.statics_item));
        return ;
    }

//    if (quotation_item->volumn == 0
//         && quotation_item->lastQty != 0
//         && statics_entry_it->second.statics_analyzer->name() == VOLUMN_STATICS_ANALYZER) {
//        statics_entry_it->second.statics_analyzer.reset(new LastQtyStaticsAnalyzer());
//    }

    StaticsEntry& statics_entry = statics_entry_it->second;
    if (minute_timestampS < statics_entry.statics_item->kline_minute_start_timestampS) {
        APPLOG_WARN("unexpected statics, timestamp is small than statics, drop it, key={}", statics_key);
        return ;
    }

    if (minute_timestampS == statics_entry.statics_item->kline_minute_start_timestampS) {
        statics_entry.statics_analyzer->onUpdateStaticsItem(*quotation_item, *(statics_entry.statics_item));
        APPLOG_DEBUG("update statics key={}, {}", statics_key, *(statics_entry.statics_item));
        return ;
    }

    {
        std::shared_ptr<StaticsItem> next_statics_item(createNewStaticsItem(*quotation_item, minute_timestampS));
        statics_entry.statics_analyzer->onNextStaticsItem(*quotation_item
            , *(statics_entry.statics_item)
            , *next_statics_item);
        if (statics_entry.statics_item->kline_qty > 0) {
            storeStaticsItem(*statics_entry.statics_item);
        }

        APPLOG_DEBUG("next statics come, key={}, old statics={}, next statics={}"
                , statics_key
                , *statics_entry.statics_item
                , *next_statics_item);

        statics_entry.statics_item = next_statics_item;
    }

    refreshStaticsItems(statics_key, minute_timestampS);
}

void RaceStaticsQuotationHandler::refreshStaticsItems(const std::string& ext_key, int64_t minute_timestampS) {
    // 新分钟行情到来，刷新所有行情
    APPLOG_WARN("refreshStaticsItems ext_key={}, minute_timestampS={}", ext_key, minute_timestampS);

    for (auto it = statics_entry_map_.begin(); it != statics_entry_map_.end(); ++it) {
        if (it->first == ext_key) {
            continue;
        }

        StaticsEntry& entry = it->second;

        std::shared_ptr<StaticsItem> next_statics_item(new StaticsItem());
        next_statics_item->platform = entry.statics_item->platform;
        next_statics_item->contract_symbols = entry.statics_item->contract_symbols;
        next_statics_item->kline_minute_start_timestampS = minute_timestampS;

        if (!entry.statics_analyzer->onFinishStaticsItem(*entry.statics_item, *next_statics_item)) {
            continue;
        }
        if (entry.statics_item->kline_qty > 0) {
            storeStaticsItem(*entry.statics_item);
        }
        entry.statics_item = next_statics_item;
    }
}

void RaceStaticsQuotationHandler::storeStaticsItem(const StaticsItem& item) {
    static const std::string queue_key = "kline_1";

    APPLOG_WARN("store statics {}", item);

    KLineQuotationMinuteItem minute_item;
    minute_item.__set_platform(item.platform);
    minute_item.__set_contractSymbol(item.contract_symbols);
    minute_item.__set_kMinutePeriod(1);
    minute_item.__set_kMinuteStartTimestampS(item.kline_minute_start_timestampS);

    KLineQuotationDetail detail;
    detail.__set_kLineOpenPrice(item.kline_open_price);
    detail.__set_kLineClosePrice(item.kline_close_price);
    detail.__set_kLineHighPrice(item.kline_high_price);
    detail.__set_kLineLowPrice(item.kline_low_price);
    detail.__set_kLineOpenInterest(item.kline_open_interest);
    detail.__set_kLineQty(item.kline_qty);
    detail.__set_kLineSettlementPrice(item.kline_settlement_turnover/item.kline_qty);

    minute_item.__set_detail(detail);

    apache::thrift::protocol::TCompactProtocolT<apache::thrift::transport::TMemoryBuffer> protocol(write_buffer_);
    minute_item.write(&protocol);

    uint8_t* buf = NULL;
    uint32_t size = 0;
    write_buffer_->getBuffer(&buf, &size);

    std::unique_ptr<char[]> result_buffer(new char[Base64encode_len((int)size) + 1]);
    int result_size = Base64encode(result_buffer.get(), (const char*)buf, (int)size);
    CHECK(result_size >= 0);

    result_buffer[result_size] = '\0';
    write_buffer_->resetBuffer();

    attr_reporter_.average(attr_store_statics_memcached_send_item_size_, result_size);

    while(true) {
        if (ending_) {
            return ;
        }

        std::chrono::high_resolution_clock::time_point start = std::chrono::high_resolution_clock::now();
        attr_reporter_.inc(attr_store_statics_memcacheq_send_total_count_key_, 1);
        if (storeMemcacheq(queue_key, item, result_buffer.get(), result_size)) {
            attr_reporter_.average(attr_store_statics_memcached_send_timens_key_
                                , std::chrono::duration_cast<std::chrono::nanoseconds>(
                                        std::chrono::high_resolution_clock::now() - start).count());
            break;
        } else {
            attr_reporter_.inc(attr_store_statics_memcacheq_send_failed_count_key_, 1);
            std::this_thread::sleep_for(std::chrono::milliseconds(100));
            continue;
        }
    }
}

bool RaceStaticsQuotationHandler::storeMemcacheq(const std::string& queue_key
            , const StaticsItem& item
            , const char* itemstr
            , int item_size) {
    memcached_return rc = memcached_set(mc_
               , queue_key.c_str()
               , queue_key.length()
               , itemstr
               , item_size
               , 0
               , 0);
    if (rc != MEMCACHED_SUCCESS) {
        APPLOG_ERROR("store memcacheq statics platform={} contractSymbols={} minute_timestamp={} queue_key={} size={} failed, rc={} errormsg={}"
                    , item.platform
                    , item.contract_symbols
                    , item.kline_minute_start_timestampS
                    , queue_key
                    , item_size
                    , rc
                    , memcached_strerror(mc_, rc));
            return false;
    }

    APPLOG_DEBUG("store memcacheq statics platform={} contractSymbols={} minute_timestamp={} queue_key={} success!"
                , item.platform
                , item.contract_symbols
                , item.kline_minute_start_timestampS
                , queue_key);

    return true;
}





} // end namespace quotation
} // end namespace xueqiao


