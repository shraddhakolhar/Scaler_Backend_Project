**UserService**: Handles user registration, authentication, and profile management within the platform. It manages user credentials securely and generates JWT tokens for authenticated access to other services.

**ProductService**: Manages the product catalog, including retrieving, creating, updating, and deleting product information. It integrates Redis caching to optimize product retrieval performance and reduce database load.

**CartService**: Responsible for shopping cart management, allowing users to add products to their cart, update quantities, and view cart contents before checkout.

**OrderService**: Handles the order lifecycle, including creating orders from cart items, tracking order status, and coordinating the checkout process with other services.

**PaymentService**: Processes payment transactions for orders and records payment status. It generates payment links and integrates with external payment providers or mock payment systems.

**NotificationService**: Listens to event messages through Kafka and sends notifications to users when important events occur, such as successful payments or order confirmations.

**ServiceDiscovery**: Implements dynamic service registration and discovery using Netflix Eureka, allowing microservices to locate and communicate with each other without hardcoding service addresses.
