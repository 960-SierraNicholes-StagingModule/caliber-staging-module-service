# Startup Guide for Caliber Staging Module
This document should serve as guide to setting up the project on your own machine.
## Service Layer
### Java
1. Install Java 8 JDK & Maven & Java IDE of your choosing, we used Spring Tool Suite.
2. Fork project from GitHub repository.
3. Import project into your IDE as a Maven project.
4. Ensure that the following dependencies are part of the project using Spring Initializr
    - **Spring Boot version 2.4.1**:
        - Spring JDBC
        - Spring JPA
        - Spring Web
        - Spring Dev-Tools
        - Spring Test
        - Spring Security
    - **PostgreSQL Driver** for database connectivity, if using a different SQL dialect make sure you have the correct SQL driver for it.
    - **H2 Database** used for testing without persisting to a database.
    - **Firebase-admin** for Security and Authorization
    - **JUnit 4** for testing
    - **Mockito** for mocking Service & Repository layers
    - **Log4j** for logging.
5. Update the application.properties within src/main/resources to point to the correct database & port that you intend to use with the project. By Default the port is: 8081
6. Set up an environment variable called GOOGLE_APPLICATION_CREDENTIALS that will be set to an absolute path to your service-account.json for Firebase Authentication.
   [More information to setup environment variables](./SETUP-ENVIRONMENT.md)
   **Make sure to keep your service-account.json out of git version control**
7. Read the README.md & look over the pre-existing codebase to gain an understanding of the project.
### Database
1. Launch either a PostgreSQL RDS on AWS or Host a PostgreSQL database on your local machine. 
2. Update the ```spring.datasource.url``` property within the application.properties file to match the new RDS url.
3. Run the Java application to populate the new database with the correct tables.

## Angular Layer
1. Fork project from GitHub repository.
2. Install the latest version of Node.js & the Node Package Manager (NPM)
3. Run ```npm install @angular/cli``` to install the latest version of Angular from the terminal.
4. Open project in your preferred IDE, we used Visual Studio Code, and open the terminal.
5. Run ```npm install``` to install all dependencies needed for the project.
6. Look over the README.md & codebase to gain an understanding of the project.

## Deployment (Not essential to development)
1. Get Center of Excellence to provision you an EC2 with Jenkins & Docker installed.
2. Docker files are already included, just attach to a Jenkins pipeline.

### ***After completing all tasks above, your project is ready to use.***



       