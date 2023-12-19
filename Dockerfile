FROM openjdk:17-jdk-slim-buster
LABEL authors="alperengumrukcu"

EXPOSE 8080

ADD target/*.jar nishproject-docker.jar

ENTRYPOINT ["java", "-jar","/nishproject-docker.jar"]