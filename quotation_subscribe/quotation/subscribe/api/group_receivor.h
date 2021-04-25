/*
 * group_receivor.h
 *
 *  Created on: 2017年4月3日
 *      Author: 44385
 */

#ifndef QUOTATION_SUBSCRIBE_GROUP_RECEIVOR_H_
#define QUOTATION_SUBSCRIBE_GROUP_RECEIVOR_H_

#include "quotation_common/thrift/quotation_item_types.h"
#include "group_config.h"
#include "quotation/subscribe/api/topic.h"
#include "zmq.hpp"

namespace xueqiao {
namespace quotation {

class GroupReceivor {
public:
    class ICallback {
    public:
        virtual ~ICallback() = default;

        virtual void onReceivedItemData(
                const Topic& topic
                , const void* p_data
                , size_t size) noexcept = 0;

    protected:
        ICallback() = default;
    };

    GroupReceivor(
            const std::shared_ptr<GroupConfig>& group_config
            , const std::string& group_name
            , const std::map<Topic, bool>& init_topics
            , ICallback* callback);
    ~GroupReceivor();

    void onGroupItemConfigChanged();
    void addTopic(const Topic& topic);
    void removeTopic(const Topic& topic);
    std::string master();

private:
    void onWork();
    void onRefreshConnections();
    void onCall();
    void onProcess();
    void end();

    void sendCallResp();

    std::shared_ptr<GroupConfig> group_config_;
    const std::string group_name_;
    std::stringstream call_socket_description_;
    ICallback* callback_;
    std::unique_ptr<zmq::context_t> group_context_;

    std::atomic_bool ending_ = { false };
    std::map<std::string, bool> subscribe_topics_;

    std::unique_ptr<std::thread> receive_thread_;
    std::unique_ptr<zmq::socket_t> receive_socket_;
    std::string receive_connection_desc_;
    std::unique_ptr<zmq::socket_t> call_socket_;
};

} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_SUBSCRIBE_GROUP_RECEIVOR_H_ */
