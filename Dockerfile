# Dockerfile-flask
# We simply inherit the Python 3 image. This image does
# not particularly care what OS runs underneath
FROM tomcat:latest
COPY $PWD/target/letscard.war /usr/local/tomcat/webapps/letscard.war
