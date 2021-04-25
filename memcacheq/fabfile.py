#
# install script
#

import sys
sys.path.append("/public/scripts/machine")

import os

from fabric.contrib import project, console, files
from common import getEnv
from supervisord import *
from epower import *

app_name="memcacheq"
install_dir="/usr/local/soldier/apps/" + app_name

def _install(env, fabfile_dir):
    run("mkdir -p " + install_dir )
    if not files.exists("/data/applog/apps/" + app_name):
        run("mkdir -p /data/applog/apps/" + app_name)
	if not files.exists("/data/memcacheq"):
		run("mkdir -p /data/memcacheq")
    run('cp -f ' + fabfile_dir + "/memcacheq-0.2.0/memcacheq " + install_dir)
    run('cp -f ' + fabfile_dir + '/bdb_install/lib/libdb-6.2.so ' + install_dir)
    run('cp -f ' + fabfile_dir + '/supervisor.conf ' + install_dir)
    addSupervisorConfig(install_dir)
    run("supervisorctl update")
    restart()

def install():
    _install(getEnv(), os.path.dirname(__file__))

def restart():
    run("supervisorctl restart " + app_name)

def uninstall():
    removeSupervisorConfig(install_dir)
    run("supervisorctl update")
    run("rm -rf " + install_dir)
    