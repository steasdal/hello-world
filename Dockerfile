FROM tomcat:8.0
COPY build/libs/*.war /usr/local/tomcat/webapps/
