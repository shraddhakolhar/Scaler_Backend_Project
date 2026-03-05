package com.scaler.notificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent {

    private Long orderId;
    private String userEmail;
    private BigDecimal totalAmount;
}
