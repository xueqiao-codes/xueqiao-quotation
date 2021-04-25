/*
 * account_config.h
 *
 *  Created on: 2018年11月22日
 *      Author: wangli
 */

#ifndef QUOTATION_ACCESS_COMMON_ACCOUNT_CONFIG_H_
#define QUOTATION_ACCESS_COMMON_ACCOUNT_CONFIG_H_

#include <string>
#include <map>

namespace xueqiao {
namespace quotation {
namespace access {

class AccountConfig {
public:
    AccountConfig() = default;
    ~AccountConfig() = default;

    const std::string& getUserName() { return user_name_; }
    AccountConfig& setUserName(const std::string& user_name) {
        user_name_ = user_name;
        return *this;
    }

    const std::string& getUserPasswd() { return user_passwd_; }
    AccountConfig& setUserPasswd(const std::string& user_passwd) {
        user_passwd_ = user_passwd;
        return *this;
    }

    const std::string& getPlatform() {
        return platform_;
    }

    AccountConfig& setPlatform(const std::string& platform) {
        platform_ = platform;
        return *this;
    }

    std::string getProperty(const std::string& key) {
        return properties_[key];
    }

    AccountConfig& setProperty(const std::string& key, const std::string& value) {
        properties_[key] = value;
        return *this;
    }

private:
    std::string platform_;
    std::string user_name_;
    std::string user_passwd_;

    std::map<std::string, std::string> properties_;
};


} // end namespace access
} // end namespace quotation
} // end namespace xueqiao


#endif /* QUOTATION_ACCESS_COMMON_ACCOUNT_CONFIG_H_ */
