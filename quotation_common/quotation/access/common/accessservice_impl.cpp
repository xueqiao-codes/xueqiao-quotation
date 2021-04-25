/*
 * accessservice_impl.cpp
 *
 *  Created on: 2018年12月23日
 *      Author: 44385
 */

#include "accessservice_impl.h"

#include <boost/filesystem.hpp>
#include <boost/lexical_cast.hpp>

#include "accessstate_holder.h"
#include "base/code_defense.h"
#include "effective_reporter.h"
#include "thrift/concurrency/PosixThreadFactory.h"
#include "thrift/protocol/TCompactProtocol.h"
#include "thrift/server/TThreadPoolServer.h"
#include "thrift/transport/TServerSocket.h"
#include "thrift/transport/TBufferTransports.h"
#include "thrift/TProcessor.h"


using namespace platform::comm;
using namespace xueqiao::quotation::access;
using namespace apache::thrift;
using namespace apache::thrift::concurrency;
using namespace apache::thrift::protocol;
using namespace apache::thrift::server;
using namespace apache::thrift::transport;


QuotationAccessServiceHandler::QuotationAccessServiceHandler(const std::shared_ptr<IHeartBeatRunner>& runner)
    : runner_(runner) {
    CHECK(runner_);
}

int64_t QuotationAccessServiceHandler::getLastUpsideEffectiveTimestamp(const PlatformArgs& platformArgs) {
    return EffectiveReporter::Global().getLastEffetiveTimestamp();
}


void QuotationAccessServiceHandler::sendUpsideHeartBeat(const PlatformArgs& platformArgs) {
    if (EffectiveReporter::Global().isAccountInfoInvalid()) {
        APPLOG_INFO("sendUpsideHeartBeat account info invalid, heartbeat success!");
        EffectiveReporter::Global().setEffective();
                return ;
    }
    runner_->sendUpsideHeartBeat();
}

void QuotationAccessServiceHandler::getAccessState(QuotationAccessState& _return, const PlatformArgs& platformArgs) {
    QuotationAccessStateHolder::Global().getAccessState(_return);
}

static std::string getSockFile(const int64_t& quotation_account_id) {
    std::string socket_file;
    socket_file.append("/data/run/quotation_access_")
                       .append(boost::lexical_cast<std::string>(quotation_account_id))
                       .append(".sock");
    return socket_file;
}

std::shared_ptr<QuotationAccessServiceStub> QuotationAccessServiceImpl::getStub(
        const int64_t& quotation_account_id) {
    std::shared_ptr<QuotationAccessServiceStub> stub(new QuotationAccessServiceStub());
    stub->setSocketFile(getSockFile(quotation_account_id));
    return stub;
}


void QuotationAccessServiceImpl::runLoop(
        const int64_t& quotation_account_id, const std::shared_ptr<IHeartBeatRunner>& runner) {
    std::string socket_file = getSockFile(quotation_account_id);
    if (boost::filesystem::exists(socket_file)) {
        CHECK(boost::filesystem::remove(socket_file));
    } else {
        boost::filesystem::path parent_dir = boost::filesystem::path(socket_file).parent_path();
        if (!boost::filesystem::exists(parent_dir)) {
            if (!boost::filesystem::create_directories(parent_dir)) {
                throw std::runtime_error("create directorys for " + socket_file + " failed!");
            }
        }
    }

    boost::shared_ptr<QuotationAccessServiceIf> if_impl(new QuotationAccessServiceHandler(runner));

    boost::shared_ptr<TProcessor> processor(new QuotationAccessServiceProcessor(if_impl));
    boost::shared_ptr<TProtocolFactory> protocol_factory(new TCompactProtocolFactory());
    boost::shared_ptr<ThreadManager> thread_manager = apache::thrift::concurrency::ThreadManager::newSimpleThreadManager(2);
    boost::shared_ptr<apache::thrift::concurrency::PosixThreadFactory> thread_factory(
                new ::apache::thrift::concurrency::PosixThreadFactory());
    thread_manager->threadFactory(thread_factory);
    thread_manager->start();

    boost::shared_ptr<TServerTransport> server_socket(new TServerSocket(socket_file));
    boost::shared_ptr<::apache::thrift::transport::TTransportFactory>
    transport_factory(new ::apache::thrift::transport::TFramedTransportFactory());

    TThreadPoolServer server(processor
            , server_socket
            , transport_factory
            , protocol_factory
            , thread_manager);
    server.serve();

    APPLOG_FATAL("QuotationAccessServiceImpl runLoop failed, terminate it....");
}
