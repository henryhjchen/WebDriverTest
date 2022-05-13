if [ -z "$REMOTE_MOUTN_PATH" ]; then
  REMOTE_MOUTN_PATH=//192.168.1.243/IT/Utils/dockerRepository/openldap2
fi
if [ -z "$LOCAL_MOUNT_PATH" ]; then
  LOCAL_MOUNT_PATH=/etc/openldap
fi
if [ -z "$MOUNT_USER" ]; then
  MOUNT_USER=dnit
fi
if [ -z "$MOUNT_PASSWORD" ]; then
  MOUNT_PASSWORD=den1ali
fi
if [ -z "$MOUNT_UID" ]; then
  MOUNT_UID=500
fi
if [ -z "$MOUNT_GID" ]; then
  MOUNT_GID=500
fi

mount -t cifs -o defaults,rw,username=${MOUNT_USER},password=${MOUNT_PASSWORD},uid=${MOUNT_UID},gid=${MOUNT_GID},file_mode=0777,dir_mode=0777 ${REMOTE_MOUTN_PATH} ${LOCAL_MOUNT_PATH}
ls -al ${LOCAL_MOUNT_PATH}

/etc/init.d/slapd start
tail -f /var/log/messages