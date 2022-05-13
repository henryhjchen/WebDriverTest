docker build \
 --build-arg SOFTWARE_FOLDER=$2 \
 --build-arg TOOLKIT_FILE_NAME=$3 \
 --build-arg ANT_FILE_NAME=$4 \
 --build-arg RTC_FILE_NAME=$5 \
 --build-arg JAVA_FILE_NAME=$6 \
 --build-arg ORACLE_JAVA_FILE_NAME=$7 \
 -t paymentdocker0.tw.ibm.com/centos8_jenkinsslave:$1 \
 --no-cache=true . 

