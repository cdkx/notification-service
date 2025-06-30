package ru.eremin.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;


@Slf4j
@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(NotificationServiceApplication.class, args);
        log.info("notification service started on {}", new Date(context.getStartupDate()));
    }
}
