FROM tomcat:8.0.20-jre8
# Dummy text to test 
COPY target/application*.war /usr/local/tomcat/webapps/application.war
