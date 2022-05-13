if [ -z "$1" ]
then
        echo "Syntax: buildImage.sh [tagName]"
else
        docker build -t paymentdocker0.tw.ibm.com/rhel68_openldap:$1 --no-cache=true . 
fi
