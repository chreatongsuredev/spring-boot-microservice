# Microservices Project

This repository contains a sample **microservices architecture** built for learning and practice purposes.
It demonstrates core microservices concepts such as **API Gateway**, **Authentication**, **Config Server**, and multiple business services.

---

## What are Microservices?

Microservices are an architectural approach to developing software applications as a collection of **small, independent services**.
Each service is responsible for a specific business capability and communicates with other services over a network, usually via REST APIs.

Key characteristics:
- Independent deployment
- Loose coupling
- Scalability
- Technology flexibility

---

## Architecture Overview

This project follows a typical **Spring Cloud–based microservices architecture** with centralized configuration and an API Gateway acting as the entry point.

---

## Services

### API Gateway
The API Gateway serves as a **single entry point** for all client requests.

Responsibilities:
- Routes requests to internal microservices
- Hides internal service structure from clients
- Centralizes cross-cutting concerns such as logging and security

---

### Authentication Service
The Authentication service is responsible for **user authentication and authorization**.

Responsibilities:
- Validates user credentials
- Issues authentication tokens
- Protects secured endpoints across services

---

### Product Service
The Product service handles **business logic related to products**.

Responsibilities:
- Exposes REST APIs for product operations
- Manages product-related data
- Communicates with other services through the gateway

---

### Config Server
The Config Server provides **centralized configuration management** for all microservices.

Responsibilities:
- Loads configuration from a remote repository
- Allows configuration changes without rebuilding services
- Ensures consistent configuration across environments

---

### Config Repository
The Config Repository stores **externalized configuration files** for all services.

Responsibilities:
- Maintains service-specific configuration
- Supports environment-based configuration
- Works together with the Config Server

---

## CI/CD, Containerization, and Deployment

Each microservice in this repository is designed to be **independently built, containerized, and deployed**, following real-world microservices best practices.

---

## CI/CD Pipeline

Every service contains its own **CI/CD pipeline**, enabling independent development and deployment.

Pipeline responsibilities:
- Build and test the service
- Create a Docker image
- Push the image to a container registry
- Prepare the service for deployment

This approach ensures:
- Independent service lifecycle
- Faster iteration
- Reduced coupling between services

---

## Docker Image

Each microservice includes a dedicated **Dockerfile**.

Responsibilities:
- Package the application and its dependencies
- Ensure consistent runtime environments
- Enable easy deployment across environments

Docker images are built per service, allowing services to scale and deploy independently.

---

## Deployment

Each service provides its own **deployment configuration**, following container-based deployment principles.

Key points:
- Services are deployed independently
- Configuration is externalized via Config Server
- Deployments can be updated without impacting other services

This design supports scalability, resilience, and maintainability.

---

## Service Definition

Each microservice exposes its functionality through a **Service definition**, enabling:
- Internal service-to-service communication
- Loose coupling between components
- Clear separation of responsibilities

This aligns with modern microservices deployment patterns.

---

## Microservice Independence

Each service in this project includes:
- Its own CI/CD pipeline
- Its own Docker image
- Its own deployment configuration
- Its own service definition

This structure reflects **real-world microservices architecture**, where services are autonomous and independently managed.

---

## Purpose

This project was built as a **hands-on learning and portfolio project** to demonstrate practical understanding of:
- Microservices architecture
- CI/CD pipelines
- Containerization with Docker
- Deployment strategies
- Service isolation and scalability


## Project Structure
