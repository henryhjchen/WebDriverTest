FROM paymentdocker0.tw.ibm.com/centos8:8.2_20200804 as buildImage
ARG SOFTWARE_FOLDER

# Need packages
RUN yum install -y \
           gtk2 \
           libXtst \
           procps \
           findutils \
           tar \
           unzip \
           which \
           Xvfb \
           iputils \
           net-tools \
           git \
    && yum clean all \
    && rm -rf /var/cache/yum \
    && rm -rf /tmp/*
RUN yum update -y

#OCP OC
COPY ${SOFTWARE_FOLDER}/oc /usr/bin/oc
RUN chmod 777 /usr/bin/oc

#ACE Toolkit
ARG TOOLKIT_FILE_NAME
RUN mkdir /opt/IBM \
    && mkdir /opt/IBM/mqsi
COPY ${SOFTWARE_FOLDER}/${TOOLKIT_FILE_NAME} /opt/IBM/mqsi/${TOOLKIT_FILE_NAME}
RUN tar -zxvf /opt/IBM/mqsi/${TOOLKIT_FILE_NAME} --directory=/opt/IBM/mqsi/ \
    && ls -al /opt/IBM/mqsi/ \
    && mkdir /opt/IBM/mqsi/toolkit \
    && cp -r /opt/IBM/mqsi/ace*/* /opt/IBM/mqsi/toolkit/ \
    && ls -al /opt/IBM/mqsi/toolkit/ \
    && rm -rf /opt/IBM/mqsi/ace* \
    && rm -rf /opt/IBM/mqsi/${TOOLKIT_FILE_NAME} \
    && /opt/IBM/mqsi/toolkit/ace make registry global accept license silently
	
#OpenVPN
RUN dnf install epel-release -y	\
    && dnf install openvpn -y \
	&& rm -rf /var/cache/yum \
    && rm -rf /tmp/*
    
COPY ${SOFTWARE_FOLDER}/openvpn/* /etc/openvpn/
RUN echo "openvpn --config /etc/openvpn/fnbucket.ovpn &" > /etc/openvpn/startVPN.sh \
    && chmod 777 /etc/openvpn/startVPN.sh

#Ant
ARG ANT_FILE_NAME
COPY ${SOFTWARE_FOLDER}/${ANT_FILE_NAME} /tmp
RUN mkdir /opt/ant \
    && unzip /tmp/${ANT_FILE_NAME} -d /opt/ \
    && ls -al /opt/ \
    && cp -r /opt/apache-ant*/* /opt/ant/ \
    && chmod -R 777 /opt/ant \
    && ls -al /opt/ant/ \
    && rm -f /tmp/${ANT_FILE_NAME} \
    && rm -rf /opt/apache-ant*

#RTC 
ARG RTC_FILE_NAME
COPY ${SOFTWARE_FOLDER}/${RTC_FILE_NAME} /opt/IBM
RUN unzip /opt/IBM/${RTC_FILE_NAME} -d /opt/IBM \
    && chmod -R 777 /opt/IBM/jazz \
    && ls -al /opt/IBM/jazz \
    && rm -f /opt/IBM/${RTC_FILE_NAME}
    
#IBM Java
ARG JAVA_FILE_NAME
COPY ${SOFTWARE_FOLDER}/${JAVA_FILE_NAME} /tmp/${JAVA_FILE_NAME}
RUN chmod 777 /tmp/${JAVA_FILE_NAME}
RUN touch /tmp/install.properties \
    && echo "INSTALLER_UI=silent" >> /tmp/install.properties \
    && echo "LICENSE_ACCEPTED=TRUE" >> /tmp/install.properties \
    && echo "USER_INSTALL_DIR=/opt/IBM/java-x86_64-80" >> /tmp/install.properties \
    && echo "-fileOverwrite_/opt/IBM/java-x86_64-80/_uninstall/uninstall.lax=Yes" >> /tmp/install.properties
RUN /tmp/${JAVA_FILE_NAME} -i silent -f /tmp/install.properties \
    && rm -f /tmp/${JAVA_FILE_NAME} \
    && rm -f /tmp/install.properties \
    && ls -al /opt/IBM/java-x86_64-80 \
    && /opt/IBM/java-x86_64-80/bin/java -version

#Oracle Java
ARG ORACLE_JAVA_FILE_NAME
COPY ${SOFTWARE_FOLDER}/${ORACLE_JAVA_FILE_NAME} /tmp/${ORACLE_JAVA_FILE_NAME}
RUN rpm -ivh /tmp/${ORACLE_JAVA_FILE_NAME}
RUN rm -f /tmp/${ORACLE_JAVA_FILE_NAME}
RUN which java && ls -al /etc/alternatives/java && java -version
    
#Create container initial script
RUN rm -rf /tmp/* \
    && touch /tmp/startUP.sh \
    && echo "if [ -z \"\${vpn}\" ]; then" >> /tmp/startUP.sh \
    && echo "    startVPN=\"NO\"" >> /tmp/startUP.sh \
    && echo "else" >> /tmp/startUP.sh \
    && echo "   startVPN=\"\${vpn}\"" >> /tmp/startUP.sh \
    && echo "fi" >> /tmp/startUP.sh \
    && echo " " >> /tmp/startUP.sh \
    && echo "if [ \${startVPN} = \"YES\" ]; then" >> /tmp/startUP.sh \
    && echo "   mkdir -p /dev/net" >> /tmp/startUP.sh \
    && echo "   mknod /dev/net/tun c 10 200" >> /tmp/startUP.sh \
    && echo "   /etc/openvpn/startVPN.sh" >> /tmp/startUP.sh \
    && echo "fi" >> /tmp/startUP.sh \
    && echo " " >> /tmp/startUP.sh \
    && echo "/tmp/startSlave.sh" >> /tmp/startUP.sh \
    && chmod 777 /tmp/startUP.sh

#Create start jenkins slave script
COPY ${SOFTWARE_FOLDER}/agent.jar /opt/
RUN touch /tmp/startSlave.sh \
    && echo "java -Djava.lang.string.substring.nocopy=true -Xmx4096m \
        -Dcom.ibm.jsse2.overrideDefaultProtocol=TLSv1.2 \
        -Dcom.ibm.jsse2.overrideDefaultTLS=true \
        -Duser.timezone=Asia/Taipei -jar /opt/agent.jar -noCertificateCheck \
        -jnlpUrl https://fojenkins.tw.ibm.com:9443/computer/\${slaveName}/slave-agent.jnlp \
        -secret \${key}" >> /tmp/startSlave.sh \
    && chmod 777 /tmp/startSlave.sh
    
#Set environment variable
ENV JAVA_HOME "/opt/IBM/java-x86_64-80"
#ENV PATH "$JAVA_HOME/bin:$PATH:/opt/ant/bin"
ENV PATH "$PATH:/opt/ant/bin"

ENTRYPOINT ["/bin/bash","/tmp/startUP.sh"]