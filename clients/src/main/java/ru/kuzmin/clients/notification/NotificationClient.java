package ru.kuzmin.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("NOTIFICATION")
public interface NotificationClient {
    @PostMapping("/api/v1/notification")
    NotificationResponse sendNotification(NotificationRequest notificationRequest);
}
