FROM openjdk:17
EXPOSE 5000
ADD target/rates-api.jar rates-api.jar
ENTRYPOINT ["java", "-jar", "rates-api.jar"]