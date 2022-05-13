FROM paymentdocker0.tw.ibm.com/rhel68_openldap:base_v1.0

COPY start.sh /tmp
RUN chmod 777 /tmp/start.sh

ENTRYPOINT ["/bin/bash","/tmp/start.sh"]
