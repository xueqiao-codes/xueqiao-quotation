/*
 * race_client_impl.cpp
 *
 *  Created on: 2017年3月12日
 *      Author: 44385
 */
#include "race_client_impl.h"

#include <algorithm>
#include "base/code_defense.h"
#include "base/time_helper.h"
#include "base/net_helper.h"
#include "base/os_helper.h"
#include "thrift/protocol/TCompactProtocol.h"

namespace xueqiao {
namespace quotation {

bool RaceClientImpl::checkConfig(const std::shared_ptr<RaceHostConfig>& master_host
        , const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists) {
    if (master_host.get() == nullptr) {
        return false;
    }
    if (race_host_lists.get() == nullptr) {
        return false;
    }
    auto it = std::find(race_host_lists->begin(), race_host_lists->end(), *master_host);
    if (it == race_host_lists->end()) {
        return false;
    }
    return true;
}

RaceClientImpl::RaceClientImpl(
		const std::string& group_name
		, const std::shared_ptr<zmq::context_t>& context
		, const std::shared_ptr<RaceHostConfig>& master_host
		, const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists
		, int hwm)
	: group_name_(group_name)
	  , context_(context)
	  , normal_priority_queued_items_(1000)
	  , high_priority_queue_items_(100)
      , reporter_(soldier::attr::AttrReporterFactory::Global().thirtySecs())
	  , end_working_(false)
	  , working_thread_(&RaceClientImpl::onWorking, this, master_host, race_host_lists)
      , hwm_(hwm) {
	CHECK(context_.get() != nullptr);

	pid_ = (int16_t)getpid();
	CHECK(pid_ > 0);
	host_name_ = soldier::base::getHostName();
	CHECK(host_name_.length() > 0);

	process_name_ = soldier::base::OSHelper::getProcessName();
	CHECK(process_name_.length() > 0);
}

RaceClientImpl::~RaceClientImpl() {
	end_working_ = true;
	working_thread_.join();

	QueuedItem* item = nullptr;
	while(normal_priority_queued_items_.pop(item)) {
		if (item != nullptr) {
			delete item;
			item = nullptr;
		}
	}
	while(high_priority_queue_items_.pop(item)) {
		if (item != nullptr) {
			delete item;
			item = nullptr;
		}
	}

	APPLOG_DEBUG("Destroy group({}) instance({:x})", group_name_, (int64_t)this);
}

void RaceClientImpl::clusterConfigChanged(
        const std::shared_ptr<RaceHostConfig>& master_host
        , const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists) noexcept {
	std::unique_ptr<QueuedItem> update_config_queued_item(new QueuedItem(master_host, race_host_lists));
	while(true) {
		if (high_priority_queue_items_.push(update_config_queued_item.get())) {
			update_config_queued_item.release();
			break;
		}
	}
}

void RaceClientImpl::send2Race(const std::shared_ptr<QuotationItem>& item) noexcept {
    item->__set_receivedProcessId(pid_);
    item->__set_receivedHostName(host_name_);
    item->__set_receivedTimestampMs(soldier::base::NowInMilliSeconds());

	static thread_local boost::shared_ptr<apache::thrift::transport::TMemoryBuffer>  write_buffer;
	if (write_buffer.get() == nullptr) {
	    write_buffer.reset(new apache::thrift::transport::TMemoryBuffer(10*1024));
	} else {
	    write_buffer->resetBuffer();
	}

	apache::thrift::protocol::TCompactProtocolT<apache::thrift::transport::TMemoryBuffer> protocol(write_buffer);
	try {
		item->write(&protocol);
	} catch (apache::thrift::TException& e) {
		APPLOG_ERROR("serialize item failed, {}", e.what());
		return ;
	}

	std::shared_ptr<zmq::multipart_t> send_msg(new zmq::multipart_t());
	std::string topic;
	topic.append("/quotation")
		 .append("/")
		 .append(item->platform)
		 .append("/")
		 .append(item->contractSymbol)
	     .append("/");
	send_msg->addstr(topic);
	send_msg->addstr(group_name_);
	uint8_t* content = nullptr;
	uint32_t size = 0;
	write_buffer->getBuffer(&content, &size);
	send_msg->addmem(content, (size_t)size);

	std::unique_ptr<QueuedItem> quotation_queued_item(new QueuedItem(send_msg));
	if (!normal_priority_queued_items_.bounded_push(quotation_queued_item.get())) {
	    static thread_local std::string key_drop_count;
	    if (key_drop_count.empty()) {
	        key_drop_count = "quotation.client.";
	        key_drop_count.append(process_name_);
	        key_drop_count.append(".drop.count");
	    }
	    INC_METRIC(DROP, reporter_, key_drop_count, "group_name", group_name_, 1)
	} else {
		quotation_queued_item.release();
	}

	static thread_local std::string key_msg_size;
	if (key_msg_size.empty()) {
	    key_msg_size = "quotation.client.";
	    key_msg_size.append(process_name_);
	    key_msg_size.append(".msg.size");
	}
	AVERAGE_METRIC(SIZE, reporter_, key_msg_size, "group_name", group_name_, (int64_t)size)
}

void RaceClientImpl::sendQuotation2Cluster(const std::shared_ptr<zmq::multipart_t>& item) noexcept {
	std::chrono::high_resolution_clock::time_point start = std::chrono::high_resolution_clock::now();

	if (master_host_.get() != nullptr) {
	   auto master_socket_it = cluster_sockets_.find(*master_host_);
	   if (master_socket_it != cluster_sockets_.end()) {
	       item->send(*(master_socket_it->second));
	   } else {
	       APPLOG_ERROR("can not found zmq socket for {} in group({})", master_host_->urlDesc(), group_name_);
	   }
	}

	std::chrono::high_resolution_clock::time_point send_msg_end = std::chrono::high_resolution_clock::now();

	static thread_local std::string key_send_timens;
	if (key_send_timens.empty()) {
	    key_send_timens = "quotation.client.";
	    key_send_timens.append(process_name_);
	    key_send_timens.append(".send.timens");
	}
	AVERAGE_METRIC(TIME, reporter_, key_send_timens, "group_name", group_name_
	        , std::chrono::duration_cast<std::chrono::nanoseconds>(send_msg_end - start).count())
}

void RaceClientImpl::refreshConnections(
        const std::shared_ptr<RaceHostConfig>& master_host
        , const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists) {
    if (!checkConfig(master_host, race_host_lists)) {
        APPLOG_ERROR("{} has unexpected host config, should check! master is not in host lists", group_name_);
        reporter_.inc(
                reporter_.requireKey("quotation.client" + std::string(process_name_) +  ".config.error"
                        , {{"group_name", group_name_}})
                , 1
            );
        return ;
    }

	// clean connections remove from group
	for (auto iter = cluster_sockets_.begin(); iter != cluster_sockets_.end(); ) {
		if (std::find(race_host_lists->begin(), race_host_lists->end(), iter->first)
				== race_host_lists->end()) {
			APPLOG_INFO("group({}) remove connections to {}", group_name_,  iter->first.urlDesc());
			iter = cluster_sockets_.erase(iter);
		} else {
			++iter;
		}
	}

	for (auto& host_config : *race_host_lists) {
		if (cluster_sockets_.find(host_config) != cluster_sockets_.end()) {
			continue;
		}

		std::shared_ptr<zmq::socket_t> new_connection_socket(
				new zmq::socket_t(*context_, ZMQ_PUB));
		new_connection_socket->setsockopt(ZMQ_SNDHWM, hwm_);
		new_connection_socket->connect("tcp://" + host_config.urlDesc());
		cluster_sockets_.insert(
				std::pair<RaceHostConfig, std::shared_ptr<zmq::socket_t>>(host_config, new_connection_socket));
		APPLOG_INFO("group({}) add connections to {}", group_name_, host_config.urlDesc());
	}

	master_host_ = master_host;
	APPLOG_INFO("group({}) master is {}", group_name_, master_host_->urlDesc());
}

void RaceClientImpl::onWorking(const std::shared_ptr<RaceHostConfig>& master_host, const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists) {
    refreshConnections(master_host, race_host_lists);

	while(!bool(end_working_)) {
		QueuedItem* item = nullptr;

		if (!high_priority_queue_items_.empty()) {
			high_priority_queue_items_.pop(item);
		} else {
			if (!normal_priority_queued_items_.pop(item)) {
				std::this_thread::sleep_for(std::chrono::microseconds(100));
				continue;
			}
		}
		if (item == nullptr) {
			APPLOG_WARN("{}, Get null QueuedItem", group_name_);
			continue;
		}

		std::unique_ptr<QueuedItem> queued_item(item);
		if (queued_item->getType() == QueuedItem::QueuedItemType::CONFIG_UPDATE) {
			refreshConnections(queued_item->getMasterHost(), queued_item->getRaceHostLists());
		} else if (queued_item->getType() == QueuedItem::QueuedItemType::MSG_ITEM) {
		    static thread_local std::string key_worked_count;
		    if (key_worked_count.empty()) {
		        key_worked_count = "quotation.client.";
		        key_worked_count.append(process_name_);
		        key_worked_count.append(".queued.worked.count");
		    }
		    INC_METRIC(WORKED, reporter_, key_worked_count, "group_name", group_name_, 1)
			sendQuotation2Cluster(queued_item->getMsgItem());
		} else {
			APPLOG_ERROR("{}, GET Unexpected QueuedItem, type={}", group_name_, (int)item->getType());
		}
		queued_item.reset();
	}

	for (auto& cluster_socket_pair : cluster_sockets_) {
		APPLOG_INFO("group({}) remove connections to {}", group_name_,  cluster_socket_pair.first.urlDesc());
	}
	cluster_sockets_.clear();
}



} // end namespace quotation
} // end namespace xueqiao



