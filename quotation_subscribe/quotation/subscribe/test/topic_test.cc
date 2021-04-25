/*
 * topic_test.cc
 *
 *  Created on: 2017年8月29日
 *      Author: wileywang
 */
#include "quotation/subscribe/api/topic.h"

#include <iostream>
using namespace xueqiao::quotation;

int main(int argc, char* argv[]) {

    Topic topic;
    topic.from("/quotation/CTP/abc/");
    std::cout << "platform=" << topic.Platform() << ", contractSymbols=" << topic.contractSymbol() << std::endl;

    topic.from("/quotation/CTP//abc/");
    std::cout << "platform=" << topic.Platform() << ", contractSymbols=" << topic.contractSymbol() << std::endl;

    topic.from("/quotation/CTP/a/bc/");
    std::cout << "platform=" << topic.Platform() << ", contractSymbols=" << topic.contractSymbol() << std::endl;

    topic.from("/quotation/CTP/a//bc/");
    std::cout << "platform=" << topic.Platform() << ", contractSymbols=" << topic.contractSymbol() << std::endl;

    topic.from("/quotation/CTP/a//bc//");
    std::cout << "platform=" << topic.Platform() << ", contractSymbols=" << topic.contractSymbol() << std::endl;

    topic.from("/quotation/CTP//");
    std::cout << "platform=" << topic.Platform() << ", contractSymbols=" << topic.contractSymbol() << std::endl;

    topic.from("/quotation/CTP/ //abc&bcd /");
    std::cout << "platform=" << topic.Platform() << ", contractSymbols=" << topic.contractSymbol() << std::endl;

    Topic error_topic;
    error_topic.from("/quotation/CTP/");
    std::cout << "platform=" << error_topic.Platform() << ", contractSymbols=" << error_topic.contractSymbol() << std::endl;

    return 0;
}



