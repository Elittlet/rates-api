FROM maven:3.8-openjdk-17 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ ./src
RUN mvn clean package -DskipTests=true

FROM eclipse-temurin:17-jdk-alpine as prod
RUN mkdir /app
COPY --from=builder /app/target/*.jar app/rates-api.jar
WORKDIR /app
ENV SERVER_PORT=5000
EXPOSE 5000
ADD target/rates-api.jar rates-api.jar
ENTRYPOINT ["java", "-jar", "rates-api.jar"]