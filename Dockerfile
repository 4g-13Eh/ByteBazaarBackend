FROM eclipse-temurin:17

WORKDIR /backendapp

COPY target/ByteBazaarBackend-0.0.1-SNAPSHOT.jar backendapp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/backendapp/backendapp.jar"]
