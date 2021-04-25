/*
 * race_client_impl.h
 *
 *  Created on: 2017年3月12日
 *      Author: 44385
 */

#ifndef QUOTATION_RACE_CLIENT_RACE_CLIENT_IMPL_H_
#define QUOTATION_RACE_CLIENT_RACE_CLIENT_IMPL_H_

#include <boost/lockfree/queue.hpp>
#include <boost/shared_ptr.hpp>
#include <memory>
#include <thread>

#include "attr/attr_reporter_factory.h"
#include "base/code_defense.h"
#include "zmq.hpp"
#include "zmq_addon.hpp"
#include "thrift/transport/TBufferTransports.h"
#include "race_client.h"
#include "race_cluster_config.h"


namespace xueqiao {
namespace quotation {

class RaceClientImpl : public IRaceClient {
public:
	RaceClientImpl(
			const std::string& group_name
			, const std::shared_ptr<zmq::context_t>& context
			, const std::shared_ptr<RaceHostConfig>& master_host
			, const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists
			, int hwm = 100);
	virtual ~RaceClientImpl();

	void clusterConfigChanged(
	        const std::shared_ptr<RaceHostConfig>& master_host
	        , const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists) noexcept;
	virtual void send2Race(const std::shared_ptr<QuotationItem>& item) noexcept;

private:
	void onWorking(const std::shared_ptr<RaceHostConfig>& master_host
	        , const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists);
	void refreshConnections(
	        const std::shared_ptr<RaceHostConfig>& master_host
	        , const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists);
	void sendQuotation2Cluster(const std::shared_ptr<zmq::multipart_t>& item) noexcept;

	bool checkConfig(const std::shared_ptr<RaceHostConfig>& master_host
	        , const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists);

	class QueuedItem {
		public:
			enum QueuedItemType{
				MSG_ITEM,
				CONFIG_UPDATE,
				HMW_UPDATE,
				UNKOWN
			};

			~QueuedItem() {
			}

			QueuedItem(const std::shared_ptr<zmq::multipart_t>& msg_item)
				: type_(MSG_ITEM), msg_item_(msg_item) {
			}

			QueuedItem(
			        const std::shared_ptr<RaceHostConfig>& master_host
			        , const std::shared_ptr<std::vector<RaceHostConfig>>& race_host_lists)
				: type_(CONFIG_UPDATE)
			      , master_host_(master_host)
				  , race_host_lists_(race_host_lists) {
			}

			QueuedItem(const QueuedItem&) = delete;
			QueuedItem& operator=(const QueuedItem&) = delete;

			QueuedItemType getType() const { return type_;}

			const std::shared_ptr<zmq::multipart_t>& getMsgItem() const { return msg_item_; }
			const std::shared_ptr<RaceHostConfig>& getMasterHost() const { return master_host_; }
			const std::shared_ptr<std::vector<RaceHostConfig>>& getRaceHostLists() const { return race_host_lists_; }

		private:
			QueuedItemType type_;
			std::shared_ptr<zmq::multipart_t> msg_item_;
			std::shared_ptr<RaceHostConfig> master_host_;
			std::shared_ptr<std::vector<RaceHostConfig>> race_host_lists_;
	};

	const std::string group_name_;
	std::shared_ptr<zmq::context_t> context_;

	boost::lockfree::queue<QueuedItem*> normal_priority_queued_items_;
	boost::lockfree::queue<QueuedItem*> high_priority_queue_items_;

	soldier::attr::IAttrReporter& reporter_;

	std::atomic<bool> end_working_;
	std::thread working_thread_;

	std::map<RaceHostConfig, std::shared_ptr<zmq::socket_t>> cluster_sockets_;
	std::shared_ptr<RaceHostConfig> master_host_;
	std::atomic_int hwm_;

	int16_t pid_ = -1;
	std::string host_name_;
	std::string process_name_;
};


} // end namespace quotation
} // end namespace xueqiao




#endif /* QUOTATION_RACE_CLIENT_RACE_CLIENT_IMPL_H_ */
