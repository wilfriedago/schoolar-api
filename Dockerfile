# This Dockerfile was writen, following the template from this tutorial available on https://spring.io/guides/topicals/spring-boot-docker/#_multi_stage_build
FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

LABEL maintainer="Wilfried AGO <wilfriedago@pm.me>"
LABEL description="Schoolar backend api"
LABEL version="1.0"
LABEL release-date="2023-08-16"
