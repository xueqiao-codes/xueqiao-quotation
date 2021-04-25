/*
 * subprocess_manager.cpp
 *
 *  Created on: 2018年2月2日
 *      Author: wangli
 */

#include "subprocess_manager.h"

#include <boost/lexical_cast.hpp>
#include <sys/wait.h>
#include "base/app_log.h"
#include "base/code_defense.h"
#include "base/os_helper.h"
#include "base/time_helper.h"

#define MAX_PROCESS_TIME_INFO_LEN 5

using namespace soldier::base;
using namespace xueqiao::quotation::process;

static std::unique_ptr<SubProcessManager> s_instance;

SubProcessManager& SubProcessManager::Global() {
	if (!s_instance) {
		static std::mutex lock;
		lock.lock();
		if (!s_instance) {
			s_instance.reset(new SubProcessManager());
		}
		lock.unlock();
	}
	return *s_instance;
}

SubProcessManager::SubProcessManager() {
	working_thread_.reset(new soldier::base::TaskThread());
}

SubProcessManager::~SubProcessManager() {
}

void SubProcessManager::addSubProcess(const std::string& sub_process){
	if (sub_process.empty()) {
		return ;
	}
	working_thread_->postTask(&SubProcessManager::onAddSubProcess, this, sub_process);
}

void SubProcessManager::removeSubProcess(const std::string& sub_process) {
	if (sub_process.empty()) {
		return ;
	}
	working_thread_->postTask(&SubProcessManager::onRemoveSubProcess, this, sub_process);
}

void SubProcessManager::clearAll() {
    working_thread_->postTask(&SubProcessManager::onClearAll, this);
}

void SubProcessManager::dumpProcessesInfo(std::vector<SubProcessEntry>& process_entries) {
	if(working_thread_->isRunningInTaskThread()) {
		onDumpProcessInfo(NULL, &process_entries);
		return ;
	}

	SyncCall sync_call;
	working_thread_->postTask(&SubProcessManager::onDumpProcessInfo, this, &sync_call, &process_entries);
	sync_call.wait();
}

void SubProcessManager::restartSubProcess(const std::string& sub_process) {
	if (sub_process.empty()) {
		return ;
	}
	working_thread_->postTask(&SubProcessManager::onRestartSubProcess, this, sub_process);
}

void SubProcessManager::checkSubProcesses() {
	working_thread_->postTask(&SubProcessManager::onCheckSubProcesses, this);
}

void SubProcessManager::retryProcesses() {
	working_thread_->postTask(&SubProcessManager::onRetryProcesses, this);
}

void SubProcessManager::startSubProcess(const std::shared_ptr<SubProcessEntry>& process_entry) {
	if (process_entry->pid > 0) {
		APPLOG_WARN("[NOTICE] sub_process={} process pid={} is already started"
				, process_entry->sub_process
				, process_entry->pid);
		return ;
	}

	std::vector<std::shared_ptr<ISubProcessListener>> listeners;
	getListeners(listeners);

	for (auto listener : listeners) {
		listener->onStartSubProcessBegin(process_entry->sub_process);
	}

	const char* args[] = {NULL};
	ProcessCommandArgs pc_args(process_entry->sub_process, args);
	process_entry->pid = OSHelper::startChildProcess(pc_args);

	SubProcessEntryTimeInfo time_info;
	time_info.start_timestamp = NowInSeconds();
	if (process_entry->pid <= 0) {
		time_info.exited_timestamp = time_info.start_timestamp;
	}
	process_entry->time_infos.push_back(time_info);

	if (process_entry->time_infos.size() > MAX_PROCESS_TIME_INFO_LEN) {
		process_entry->time_infos.erase(process_entry->time_infos.begin());
	}

	for (auto listener : listeners) {
		listener->onStartSubProcessFinished(process_entry->sub_process, process_entry->pid);
	}

	pid_processed_map_[process_entry->pid] = process_entry;
}

void SubProcessManager::stopSubProcess(const std::shared_ptr<SubProcessEntry>& process_entry) {
	if (process_entry->pid <= 0) {
		APPLOG_WARN("[NOTICE] sub_process={} process pid={} is already stopped"
				, process_entry->sub_process
				, process_entry->pid);
		return ;
	}

	int old_pid = process_entry->pid;
	std::vector<std::shared_ptr<ISubProcessListener>> listeners;
	getListeners(listeners);

	for (auto listener : listeners) {
		listener->onStopSubProcessBegin(process_entry->sub_process, process_entry->pid);
	}
	OSHelper::killChildProcess(process_entry->pid);
	CHECK(process_entry->time_infos.size() > 0);
	process_entry->time_infos.back().exited_timestamp = NowInSeconds();
	process_entry->pid = -1;

	for (auto listener : listeners) {
		listener->onStopSubProcessFinished(process_entry->sub_process);
	}

	pid_processed_map_.erase(old_pid);
}

void SubProcessManager::onAddSubProcess(const std::string& sub_process) {
	APPLOG_INFO("onAddSubProcess sub_process={}", sub_process);
	auto process_entry_it = sub_processes_map_.find(sub_process);
	if (process_entry_it != sub_processes_map_.end()) {
		return ;
	}

	std::shared_ptr<SubProcessEntry> new_entry(new SubProcessEntry());
	new_entry->sub_process = sub_process;
	startSubProcess(new_entry);

	sub_processes_map_[sub_process] = new_entry;
}

