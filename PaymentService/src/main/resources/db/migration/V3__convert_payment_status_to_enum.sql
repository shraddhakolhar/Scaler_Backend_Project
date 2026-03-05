-- =========================================================
-- Migration: Convert status from VARCHAR to ENUM
-- Service  : PAYMENT-SERVICE
-- Reason   : Align DB schema with Java enum PaymentStatus
-- Values   : pending, success, failed
-- =========================================================

ALTER TABLE payments
MODIFY COLUMN status
ENUM ('pending', 'success', 'failed')
NOT NULL;
