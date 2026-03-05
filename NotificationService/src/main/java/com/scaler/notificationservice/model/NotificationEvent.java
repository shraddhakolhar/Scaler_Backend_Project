package com.scaler.notificationservice.model;

import lombok.Data;

@Data
public class NotificationEvent {

    private String eventType;
    private String email;
    private String firstName;
}
