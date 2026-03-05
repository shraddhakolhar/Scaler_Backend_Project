CREATE TABLE products
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(500) NULL,
    price DOUBLE NULL,
    category      VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);