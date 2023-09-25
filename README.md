http://localhost:8080/actuator
http://localhost:8080/actuator/health
http://localhost:8080/swagger-ui/index.html#/
http://localhost:8080/h2-console/login.do

[![LinkedIn][linkedin-shield]][linkedin-url] [![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/project/overview?id=kivancbeser_rest-code-challenge)

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <h3 align="center">Rest Code Challenge</h3>
  <br />
 
  <p align="center">
    <br />
    <a href="https://github.com/kivancbeser/rest-code-challenge"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/kivancbeser/spring-boot-crud-example-master/blob/master/ProjectDemo.mp4">View Demo Video</a>
    ·
    <a href="https://github.com/kivancbeser/rest-code-challenge/issues">Report Bug</a>
    ·
    <a href="https://github.com/kivancbeser/rest-code-challenge/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Database Diagram](#databasediagram)
* [Explore Rest APIs](#explorerestapis)
  * [Account](#account)
* [Test Results](#testresults)
* [Done](#done)
* [TODO](#todo)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)

<!-- ABOUT THE PROJECT -->
## About The Project

This project created for 

It was created for the purpose of a web project from database to user. You can see the done features in [Done](#done) sections. 


### Built With

* [SpringBoot](https://start.spring.io/)
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [H2Database-Console](https://www.h2database.com/html/main.html)
* [Spring Actuator]
* [Spring Swagger]

## Why Spring Boot ?

Easy choice for small project spring boot is best choice.It provides a flexible way to configure Java Beans, XML configurations, and Database Transactions. It provides a powerful batch processing and manages REST endpoints. In Spring Boot, everything is auto configured; no manual configurations are needed.


## Why H2 Database ?

H2 is an open source Java SQL Database. It can be run in both embedded and server mode. We will use a file as a using h2.

## Why Spring Actuator ?

Spring Boot's 'Actuator' dependency is used to monitor and manage the Spring web application. We can use it to monitor and manage the application with the help of HTTP endpoints or with the JMX.

## Why Spring Swagger ?

Swagger in Spring Boot is an open-source project that helps generate documents of REST APIs for RESTful web services via a web browser. It renders the documentation of an API visually using web services. Open API is the specification, and Swagger is a tool that helps implement the API specification.

<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.

* Install JDK 17 version or more higher version.
```sh
https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
```

### Installation
 
1. Clone the repo
```sh
git clone https://github.com/kivancbeser/rest-code-challenge/tree/master.git
```
### Installation For Backend

1. Open the project in the IDE. (I Prefer Intellij Idea)

2. Execute Maven Clean & Install

3. Start the Spring Boot Application

<!-- USAGE EXAMPLES -->
## Usage

1. After all installations start the spring boot application.

2. Go to Chrome or another browser to check whether the application is up or not with the actuator

```sh
http://localhost:8080/actuator
```
3. Open swagger-ui to test the rest of the API application.
```sh
http://localhost:8080/swagger-ui/index.html#/
```
3. Also you can check the database with H2 Console.
```sh
http://localhost:8080/h2-console
```
Username and Password
```sh
admin
```

## Database Diagram
 <a href="https://github.com/kivancbeser/rest-code-challenge">
      <img src="/screenshots/RestCodeChallengeDBDiagram.png" width="1200">
 </a>

<!-- Explore Rest APIs -->
## Explore Rest APIs

The app defines the following CRUD APIs.

You can test them using Postman.



## Test Results
 <a href="https://github.com/kivancbeser/rest-code-challenge">
      <img src="/screenshots/CustomerControllerTestResult.png" width="1200">
 </a>

  <a href="https://github.com/kivancbeser/rest-code-challenge">
      <img src="/screenshots/WalletControllerTestResult.png" width="1200">
 </a>

<!-- Done -->
## Done
* Created Spring Boot Project
* Added dependencies
* Created Entities-Repositories-Services-Controller
* Added Validation for Entity
* Added Spring Actuator
* Added Spring Swagger
* Added GlobalExceptionHandler
* Added Unit Tests
* Sonar Scan Report
* Created Readme file
<!-- TODO -->
## TODO
* More comments for the spring side.
* Add More Test Cases
* 
<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/kivancbeser/rest-code-challenge/issues) for a list of proposed features (and known issues).



<!-- CONTRIBUTING -->
## Contributing

Contributions are what makes the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Kıvanç Enes Beşer - [@Linkedin_handle](https://www.linkedin.com/in/kivancenesbeser/) - beserkivanc@gmail.com

Project Link: [https://github.com/kivancbeser/rest-code-challenge](https://github.com/kivancbeser/rest-code-challenge)

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/kivancenesbeser
[project-screenshot]: /screenshots/screenshot.png
