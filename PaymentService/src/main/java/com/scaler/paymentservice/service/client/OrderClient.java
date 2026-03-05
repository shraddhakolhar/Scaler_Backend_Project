package com.scaler.paymentservice.service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClient {

    private final RestTemplate restTemplate;

    public OrderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void markOrderPaid(String orderId, String paymentId) {
        restTemplate.postForEntity(
                "http://ORDER-SERVICE/orders/" + orderId + "/paid",
                paymentId,
                Void.class
        );
    }
}
