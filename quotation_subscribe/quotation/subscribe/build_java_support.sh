#!/bin/bash
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


pushd ${THIS_SCRIPT_REAL_DIR} > /dev/null

blade build --generate-java || exit 1

rm -f java/src/main/java/xueqiao/quotation/*.java
cp -f ../../build64_release/quotation/quotation_item_client/xueqiao/quotation/*.java java/src/main/java/xueqiao/quotation/ || exit 1
cp -f  ../../build64_release/quotation/subscribe/libQuotationSubscriber_java.so java/src/main/resources/linux || exit 1

pushd java
mvn package || exit 1
popd > /dev/null

#echo "runing test...."
#chmod u+x java/test/test.sh
#java/test/test.sh || exit 1

echo "Finished..."

popd > /dev/null