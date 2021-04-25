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

pushd ${CURRENT_SCRIPT_DIR} > /dev/null

if [ ! -d bdb_install ];then
	mkdir bdb_install || exit 1
fi

if [ ! -f "${CURRENT_SCRIPT_DIR}/bdb_install/lib/libdb-6.2.so" ];then
	pushd $CURRENT_SCRIPT_DIR/db-6.2.32/build_unix > /dev/null
	chmod a+x ../dist/configure || exit
	../dist/configure --prefix=$CURRENT_SCRIPT_DIR/bdb_install || exit 1
	make || exit 1
	make install || exit 1

	popd > /dev/null
	
fi

pushd $CURRENT_SCRIPT_DIR/memcacheq-0.2.0 > /dev/null
chmod a+x configure || exit
./configure --enable-threads --with-bdb=${CURRENT_SCRIPT_DIR}/bdb_install || exit 2
make || exit 2

popd > /dev/null

popd > /dev/null

echo "finished!"