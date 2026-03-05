package com.scaler.paymentservice.service;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("stripe")
public class StripePaymentWebhookService implements PaymentWebhookService {

    private final com.scaler.paymentservice.service.client.OrderClient orderClient;

    public StripePaymentWebhookService(com.scaler.paymentservice.service.client.OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @Override
    public void handleEvent(Event event) {

        switch (event.getType()) {

            case "checkout.session.completed" -> {
                Session session = (Session) event.getDataObjectDeserializer()
                        .getObject()
                        .orElseThrow();

                String orderId = session.getMetadata().get("orderId");
                String paymentId = session.getId();

                // Inform OrderService
                orderClient.markOrderPaid(orderId, paymentId);
            }

            default -> {
                // Ignore other events
            }
        }
    }
}
