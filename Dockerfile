FROM openjdk:8-jdk-alpine
MAINTAINER Leonardo Park
VOLUME /tmp
ARG JAR_FILE
ADD ./build/libs/app.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx200m","-jar","/app.jar"]