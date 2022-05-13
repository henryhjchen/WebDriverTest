echo "Start Slave:$slaveName with key:$key"
/usr/bin/java -Duser.timezone=Asia/Taipei -jar /opt/jenkinsSlave/agent.jar -noCertificateCheck -jnlpUrl https://fojenkins.tw.ibm.com:9443/computer/$slaveName/slave-agent.jnlp -secret $key
