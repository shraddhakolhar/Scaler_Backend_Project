Payment Service

The Payment Service is responsible for handling payments in the e-commerce system. It generates payment links during the checkout process and processes payment confirmations after a successful transaction. This service is part of the Spring Boot microservices architecture and registers itself with Eureka Service Discovery so that other services can locate and communicate with it.

The Payment Service supports two different runtime profiles. The first profile is the mock profile, which is used for development and testing. In this mode, the system simulates a payment gateway so that the full checkout flow can be tested without using a real payment provider. The second profile is the stripe profile, which integrates the system with Stripe for real payment processing.

When a user places an order, the Order Service calls the Payment Service to create a payment link. The Payment Service generates a payment session and returns a payment URL that the user can use to complete the payment.

The main API exposed by the service is used to create a payment link for an order.

POST /payments/create-link

This endpoint receives payment details such as the order ID and amount and returns a payment URL that the client can redirect the user to in order to complete the payment.

When running in mock mode, the service provides a webhook endpoint that simulates successful payments. This allows developers to trigger payment completion events without relying on an external payment gateway.

POST /mock/webhook/payment-success

This endpoint accepts a payload containing the order ID and a gateway payment ID. When it is called, the service marks the payment as successful and notifies the Order Service so that the order status can be updated.

When running in Stripe mode, the service listens for Stripe webhook events.

POST /webhooks/stripe

Stripe sends webhook notifications when a checkout session is completed. When the service receives a checkout.session.completed event, it marks the payment as successful and notifies the Order Service that the order has been paid.

To run the Payment Service, make sure that Eureka Server, MySQL, and Kafka are running. The application can then be built and started using Maven.

mvn clean install
mvn spring-boot:run

By default, the service runs on port 8084, but the port can be configured using environment variables. Other configuration values such as database credentials, Kafka servers, Stripe keys, and JWT secrets are also externalized using environment variables so that the service can run in different environments without changing the code.

Flyway is used for managing database migrations, and migration scripts should be placed in the src/main/resources/db/migration directory.

Overall, the Payment Service is responsible for completing the final stage of the checkout workflow by ensuring that payments are processed successfully and that the Order Service is notified when an order has been paid.
