/*
 * group_chooser.h
 *
 *  Created on: 2017年4月22日
 *      Author: 44385
 */

#ifndef QUOTATION_ACCESS_COMMON_GROUP_CHOOSER_H_
#define QUOTATION_ACCESS_COMMON_GROUP_CHOOSER_H_

#include <atomic>
#include <memory>
#include <string>

namespace xueqiao {
namespace quotation {
namespace access {

class GroupChooser {
public:
    static std::shared_ptr<GroupChooser> newInstance();
    virtual ~GroupChooser();

    std::string chooseGroup(const std::string& key);

private:
    GroupChooser() = default;
};

} // end namespace access
} // end namespace quotation
} // end namespace xueqiao



#endif /* QUOTATION_ACCESS_COMMON_GROUP_CHOOSER_H_ */
