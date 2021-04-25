/*
 * group_receivor.cpp
 *
 *  Created on: 2017年4月3日
 *      Author: 44385
 */
#include "quotation/subscribe/api/group_receivor.h"

#include "base/app_log.h"
#include "zmq_addon.hpp"
#include "thrift/protocol/TCompactProtocol.h"
#include "thrift/transport/TBufferTransports.h"

namespace xueqiao {
namespace quotation {

static std::atomic_int s_instance_count = {0};

static std::string CONFIG_CHANGED_CALL = "config_changed";
static std::string ADD_TOPIC_CALL = "add_topic";
static std::string REMOVE_TOPIC_CALL = "remove_topic";
static std::string END_CALL = "end";
static std::string REP_SUCCESS = "success";

GroupReceivor::GroupReceivor(const std::shared_ptr<GroupConfig>& group_config
        , const std::string& group_name
        , const std::map<Topic, bool>& init_topics
        , ICallback* callback)
    : group_config_(group_config)
     , group_name_(group_name)
     , callback_(callback) {
    call_socket_description_<< "inproc://#" << group_name_ << "_" << s_instance_count.fetch_add(1);
    group_context_.reset(new zmq::context_t(2));
    for (auto& init_pair : init_topics) {
        subscribe_topics_[init_pair.first.description()] = true;
    }
    receive_thread_.reset(new std::thread(&GroupReceivor::onWork, this));
}

GroupReceivor::~GroupReceivor() {
    end();
    receive_thread_->join();
}

void GroupReceivor::end() {
    ending_ = true;

    zmq::socket_t socket(*group_context_, zmq::socket_type::req);
    socket.connect(call_socket_description_.str());
    zmq::multipart_t req;
    req.addstr(END_CALL);
    req.send(socket);

//    zmq::message_t resp;
//    socket.recv(&resp);
}

void GroupReceivor::onGroupItemConfigChanged() {
    zmq::socket_t socket(*group_context_, zmq::socket_type::req);
    socket.connect(call_socket_description_.str());
    zmq::multipart_t req;
    req.addstr(CONFIG_CHANGED_CALL);
    req.send(socket);

    zmq::message_t resp;
    socket.recv(&resp);
}

void GroupReceivor::addTopic(const Topic& topic) {
    zmq::socket_t socket(*group_context_, zmq::socket_type::req);
    socket.connect(call_socket_description_.str());

    zmq::multipart_t req;
    req.addstr(ADD_TOPIC_CALL);
    req.addstr(topic.Platform());
    req.addstr(topic.contractSymbol());
    req.send(socket);

    zmq::message_t resp;
    socket.recv(&resp);
}

void GroupReceivor::removeTopic(const Topic& topic) {
    zmq::socket_t socket(*group_context_, zmq::socket_type::req);
    socket.connect(call_socket_description_.str());

    zmq::multipart_t req;
    req.addstr(REMOVE_TOPIC_CALL);
    req.addstr(topic.Platform());
    req.addstr(topic.contractSymbol());
    req.send(socket);

    zmq::message_t resp;
    socket.recv(&resp);
}

void GroupReceivor::onCall() {
    zmq::multipart_t msg;
    while(msg.recv(*call_socket_, ZMQ_DONTWAIT)) {
        std::string call_type = msg.popstr();
        if (call_type == ADD_TOPIC_CALL) {
            Topic add_topic;
            add_topic.setPlatform(msg.popstr());
            add_topic.setContractSymbol(msg.popstr());

            auto it = subscribe_topics_.find(add_topic.description());
            if (it != subscribe_topics_.end()) {
                sendCallResp();
                continue;
            }

            std::string topic_description = add_topic.description();
            subscribe_topics_[topic_description] = true;
            if (receive_socket_) {
                receive_socket_->setsockopt(ZMQ_SUBSCRIBE, topic_description.c_str(), topic_description.length());
            }

            APPLOG_INFO("{} add topic {}", group_name_, topic_description);
        } else if (call_type == REMOVE_TOPIC_CALL) {
            Topic remove_topic;
            remove_topic.setPlatform(msg.popstr());
            remove_topic.setContractSymbol(msg.popstr());

            auto it = subscribe_topics_.find(remove_topic.description());
            if (it == subscribe_topics_.end()) {
                sendCallResp();
                continue;
            }

            std::string topic_description = remove_topic.description();
            subscribe_topics_.erase(it);
            if (receive_socket_) {
                receive_socket_->setsockopt(ZMQ_UNSUBSCRIBE, topic_description.c_str(), topic_description.length());
            }
            APPLOG_INFO("{} remove topic {}", group_name_, topic_description);

        } else if (call_type == CONFIG_CHANGED_CALL) {
            onRefreshConnections();
        }

        sendCallResp();

        if (call_type == END_CALL) {
            APPLOG_INFO("{} end...", group_name_);
            break;
        }
    }
}

void GroupReceivor::onProcess() {
    zmq::multipart_t msg;
    int received_count = 0;
    while(received_count < 100) {
        if (!msg.recv(*receive_socket_, ZMQ_DONTWAIT)) {
            break;
        }

        ++received_count;
        if (msg.size() != 2) {
            continue;
        }

        Topic topic;
        topic.from(msg.popstr());
        zmq::message_t content_msg = msg.pop();

        if (callback_ == nullptr) {
            continue;
        }

        callback_->onReceivedItemData(topic, content_msg.data(), content_msg.size());
    }
}

void GroupReceivor::onRefreshConnections() {
    std::shared_ptr<std::vector<GroupConfig::GroupItem>> group_config_items
            = group_config_->getGroups();
    std::unique_ptr<GroupConfig::GroupItem> match_group_item;
    for (auto& item : *group_config_items) {
        if (item.name == group_name_) {
            match_group_item.reset(new GroupConfig::GroupItem(item));
            break;
        }
    }
    if (!match_group_item) {
        APPLOG_ERROR("{} can not found group configuration", group_name_);
        return ;
    }

    std::stringstream new_connection_desc_ss;
    for (auto& host : *(match_group_item->hosts)) {
        if (match_group_item->master->host_id == host.host_id) {
            new_connection_desc_ss << "tcp://" << host.host_addr << ":" << host.backend;
            break;
        }
    }
    std::string new_connection_desc = new_connection_desc_ss.str();
    if (new_connection_desc.empty()) {
        APPLOG_ERROR("{} configuration error, can not found master", group_name_);
        return ;
    }

    if (new_connection_desc == receive_connection_desc_) {
        APPLOG_INFO("{} configuration not changed!", group_name_);
        return ;
    }

    receive_connection_desc_ = new_connection_desc;
    receive_socket_.reset(new zmq::socket_t(*group_context_, zmq::socket_type::sub));
    receive_socket_->setsockopt(ZMQ_HEARTBEAT_IVL, 15000);
    receive_socket_->setsockopt(ZMQ_HEARTBEAT_TIMEOUT, 3000);
    receive_socket_->setsockopt(ZMQ_HEARTBEAT_TTL, 60000);
    for (auto& pair : subscribe_topics_) {
        receive_socket_->setsockopt(ZMQ_SUBSCRIBE, pair.first.c_str(), pair.first.length());
    }
    receive_socket_->setsockopt(ZMQ_RCVHWM, 100);
    receive_socket_->connect(receive_connection_desc_);
    APPLOG_INFO("{} switch connection to {}", group_name_, receive_connection_desc_);
}

void GroupReceivor::onWork() {
    call_socket_.reset(new zmq::socket_t(*group_context_, zmq::socket_type::rep));
    call_socket_->bind(call_socket_description_.str());

    onRefreshConnections();

    while(!ending_) {
        int ret = 0;

        zmq_pollitem_t items[2];
        items[0].socket = (void*)(*call_socket_);
        items[0].events = ZMQ_POLLIN;
        items[0].revents = 0;

        if (receive_socket_) {
            items[1].socket = (void*)(*receive_socket_);
            items[1].events = ZMQ_POLLIN;
            items[1].revents = 0;
            ret = zmq_poll(items, 2, -1);
        } else {
            items[1].socket = nullptr;
            items[1].events = 0;
            items[1].revents = 0;
            ret = zmq_poll(items, 1, -1);
        }

        if (ret <= 0) {
            if (ret < 0) {
                APPLOG_ERROR("{} poll sockets failed, err{} : {}", group_name_.c_str()
                        , zmq_errno(), zmq_strerror(zmq_errno()));
            }
            continue;
        }

        if (items[0].revents > 0) {
            onCall();
            if (ending_) {
                break;
            }
        }
        if (items[1].socket != nullptr && items[1].revents > 0) {
            onProcess();
        }
    }

    receive_socket_.reset();
    call_socket_.reset();
}

void GroupReceivor::sendCallResp() {
    zmq::message_t resp(REP_SUCCESS.c_str(), REP_SUCCESS.length());
    call_socket_->send(resp);
}


} // end namespace quotation
} // end namespace xueqiao



