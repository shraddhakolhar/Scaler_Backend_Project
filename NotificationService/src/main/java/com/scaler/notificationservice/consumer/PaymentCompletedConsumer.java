package com.scaler.notificationservice.consumer;

import com.scaler.notificationservice.event.PaymentCompletedEvent;
import com.scaler.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentCompletedConsumer {

    private final EmailService emailService;

    @KafkaListener(
            topics = "payment.completed",
            groupId = "notification-service"
    )
    public void handlePaymentCompleted(PaymentCompletedEvent event) {

        log.info(
                "Received PaymentCompletedEvent for orderId={} paymentId={}",
                event.getOrderId(),
                event.getPaymentId()
        );

        String subject = "Payment Successful";
        String body =
                "Hi,\n\n" +
                        "Your payment for order #" + event.getOrderId() + " was successful.\n" +
                        "Amount Paid: ₹" + event.getAmount() + "\n\n" +
                        "Happy shopping!";

        emailService.sendMail(
                event.getUserEmail(),
                subject,
                body
        );
    }
}
