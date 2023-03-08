package ru.kuzmin.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import ru.kuzmin.clients.notification.NotificationRequest;

@RestController
@RequiredArgsConstructor
@EnableFeignClients
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping()
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {

        notificationService.sendNotification(notificationRequest);
//        return new NotificationResponse(message);

    }

}
