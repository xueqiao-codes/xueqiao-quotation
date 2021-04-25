/*
 * main.cc
 *
 *  Created on: 2017年3月11日
 *      Author: 44385
 */
#include <atomic>
#include <boost/program_options.hpp>
#include <iostream>

#include "attr/attr_reporter_factory.h"
#include "base/app_log.h"

#include "zmq.hpp"
#include "zmq_addon.hpp"
#include "quotation/race/server/race_processor.h"
#include "quotation/race/server/race_publish_quotation_handler.h"
#include "quotation/race/server/race_statics_quotation_handler.h"
#include "quotation/race/server/race_store_memcacheq_quotation_handler.h"
#include "quotation/race/server/race_quotation_chooser_impl.h"
#include "thrift/protocol/TCompactProtocol.h"
#include "qconf.h"

namespace po = boost::program_options;



using namespace xueqiao::quotation;
using namespace soldier::attr;

std::atomic_bool HEART_BEAT_FLAG = {false};
const std::string HEART_BEAT_TOPIC = "#heartbeat#";

void process(
		int cpu_cores
		, int receive_hwm
		, int send_hwm
		, int frontend_port
		, int backend_port
		, const std::string& top_topic
		) {

	zmq::context_t context((cpu_cores >= 2)  ? (cpu_cores - 1) : 1);
	zmq::socket_t race_subscribe_socket(context, ZMQ_SUB);
	std::stringstream bind_desc;
	bind_desc << "tcp://*:" << frontend_port;
	race_subscribe_socket.setsockopt(ZMQ_RCVHWM, receive_hwm);
	race_subscribe_socket.bind(bind_desc.str());
	std::cout << "race_subscribe_socket bind " << bind_desc.str() << std::endl;
	race_subscribe_socket.setsockopt(ZMQ_SUBSCRIBE, top_topic.c_str(), top_topic.length());

	zmq::socket_t race_publish_socket(context, ZMQ_PUB);
	bind_desc.str("");
	bind_desc << "tcp://*:" << backend_port;
	race_publish_socket.setsockopt(ZMQ_SNDHWM, send_hwm);
	race_publish_socket.bind(bind_desc.str());
	std::cout << "race_publish_socket bind " << bind_desc.str() << std::endl;

	std::shared_ptr<IRaceQuotationChooser> race_quotation_chooser(new RaceQuotationChooserImpl());
	std::unique_ptr<LinkedRaceQuotationHandler> linked_race_quotation_handler(new LinkedRaceQuotationHandler());
	linked_race_quotation_handler->addHandler(
			std::shared_ptr<xueqiao::quotation::IRaceQuotationHandler>(
					new RacePublishQuotationHandler((void*)race_publish_socket)));
	linked_race_quotation_handler->addHandler(
	        std::shared_ptr<xueqiao::quotation::IRaceQuotationHandler>(
	                new RaceStaticsQuotationHandler())
	        );
	linked_race_quotation_handler->addHandler(
	        std::shared_ptr<xueqiao::quotation::IRaceQuotationHandler>(
	                new RaceStoreMemcacheqQuotationHandler()));
	std::shared_ptr<RaceProcessor> race_processor(new RaceProcessor(
			race_quotation_chooser
			, std::shared_ptr<IRaceQuotationHandler>(linked_race_quotation_handler.release())));

	IAttrReporter& reporter = AttrReporterFactory::Global().thirtySecs();
    reporter.keep(reporter.requireKey("quotation.race.server.keepalive", {}), 1);

	std::map<std::string, int64_t> received_count_id_map;

	bool heartbeat_subscribe = true;

	while(true) {
	    if (HEART_BEAT_FLAG) {
	        std::chrono::high_resolution_clock::time_point t1 = std::chrono::high_resolution_clock::now();
	        if (heartbeat_subscribe) {
	            race_subscribe_socket.setsockopt(ZMQ_SUBSCRIBE, HEART_BEAT_TOPIC.c_str(), HEART_BEAT_TOPIC.length());
	            heartbeat_subscribe = false;
	        } else {
	            race_subscribe_socket.setsockopt(ZMQ_UNSUBSCRIBE, HEART_BEAT_TOPIC.c_str(), HEART_BEAT_TOPIC.length());
	            heartbeat_subscribe = true;
	        }

	        APPLOG_WARN("HeartBeat..., next heartbeat subscribe={}, escaped={}ns"
	                , heartbeat_subscribe
	                , std::chrono::duration_cast<std::chrono::nanoseconds>(
	                        std::chrono::high_resolution_clock::now() - t1).count());
	        HEART_BEAT_FLAG = false;
	    }

		zmq::multipart_t multipart_msg;
		if (!multipart_msg.recv(race_subscribe_socket)) {
			continue ;
		}

		/**
		 *  Frame1: topic
		 *  Frame2: group_name
		 *  Frame3: content
		 */
		if (multipart_msg.size() != 3) {
			APPLOG_ERROR("recived msg is not 3 frame msg");
			continue;
		}

		zmq::message_t& topic_msg = multipart_msg.at(0);
		if (topic_msg.size() < top_topic.size()) {
			APPLOG_WARN("received not {} topic message, maybe some error in program", top_topic);
			continue ;
		}

		zmq::message_t& group_msg = multipart_msg.at(1);
		std::string group_name((const char*)group_msg.data(), group_msg.size());

		INC_METRIC(RECEIVED, reporter, "quotation.race.received", "group_name", group_name, 1)

		zmq::message_t& content_msg = multipart_msg.at(2);

		boost::shared_ptr<apache::thrift::transport::TMemoryBuffer> read_buffer(
				new apache::thrift::transport::TMemoryBuffer(
						(uint8_t*)content_msg.data(), content_msg.size(),
						apache::thrift::transport::TMemoryBuffer::MemoryPolicy::OBSERVE));

		apache::thrift::protocol::TCompactProtocolT<apache::thrift::transport::TMemoryBuffer>
				protocol(read_buffer);

		std::shared_ptr<QuotationItem> item(new QuotationItem());
		try {
			item->read(&protocol);
		} catch (apache::thrift::TException& e) {
			APPLOG_ERROR("unserialize quotation item failed! {}", e.what());
			continue;
		}
		race_processor->processQuotationItem(item);
	}

}

