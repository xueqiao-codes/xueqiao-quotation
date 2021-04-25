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

install_dir="/usr/local/soldier/apps/quotation_race_server"

def _install(env, fabfile_dir):
    run("mkdir -p " + install_dir )
    if not files.exists("/data/applog/apps/quotation_race_server"):
        run("mkdir -p /data/applog/apps/quotation_race_server")
    if files.exists(install_dir + "/quotation_race_server"):
        run('cp -f ' + install_dir + "/quotation_race_server " + install_dir + '/quotation_race_server.bak')
    run('cp -f ' + fabfile_dir + "/../../../build64_release/quotation/race/server/quotation_race_server " + install_dir)
    run('cp -f ' + fabfile_dir + '/supervisor.conf ' + install_dir)
    addSupervisorConfig(install_dir)
    run("supervisorctl update")
    restart()
    

def install():
    _install(getEnv(), os.path.dirname(__file__))

def restart():
    run("supervisorctl restart quotation_race_server")

def uninstall():
    removeSupervisorConfig(install_dir)
    run("supervisorctl update")
    run("rm -rf " + install_dir)
    