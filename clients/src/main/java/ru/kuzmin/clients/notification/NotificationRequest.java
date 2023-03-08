package ru.kuzmin.clients.notification;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    private String email;

    private String name;

    private boolean isFraudster;

    private boolean isAlreadyRegistered;

}
