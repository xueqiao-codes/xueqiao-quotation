#include "base/app_log.h"
#include "QuotationPlanBo_variables.h"
#include "thrift/concurrency/PosixThreadFactory.h"
#include "thrift/protocol/TCompactProtocol.h"
#include "thrift/server/TNonblockingServer.h"
#include "thrift/transport/TServerSocket.h"
#include "thrift/TProcessor.h"
#include "QuotationPlanBo_handler.h"

int main(int argc, char* argv[]) { 
  soldier::base::AppLog::Init("service/quotation_plan_bo");
  boost::shared_ptr<apache::thrift::TProcessor> processor(
    new  ::xueqiao::quotation::plan::bo::QuotationPlanBoProcessor(boost::shared_ptr< ::xueqiao::quotation::plan::bo::QuotationPlanBoIf>(new  ::xueqiao::quotation::plan::bo::QuotationPlanBoHandler())));
  boost::shared_ptr<apache::thrift::protocol::TProtocolFactory>
    protocolFactory(new apache::thrift::protocol::TCompactProtocolFactory());
  boost::shared_ptr<apache::thrift::concurrency::ThreadManager> threadManager
    = apache::thrift::concurrency::ThreadManager::newSimpleThreadManager(20);
  boost::shared_ptr<apache::thrift::concurrency::PosixThreadFactory>
    threadFactory(new ::apache::thrift::concurrency::PosixThreadFactory());
  threadManager->threadFactory(threadFactory); 
  threadManager->start();
  int port = 10000 +  ::xueqiao::quotation::plan::bo::QuotationPlanBo_service_key;
  apache::thrift::server::TNonblockingServer server(processor, protocolFactory, port, threadManager);
  server.serve();
  return 0;
}
