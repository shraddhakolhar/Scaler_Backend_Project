package com.scaler.notificationservice.consumer;

import com.scaler.notificationservice.event.OrderPlacedEvent;
import com.scaler.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderPlacedConsumer {

    private final EmailService emailService;

    @KafkaListener(
            topics = "order.placed",
            groupId = "notification-service"
    )
    public void handleOrderPlaced(OrderPlacedEvent event) {

        log.info("Received OrderPlacedEvent: {}", event.getOrderId());

        String subject = "Order Placed Successfully";
        String body =
                "Hi,\n\n" +
                        "Your order #" + event.getOrderId() + " has been placed successfully.\n" +
                        "Total Amount: ₹" + event.getTotalAmount() + "\n\n" +
                        "Thank you for shopping with us!";

        emailService.sendMail(
                event.getUserEmail(),
                subject,
                body
        );
    }
}
