# Fase de construcción
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Fase de ejecución
FROM openjdk:17-jdk-alpine
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ./target/ForoHub-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]


