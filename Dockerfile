FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/registration-0.0.1-SNAPSHOT.jar /app/registration.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/registration.jar"]
