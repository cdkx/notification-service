package ru.eremin.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.eremin.notificationservice.mail.MailService;


@SpringBootApplication
public class NotificationServiceApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NotificationServiceApplication.class, args);
        System.out.println(context.getBean(MailService.class).send());

    }
}
