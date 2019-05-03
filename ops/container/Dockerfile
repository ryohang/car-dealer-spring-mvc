FROM tomcat:9.0.10-jre8

MAINTAINER Ryo Hang
ARG PROFILE=dev
ENV PROFILE ${PROFILE}

EXPOSE 8080

RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY ./*.war /usr/local/tomcat/webapps/ROOT.war
COPY ./setenv.sh  /usr/local/tomcat/bin/setenv.sh