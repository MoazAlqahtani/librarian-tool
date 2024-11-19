# librarian-tool
This project demonstrates the need of managing libraries. It includes the following features:

## Features
* User registration and login with JWT authentication
* Password encryption using BCrypt
* Role-based authorization with Spring Security
* Customized access denied handling
* Logout mechanism
* Docker

## Technologies
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven
* Docker
 
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+

##There are two ways to run the application: 

To build and run the project in local enviroment, follow these steps:

* Clone the repository: `https://github.com/MoazAlqahtani/librarian-tool.git`
* Navigate to the project directory: cd librarian-tool
* Add database "library" to MySQL 
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run

To build and run the project in using Docker, follow these steps:
* Clone the repository: `https://github.com/MoazAlqahtani/librarian-tool.git`
* In Terminal run `docker build -t apis .`
* In terminal run `docker compose up -d`

-> The application will be available at http://localhost:18000.
