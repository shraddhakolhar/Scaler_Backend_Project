package com.scaler.notificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCompletedEvent {

    private Long paymentId;
    private Long orderId;
    private String userEmail;
    private BigDecimal amount;
    private String gatewayPaymentId;
}
