/*
 * subprocess_manager.h
 *
 *  Created on: 2018年2月2日
 *      Author: wangli
 */

#ifndef SRC_PROCESS_SUBPROCESS_MANAGER_H_WIL_
#define SRC_PROCESS_SUBPROCESS_MANAGER_H_WIL_

#include <chrono>
#include <map>
#include <mutex>
#include <queue>
#include <stdint.h>
#include "base/thread_pool.h"
#include "base/sync_call.h"
#include "base/listener_container.h"

namespace xueqiao {
namespace quotation {
namespace process {

struct SubProcessEntryTimeInfo {
	int32_t start_timestamp = 0;  // 进程启动的时间
	int32_t exited_timestamp = 0; // 进程推出的时间
};

struct SubProcessEntry {
	std::string sub_process;
	int pid = -1;  // 进程对应的PID
	std::vector<SubProcessEntryTimeInfo> time_infos;
};

class ISubProcessListener {
public:
	virtual ~ISubProcessListener() = default;

	virtual void onStartSubProcessBegin(const std::string& sub_process) {}
	virtual void onStartSubProcessFinished(const std::string& sub_process, int pid) {}

	virtual void onStopSubProcessBegin(const std::string& sub_process, int pid){}
	virtual void onStopSubProcessFinished(const std::string& sub_process){}

	virtual void onRestartSubProcessBegin(const std::string& sub_process){}
	virtual void onRestartSubProcessFinished(const std::string& sub_process, int pid){}

	virtual void onSubProcessExited(const std::string& sub_process) {}

protected:
	ISubProcessListener() = default;
};

class SubProcessManager : public soldier::base::ListenerContainer<ISubProcessListener> {
public:
	static SubProcessManager& Global();
	virtual ~SubProcessManager();

	void addSubProcess(const std::string& sub_process);
	void removeSubProcess(const std::string& sub_process);
	void dumpProcessesInfo(std::vector<SubProcessEntry>& process_entries);
	void restartSubProcess(const std::string& sub_process);
	void checkSubProcesses();
	void clearAll();

	void retryProcesses();

private:
	SubProcessManager();

	void onCheckSubProcesses();
	void onAddSubProcess(const std::string& sub_process);
	void onRemoveSubProcess(const std::string& sub_process);
	void onRestartSubProcess(const std::string& sub_process);
	void onDumpProcessInfo(soldier::base::SyncCall* sync_call, std::vector<SubProcessEntry>* process_entries);
	void onClearAll();

	void startSubProcess(const std::shared_ptr<SubProcessEntry>& process_entry);
	void stopSubProcess(const std::shared_ptr<SubProcessEntry>& process_entry);

	void onRetryProcesses();

private:
	std::map<std::string, std::shared_ptr<SubProcessEntry>> sub_processes_map_;
	std::map<int, std::shared_ptr<SubProcessEntry>>     pid_processed_map_;

	std::unique_ptr<soldier::base::TaskThread> working_thread_;
};

} // end namespace process
} // end namespace quotation
} // end namespace xueqiao


#endif /* SRC_PROCESS_SUBPROCESS_MANAGER_H_WIL_ */
