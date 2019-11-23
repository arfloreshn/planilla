#!/bin/sh
mvn clean package && docker build -t com.arflores.hn/planilla .
docker rm -f planilla || true && docker run -d -p 9080:9080 -p 9443:9443 --name planilla com.arflores.hn/planilla