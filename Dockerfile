FROM tomcat:8.0-jre8
COPY build/libs/*.war /usr/local/tomcat/webapps/
