-- =========================================================
-- Migration: Convert payment_gateway from VARCHAR to ENUM
-- Service  : PAYMENT-SERVICE
-- Reason   : Align DB schema with Java enum PaymentProvider
-- Java Enum: STRIPE, MOCK
-- =========================================================

ALTER TABLE payments
MODIFY COLUMN payment_gateway
ENUM ('STRIPE', 'MOCK')
NOT NULL;
