#!/bin/bash
RESINWEBINF=`pwd`/common
LIBDIRS=${RESINWEBINF}/lib
CP=.:${RESINWEBINF}/resources
for Jars in `ls ${LIBDIRS}`
do
CP=${CP}:${LIBDIRS}/${Jars}
done
 
java  -Xms2048m -Xmx2048m -cp ${CP}   -Djava.net.preferIPv4Stack=true -Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=192.168.1.76  -Dcom.sun.management.jmxremote.port=9004  -Dcom.sun.management.jmxremote.authenticate=false  -Dcom.sun.management.jmxremote.ssl=false  com.cll.main.StartMain  >>logs/error.log 2>&1 &
