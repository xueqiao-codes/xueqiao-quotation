/*
 * race_publish_quotation_handler.cpp
 *
 *  Created on: 2017年3月11日
 *      Author: 44385
 */
#include "quotation/race/server/race_publish_quotation_handler.h"

#include <boost/lexical_cast.hpp>
#include <sstream>

#include "base/code_defense.h"
#include "thrift/protocol/TCompactProtocol.h"
#include "thrift/protocol/TJSONProtocol.h"
#include "qconf.h"
#include "zmq.h"

namespace xueqiao {
namespace quotation {

RacePublishQuotationHandler::RacePublishQuotationHandler(void* publish_socket)
	: publish_socket_(publish_socket)
	  , write_buffer_(new apache::thrift::transport::TMemoryBuffer(10*1024)) {
	CHECK(publish_socket_ != NULL);
}

RacePublishQuotationHandler::~RacePublishQuotationHandler() {

}

void RacePublishQuotationHandler::handleQuotation(const std::shared_ptr<QuotationItem>& item) noexcept {
    apache::thrift::protocol::TCompactProtocolT<apache::thrift::transport::TMemoryBuffer>
        protocol(write_buffer_);
    item->write(&protocol);

    uint8_t* buf = NULL;
    uint32_t size = 0;
    write_buffer_->getBuffer(&buf, &size);

    std::stringstream origin_topic;
    origin_topic << "/quotation/" << item->platform
            << "/" << item->contractSymbol << "/";
    zmq_send(publish_socket_, origin_topic.str().c_str(), origin_topic.str().length(), ZMQ_SNDMORE);
    zmq_send(publish_socket_, buf, (size_t)size, 0);

    write_buffer_->resetBuffer();
}

} // end namespace quotation
} // end namespace xueqiao



