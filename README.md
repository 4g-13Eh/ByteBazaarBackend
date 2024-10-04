# ByteBazaarBackend

This is the backend of the ByteBazaar application, which handles user authentication, shopping cart management, and item handling. The application uses Spring Boot and provides various REST APIs for interaction with the front end.

## Table of Contents
- [Prerequisites](#Prerequisites)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [Environment Variables](#environment-variables)
- [Testing the Application](#testing-the-application)
- [License](#License)

## Prerequisites
Ensure you have the following installed:
- Java 17 or above
- Maven 3.6+
- MariaDB or any other preferred database
### Setting Up the Database
Configure your database in the [application.properties](./src/main/resources/application.properties) file under the src/main/resources directory. You can either use the [example.env](src/main/resources/example.env) file, adjust the values and rename it to .env or you could just update the values directly in the properties file.
#### Example configuration for MariaDB:
````properties
spring.datasource.url=jdbc:mariadb://${MARIADB_HOST}:${MARIADB_PORT}/${DB_NAME}
spring.datasource.username=${MARIADB_USER}
spring.datasource.password=${MARIADB_PASSWORD}
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
````
Create the necessary database and corresponding tables manually if they don’t exist or import them from the [schema](./schema.sql):
`````sql
MARIADB-IMPORT DATABASE schema.sql
`````

## Getting Started
To get started with this project, you'll need to clone the repository and set up your environment.
```bash
git clone https://github.com/4g-13Eh/ByteBazaarBackend.git
cd bytebazaarbackend
````

## Running the Application
You can run the application using Maven:
````maven
./mvnw spring-boot:run
````
or package it into a JAR file and run it:
````bash
./mvnw package
java -jar target/bytebazaar-backend-0.0.1-SNAPSHOT.jar
````

## Environment Variables
You can configure environment-specific settings using a [.env](./src/main/resources/example.env) file or by updating the [application.properties file](./src/main/resources/application.properties). The example.env files provides a sample configuration.
````env
MARIADB_USER=USERNAME 
MARIADB_PASSWORD=PASSWORD
MARIADB_HOST=localhost
MARIADB_PORT=3306
DB_NAME=bytebazaarDB
````

## Testing the Application
The project includes Postman test collections, which can be found in the [src/test/postman](./src/test/postman) directory:

- [ByteBazaar.postman_collection.json](./src/test/postman/ByteBazaar.postman_collection.json): The Postman collection for testing the API endpoints.
- [ByteBazaar.postman_test_run.json](./src/test/postman/ByteBazaar.postman_test_run.json): A sample test run result.

Alternatively, you can also just use the below button to run the collection:<br>
  [<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/26136977-ff7f7f1f-b3ba-4fab-8bc1-1923d41d53c5?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D26136977-ff7f7f1f-b3ba-4fab-8bc1-1923d41d53c5%26entityType%3Dcollection%26workspaceId%3D378bc62c-a63d-448e-8372-032400ac36c1)

If you are running Postman tests, ensure that the expiration time of the JWT tokens is sufficient for the test run.
- **Option 1**: You can adjust the setTimeout function in the Postman tests if the tokens are expiring too quickly.
- **Option 2**: Adjust the expiration time in the [application.properties](./src/main/resources/application.properties) file & [CookieServiceImpl](./src/main/java/ByteBazaar/ByteBazaarBackend/security/service/impl/CookieServiceImpl.java)-class by changing these values :
````properties
application.security.jwt.access.expiration=180000
application.security.jwt.refresh.expiration=604800000
````
````java
if (tokenType == TokenType.REFRESH){
    cookie.setMaxAge(300); // <- Change this value
} else if (tokenType == TokenType.ACCESS) {
    cookie.setMaxAge(180); // <- and this one
}
````

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

