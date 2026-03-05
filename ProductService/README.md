Product Service

The Product Service manages the product catalog for the e-commerce system. It provides APIs to retrieve product information, search products, and manage product data. This service is part of the Spring Boot microservices architecture and registers with Eureka Service Discovery so that other services can locate it.

The service is built using Spring Boot and uses Spring Data JPA with MySQL for storing product information. Flyway is used for managing database schema migrations, and Redis is used to cache frequently accessed product data to improve read performance.

The Product Service exposes several REST endpoints for product operations.

Product retrieval and search endpoints include:

GET /products – Retrieve a paginated list of products

GET /products/{id} – Retrieve details of a specific product

GET /products/search?q=keyword – Search products by keyword

Product management endpoints include:

POST /products – Create a new product

PUT /products/{id} – Update an existing product

DELETE /products/{id} – Delete a product from the catalog

To run the service, ensure that Eureka Server, MySQL, and Redis are running. The application can then be built and started using Maven.

mvn clean install
mvn spring-boot:run

By default, the service runs on port 8081, but the port and other configuration values such as database credentials, Redis settings, and external API URLs are provided using environment variables so that the service can run in different environments without changing the code.

The Product Service acts as the central catalog component of the e-commerce platform and is used by other services such as the Cart Service and Order Service to retrieve product information during the checkout workflow.
