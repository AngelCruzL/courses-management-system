# Courses management system - API

This project is a simple API for a courses management system.

## Features

- Create, update, delete and list courses
- Create, update, delete and list students
- Create, update, delete and list instructors
- Enroll students in courses
- List courses that a student is enrolled in
- Remove a student from a course

## Technologies

- Java 17
- Spring Boot 3.0.6
- Docker
- Docker Compose
- MySQL 8.0

## What I learned?

- How to create a REST API using Spring Boot
- How to structure a Spring Boot project
- How to set up swagger documentation
- How implement the repository pattern

## Setup

With the purpose of make the execution of the application easier, I created a `docker-compose.yml` file to create the
instance of the database, if you want to use your own instance of MySQL, you can do it, just need to execute the content
of the `sql/init_test` file to create the databases and tables that the application needs.

If you want to use [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/) to create
the instance of the database, you need to execute the next command:

```bash
docker-compose up -d
```

This will to create a container with the instance of MySQL 8.0, and the database will be created automatically. In order
to avoid problems with this approach you need to get free the port 3306 of your machine, because this is the port that
the container uses.

## Usage

To run the application you need to execute the next command:

```bash
./mvnw spring-boot:run
```

This will to start the application in the port 8080, if you want to change the port, you can do it in the
`application.properties` file.

## Documentation

You can see the documentation of the API in the next
link: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)