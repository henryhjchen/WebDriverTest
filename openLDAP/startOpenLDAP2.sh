#docker run -it --cap-add sys_admin -v /opt/dockerVolume/openldap2/var/log:/var/log -v /opt/dockerVolume/openldap2/etc/openldap:/etc/openldap -v /etc/localtime:/etc/localtime:ro --name=openLDAP2 -p 389:389 paymentdocker0.tw.ibm.com:5000/rhel68_openldap:latest bash

docker run -it -d --cap-add sys_admin \
-h openLDAP2 \
-e REMOTE_MOUTN_PATH='//192.168.1.243/IT/Utils/dockerRepository/openldap2' \
-e LOCAL_MOUNT_PATH='/etc/openldap' \
-e MOUNT_USER='dnit' \
-e MOUNT_PASSWORD='den1ali' \
-e MOUNT_UID='55' \
-e MOUNT_GID='55' \
-v /etc/localtime:/etc/localtime:ro \
--name=openLDAP2 -p 389:389 \
paymentdocker0.tw.ibm.com/rhel68_openldap:20191114_v2.0
