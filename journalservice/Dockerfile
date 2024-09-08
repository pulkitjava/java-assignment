
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/journalservice-0.0.1-SNAPSHOT.jar /app/journal-service.jar


ENTRYPOINT ["java", "-jar", "/app/journal-service.jar"]