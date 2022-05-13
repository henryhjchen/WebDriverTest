if [ -z "$1" ]
then
        echo "Syntax: startOpenVPN.sh [Port number]"
else

        netstat -a | grep LISTEN | grep $1
        RC=$?
        if [ "$RC" == "0" ]
        then
           echo "Port $1 is used, please select other port, exit script..."
        else
           echo "Start OpenVPN docker images on port $1"
           docker run -d --privileged -p $1:$1 -v /etc/localtime:/etc/localtime:ro --name=openVPNServer_$1 paymentdocker0.tw.ibm.com/rhel68_openvpn:20191019_$1
        fi
fi
