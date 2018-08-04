FROM tomcat:8.0.36-jre8

MAINTAINER Ryo Hang
ARG APP_ENV=test
ENV APP_ENV ${APP_ENV}

EXPOSE 8080

RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY ./*.war /usr/local/tomcat/webapps/ROOT.war
COPY ./setenv.${APP_ENV}.sh  /usr/local/tomcat/bin/setenv.sh