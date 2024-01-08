# Quiz Application with Spring Boot, Eureka Server, and OpenFeign

This repository contains a simple quiz application built with Spring Boot. The application is designed to demonstrate microservices architecture using Eureka Server for service registry and OpenFeign for communication between services.

## Components

1. **Eureka Server (service-registry):**
   - The `service-registry` module is responsible for acting as the service registry using Netflix Eureka. All microservices will register themselves with this service registry.

2. **Quiz Service (quiz-service):**
   - The `quiz-service` module is the main service handling quiz-related functionalities. It provides APIs for managing quizzes, questions, and quiz submissions.

3. **Question Service (question-service):**
   - The `question-service` module is dedicated to managing quiz questions. It provides APIs for creating, updating, and retrieving quiz questions.

## Technologies Used

- Spring Boot: Java-based framework for microservices development.
- Eureka Server: Service registry for microservices.
- OpenFeign: Declarative REST client for simplifying microservices communication.
- Maven: Project management and build tool.
