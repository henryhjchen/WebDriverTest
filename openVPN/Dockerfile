FROM paymentdocker0.tw.ibm.com/rhel68_openvpn:initVersion_20191019
ARG port
ARG dns
EXPOSE ${port}
RUN cp -p /etc/openvpn/server.conf /etc/openvpn/server.conf.ori
RUN sed -e 's/<PORT>/'"${port}"'/' -e 's/<DNS>/'"${dns}"'/' -e 's/;log/log/' -e 's/log-append/;log-append/' /etc/openvpn/server.conf.ori > /etc/openvpn/server.conf
RUN echo 'push "route 192.168.2.0 255.255.255.0"' >> /etc/openvpn/server.conf
RUN echo 'push "route 192.168.0.0 255.255.255.0"' >> /etc/openvpn/server.conf
RUN ls -al /etc/openvpn/
RUN diff /etc/openvpn/server.conf /etc/openvpn/server.conf.ori;echo $?
RUN cp -p /etc/sysconfig/iptables /etc/sysconfig/iptables.ori
RUN sed -e 's/11194/'"${port}"'/g' /etc/sysconfig/iptables.ori > /etc/sysconfig/iptables
RUN ls -al /etc/sysconfig/
RUN diff /etc/sysconfig/iptables /etc/sysconfig/iptables.ori;echo $?
RUN echo "/etc/init.d/iptables start;/etc/init.d/openvpn start" > /tmp/start.sh
RUN echo "sleep 10" >> /tmp/start.sh
RUN echo "tail -f /etc/openvpn/openvpn.log" >> /tmp/start.sh
RUN chmod 777 /tmp/start.sh
RUN ls -al /tmp/start.sh
RUN cat /tmp/start.sh
CMD ["/bin/bash","/tmp/start.sh"]
#ENTRYPOINT /tmp/start.sh 