void HeartBeat() {
    while(true) {
        std::this_thread::sleep_for(std::chrono::seconds(300));
        HEART_BEAT_FLAG = true;
    }
}

int main(int argc, char* argv[]) {
	soldier::base::AppLog::Init("apps/quotation_race_server");

	po::options_description desc("Allowed options");
	desc.add_options()
	        ("help", "produce help message")
	        ("frontend", po::value<int>(), "listen port from recv race quotations, default is 1555")
			("backend", po::value<int>(), "listen port for subscribe quotations, default is 1556");

	po::variables_map vm;
	po::store(po::parse_command_line(argc, argv, desc), vm);
	po::notify(vm);

	if(vm.count("help")){
		std::cout<< desc << std::endl;
		return 0;
	}

	int receive_hwm = 100;
	int send_hwm = 100;
	int frontend_port = 1555;
	int backend_port = 1556;
	std::string top_topic = "/quotation";
	if(vm.count("frontend")) {
		frontend_port = vm["frontend"].as<int>();
	}
	if (vm.count("backend")) {
		backend_port = vm["backend"].as<int>();
	}

	int ret = qconf_init();
	if (ret != QCONF_OK) {
	    std::cerr << "qconf init failed, err = " << ret << std::endl;
	    return -1;
	}

	unsigned int cpu_cores = std::thread::hardware_concurrency();
	std::cout << "frontend=" << frontend_port << ", backend=" << backend_port
			  << ", receive hwm=" << receive_hwm
			  << ", send_hwm=" << send_hwm
			  << ", cpu_cores=" << cpu_cores
			  << ", top_topic=" << top_topic
			  << std::endl;

	std::thread heartbeat_thread(&HeartBeat);
	heartbeat_thread.detach();
	try {
		process(cpu_cores, receive_hwm, send_hwm, frontend_port, backend_port, top_topic);
	} catch (zmq::error_t& zmqerr) {
		APPLOG_ERROR("zeromq throws error, {}", zmqerr.what());
		qconf_destroy();
		return -1;
	}

	qconf_destroy();
	return 0;
}
