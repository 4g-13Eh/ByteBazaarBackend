FROM maven:3.8.4-eclipse-temurin-17 AS build

WORKDIR /backendapp

COPY pom.xml .
COPY src ./src
COPY .env .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17

WORKDIR /backendapp

COPY --from=build /backendapp/target/ByteBazaarBackend-0.0.1-SNAPSHOT.jar backendapp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/backendapp/backendapp.jar"]
