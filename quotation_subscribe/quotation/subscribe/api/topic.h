/*
 * topic.h
 *
 *  Created on: 2017年4月3日
 *      Author: 44385
 */

#ifndef QUOTATION_SUBSCRIBE_TOPIC_H_
#define QUOTATION_SUBSCRIBE_TOPIC_H_

#include "base/string_util.h"

#include <string>
#include <sstream>
#include <iostream>

namespace xueqiao {
namespace quotation {

class Topic {
public:
    Topic() {}
    ~Topic() {}
    Topic(const std::string& platform, const std::string& contract_symbol)
        :platform_(platform), contract_symbol_(contract_symbol){
    }

    const std::string& Platform() const { return platform_; }
    void setPlatform(const std::string& platform) { platform_ = platform; }

    const std::string& contractSymbol() const { return contract_symbol_; }
    void setContractSymbol(const std::string& contract_symbol) { contract_symbol_ = contract_symbol;}

#ifndef SWIG
    inline bool operator==(const Topic& topic) const {
        if (platform_ == topic.Platform() && contract_symbol_ == topic.contractSymbol()) {
            return true;
        }
        return false;
    }

    bool operator < (const Topic& topic) const {
        if (platform_ < topic.Platform()) {
            return true;
        }
        return contract_symbol_ < topic.contractSymbol();
    }


    inline std::string description() const {
        std::stringstream ss;
        ss << "/quotation/" << platform_ << "/" << contract_symbol_ << "/";
        return ss.str();
    }

    inline void from(const std::string& description) {
        std::vector<std::string> tokens;
        ::soldier::base::StringUtil::tokenize(description, tokens, "/", false);
        if (tokens.size() >= 5 && tokens[1] == "quotation" && tokens[tokens.size() - 1].empty()) {
            platform_ = tokens[2];
            contract_symbol_ = tokens[3];
            for (size_t index = 4; index < tokens.size() - 1; ++index) {
                contract_symbol_ += "/";
                contract_symbol_ += tokens[index];
            }
        }
    }
#endif

private:
    std::string platform_;
    std::string contract_symbol_;
};


} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_SUBSCRIBE_TOPIC_H_ */
