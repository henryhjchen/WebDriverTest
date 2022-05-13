if [ -z "$1" ]
then
        echo "Syntax: buildOpenVPNImage.sh [Port number]"
else
        if [ -z "$2" ]
        then
            dns=192.168.1.253
        else
            dns=$2
        fi
        echo "Start build OpenVPN images with default port $1, DNS server is $dns"
        today=`date +%Y%m%d%H%M | awk '{print $1}'`
        docker build -t paymentdocker0.tw.ibm.com/rhel68_openvpn:${today}_$1 --build-arg port=$1 --build-arg dns=$dns --no-cache=true .
fi
