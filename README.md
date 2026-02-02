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

## Project Structure
