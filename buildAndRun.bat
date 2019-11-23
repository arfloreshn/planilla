@echo off
call mvn clean package
call docker build -t com.arflores.hn/planilla .
call docker rm -f planilla
call docker run -d -p 9080:9080 -p 9443:9443 --name planilla com.arflores.hn/planilla