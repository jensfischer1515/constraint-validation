# JPA ConstraintViolationException example

This example tries to demonstrate, 
that the default message found in `javax.validation.ConstraintViolationException` does not provide enough context information
to be usable in log statements for analysing the `javax.validation.ConstraintViolation` involved.

## Setup

This project uses Spring Boot, Spring Data JPA, EclipseLink, H2 and Hibernate Validator
to demonstrate a constraint violation on a simple Person entity.
It provides a Gradle based build system and should be easily accessible in your favourite IDE.

## Run the test

```
$ ./gradlew clean build
```
