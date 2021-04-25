#!/bin/bash
#

if [ -L "$0" ];then
    THIS_SCRIPT_REAL_DIR=$( dirname $0 )
else
    # run this script directly, can be run everywhere
    THIS_SCRIPT_REAL_DIR="$(dirname "${BASH_SOURCE:-$0}")"
    if [ "$( echo ${THIS_SCRIPT_REAL_DIR} | grep '^/' | wc -l )" = "0" ];then
        THIS_SCRIPT_REAL_DIR="$( pwd )/${THIS_SCRIPT_REAL_DIR}"
    fi
fi
cd ${THIS_SCRIPT_REAL_DIR} > /dev/null
CURRENT_SCRIPT_DIR=$( pwd )
cd - > /dev/null

CPP_PLATFORM_DIR="/data/code/soldier/platform/cpp_platform"

if [ "z" = "z${BUILD_BRANCH}" ];then
  echo "no BUILD_BRANCH, we should set it"
  exit 1
fi

pushd ${CURRENT_SCRIPT_DIR} > /dev/null

if [ ! -L "${CURRENT_SCRIPT_DIR}/cpp_platform" ];then
  ln -sf $CPP_PLATFORM_DIR cpp_platform || exit 1
fi

if [ ! -d "quotation_common" ];then
  git clone -b ${BUILD_BRANCH} ssh://git@git.soldier-tools.svc:29418/xueqiao-quotation/quotation_common.git || exit 
else
  pushd quotation_common > /dev/null
  git checkout ${BUILD_BRANCH} || exit
  git pull || exit
  popd > /dev/null
fi

source cpp_platform/envsetup.sh || exit
blade build quotation/race/server || exit 
popd > /dev/null
