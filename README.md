## Prerequisites

Before running this application, please make sure you have prepared the required dependencies below.

### 1. Authentication Service

This application requires an authentication service running locally.  
You can obtain and run the service from the following repository:

https://github.com/livindizzy/simple-authentication

Make sure the authentication service is already running before starting this application.

### 2. Redis

Run the Redis container using Docker with the following command:

docker run -d -p 6379:6379 --name redis redis

### 3. Kafka

Run the Kafka container using Docker with the following command:

docker run -d -p 9092:9092 --name kafka apache/kafka

Ensure both Redis and Kafka containers are running before starting the application.

## Running the Application

After all dependencies above are running, you can start the application normally using your IDE.

## API Documentation

Once the application is running, you can access the Swagger API documentation at the following URL:

http://localhost:8086/swagger-ui/index.html

## Tech Stack

This application is built using the following technologies:

- Java Spring Boot
- PostgreSQL
- Redis
- Apache Kafka
- Docker
- JWT Authentication