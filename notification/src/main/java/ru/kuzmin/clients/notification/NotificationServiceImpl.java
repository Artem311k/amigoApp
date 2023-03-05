package ru.kuzmin.clients.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationRequest notificationRequest) {

        Notification notification = new Notification(
                notificationRequest.isFraudster(),
                notificationRequest.getName(),
                notificationRequest.getEmail());
        notificationRepository.saveAndFlush(notification);
        if(notificationRequest.isFraudster()) {
            log.info("User " + notificationRequest.getName() + "detected as a fraudster");
        }
        log.info("MESSAGE - '" + notification.getMessage() + "' WAS SENT");

    }
}
