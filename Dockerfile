FROM openjdk:8-jdk-alpine
MAINTAINER Leonardo Park
VOLUME /tmp
ADD ./build/libs/app.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx200m","-jar","/app.jar"]