FROM openjdk:17-jdk-slim-buster
LABEL authors="alperengumrukcu"

EXPOSE 8080

ADD target/*.jar nish-project-docker.jar

ENTRYPOINT ["java", "-jar","/nish-project-docker.jar"]