# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

FROM payara/server-full:5.191

RUN echo 'set-hazelcast-configuration --clusterMode=multicast
--multicastGroup=224.2.2.3 --multicastPort=54327 --dynamic=true' > $POSTBOOT_COMMANDS

COPY ./target/mysql-connector-java-5.1.44.jar /opt/payara/appserver/glassfish/lib/
COPY ./target/planilla.war /opt/payara/deployments/

LABEL arfloreshn.frontend.rule "Host:payara-node.cluster"
LABEL arfloreshn.port 8080
LABEL arfloreshn.backend planilla
