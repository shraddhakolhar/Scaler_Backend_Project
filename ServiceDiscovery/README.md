Service Discovery provides a centralized registry where all microservices in the system register themselves. It is implemented using Netflix Eureka Server and enables services to discover and communicate with each other dynamically.

Each microservice registers with the Eureka server when it starts. Other services can then locate it using the service name instead of a fixed host and port.

To run the Service Discovery server:

mvn clean install
mvn spring-boot:run

By default, the Eureka dashboard is available at:

http://localhost:8761

The dashboard shows all registered services in the system and helps verify that the microservices are running correctly.
