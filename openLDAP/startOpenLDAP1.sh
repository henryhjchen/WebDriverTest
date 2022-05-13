docker run -it -d --cap-add sys_admin \
-h openLDAP1 \
-e REMOTE_MOUTN_PATH='//192.168.1.243/IT/Utils/dockerRepository/openldap1' \
-e LOCAL_MOUNT_PATH='/etc/openldap' \
-e MOUNT_USER='dnit' \
-e MOUNT_PASSWORD='den1ali' \
-e MOUNT_UID='55' \
-e MOUNT_GID='55' \
-v /etc/localtime:/etc/localtime:ro \
--name=openLDAP1 -p 389:389 \
paymentdocker0.tw.ibm.com/rhel68_openldap:20191114_v2.0
