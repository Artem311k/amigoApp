package ru.kuzmin.clients.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        scanBasePackages = {
                "ru.kuzmin.amigoApp.",
                "ru.kuzmin.amqp"
        }
)
@EnableEurekaClient
public class NotificationApp {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApp.class, args);
    }
}
