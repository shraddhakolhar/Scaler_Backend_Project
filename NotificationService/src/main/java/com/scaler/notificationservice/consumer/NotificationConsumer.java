package com.scaler.notificationservice.consumer;

import com.scaler.notificationservice.event.UserRegisteredEvent;
import com.scaler.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(
            topics = "user.registered",
            groupId = "notification-service"
    )
    public void handleUserRegistered(UserRegisteredEvent event) {

        log.info("Received UserRegisteredEvent for {}", event.getEmail());

        String subject = "Welcome to Our E-Commerce Platform";
        String body =
                "Hi,\n\n" +
                        "Welcome to our platform! Your account has been created successfully.\n\n" +
                        "Happy shopping!";

        emailService.sendMail(
                event.getEmail(),
                subject,
                body
        );
    }
}
