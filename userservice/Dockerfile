FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/userservice-0.0.1-SNAPSHOT.jar /app/user-service.jar


ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]