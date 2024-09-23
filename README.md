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
The project includes Postman test collections, which can be found in the src/test/postman directory:

ByteBazaar.postman_collection.json: The Postman collection for testing the API endpoints.
ByteBazaar.postman_test_run.json: A sample test run result.
Important for Testing
The application uses JWT tokens for authentication, and the tokens have an expiration time defined in the application.properties file.

If you are running Postman tests, ensure that the expiration time of the JWT tokens is sufficient for the test run.
- **Option 1**: You can adjust the setTimeout function in the Postman tests if the tokens are expiring too quickly.
- **Option 2**: Adjust the expiration time in the application.properties by changing the application.security.jwt.access.expiration value:
````properties
application.security.jwt.access.expiration=180000
application.security.jwt.refresh.expiration=604800000
````

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

