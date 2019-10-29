FROM openjdk:8-jdk-alpine

LABEL maintainer="berserkV"

VOLUME /tmp

EXPOSE 8081

RUN mkdir -p /app/

RUN mkdir -p /app/logs/

ADD target/anime-init-service-0.0.1-SNAPSHOT.jar /app/anime-init-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker", "-jar", "/app/anime-init-service-0.0.1-SNAPSHOT.jar"]