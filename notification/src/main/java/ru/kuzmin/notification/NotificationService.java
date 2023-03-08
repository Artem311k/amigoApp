package ru.kuzmin.notification;

import ru.kuzmin.clients.notification.NotificationRequest;

public interface NotificationService {

    void sendNotification(NotificationRequest notificationRequest);
}