void SubProcessManager::onRemoveSubProcess(const std::string& sub_process) {
	APPLOG_INFO("onRemoveSubProcess sub_process={}", sub_process);
	auto process_entry_it = sub_processes_map_.find(sub_process);
	if (process_entry_it == sub_processes_map_.end()) {
	    APPLOG_INFO("onRemoveSubProcess sub_process={}, process entry not found", sub_process);
		return ;
	}

	if (process_entry_it->second->pid > 0) {
		stopSubProcess(process_entry_it->second);
	}
	sub_processes_map_.erase(sub_process);
	APPLOG_INFO("onRemoveSubProcess sub_process={} finished , after sub_processes_map_ size={}"
	        , sub_process
	        , sub_processes_map_.size());
}

void SubProcessManager::onRestartSubProcess(const std::string& sub_process) {
	APPLOG_INFO("onRestartSubProcess sub_process={}", sub_process);

	auto process_entry_it = sub_processes_map_.find(sub_process);
	if (process_entry_it == sub_processes_map_.end()) {
		return ;
	}

	std::vector<std::shared_ptr<ISubProcessListener>> listeners;
	getListeners(listeners);

	for (auto listener : listeners) {
		listener->onRestartSubProcessBegin(sub_process);
	}
	if (process_entry_it->second->pid > 0) {
		stopSubProcess(process_entry_it->second);
	}
	startSubProcess(process_entry_it->second);

	for (auto listener : listeners) {
		listener->onRestartSubProcessFinished(sub_process, process_entry_it->second->pid);
	}
}

void SubProcessManager::onDumpProcessInfo(
		SyncCall* sync_call, std::vector<SubProcessEntry>* process_entries) {
	for(auto it = sub_processes_map_.begin(); it != sub_processes_map_.end(); ++it) {
		process_entries->push_back(*(it->second));
	}
	if (sync_call) {
		sync_call->notify();
	}
}

void SubProcessManager::onClearAll() {
    APPLOG_INFO("onClearAll...");
    std::vector<std::string> all_sub_processes;
    for(auto it = sub_processes_map_.begin(); it != sub_processes_map_.end(); ++it) {
        all_sub_processes.push_back(it->first);
    }
    for (auto sub_process : all_sub_processes) {
        onRemoveSubProcess(sub_process);
    }
}

void SubProcessManager::onCheckSubProcesses() {
	int pid = 0;
	int status = 0;

	std::vector<std::shared_ptr<ISubProcessListener>> listeners;
	getListeners(listeners);

	bool has_process_exited = false;
	while((pid = waitpid(-1, &status, WNOHANG)) > 0) {
		auto process_entry_it = pid_processed_map_.find(pid);
		if (process_entry_it == pid_processed_map_.end()) {
			continue;
		}

		process_entry_it->second->pid = -1;
		CHECK(process_entry_it->second->time_infos.size() > 0);
		process_entry_it->second->time_infos.back().exited_timestamp = NowInSeconds();
		pid_processed_map_.erase(process_entry_it);

		for (auto listener : listeners) {
			listener->onSubProcessExited(process_entry_it->second->sub_process);
		}

		has_process_exited = true;
	}
	if (has_process_exited) {
		retryProcesses();
	}
}

void SubProcessManager::onRetryProcesses() {
	APPLOG_INFO("onRetryProcesses...");
	for (auto process_entry_it = sub_processes_map_.begin()
			; process_entry_it != sub_processes_map_.end()
			; ++process_entry_it) {
		if (process_entry_it->second->pid > 0) {
			continue ;
		}

		// 检查最近启动的情况
		if (process_entry_it->second->time_infos.size() < 3) {
			// 如果最近重启的次数小于3， 则尝试重启
			startSubProcess(process_entry_it->second);
		} else {
			int restart_min_secs = 0;
			int alive_period_escaped = 0;

			std::vector<SubProcessEntryTimeInfo>& time_infos = process_entry_it->second->time_infos;
			for (int rindex = 0; rindex < 3; ++rindex) {
				SubProcessEntryTimeInfo time_info = time_infos[time_infos.size() - rindex - 1];
				if (time_info.exited_timestamp <= 0) {
					APPLOG_WARN("found exited timestamp <= 0 for trade_account_id={}"
							, process_entry_it->first);
					continue;
				}
				if (time_info.exited_timestamp < time_info.start_timestamp) {
					APPLOG_WARN("found exited timestamp < start timestamp for trade_account_id={}"
							, process_entry_it->first);
					continue;
				}

				alive_period_escaped += (time_info.exited_timestamp - time_info.start_timestamp);
			}

			if (alive_period_escaped <= 6) {
				restart_min_secs = 60;
			} else if (alive_period_escaped <= 60) {
				restart_min_secs = 30;
			} else if (alive_period_escaped <= 5*60){
				restart_min_secs = 10;
			} else {
				restart_min_secs = 0;
			}

			APPLOG_INFO("sub_process={} restart min seconds={}", process_entry_it->first , restart_min_secs);
			int32_t now_timestamp = NowInSeconds();
			if((now_timestamp - time_infos[time_infos.size() - 1].exited_timestamp) >= restart_min_secs) {
				startSubProcess(process_entry_it->second);
			}
		}
	}
}








