package ru.eremin.notificationservice.mail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static ru.eremin.notificationservice.consts.Environment.GMAIL_FROM;


@Slf4j
@AllArgsConstructor
@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMail = new SimpleMailMessage();


    public String send() {
        simpleMail.setFrom(GMAIL_FROM);
        simpleMail.setTo("viktor451@yandex.ru");
        simpleMail.setSubject("Java 20 new hot features");
        simpleMail.setText("Java 20 new hot features. there No attachments :(");
        javaMailSender.send(simpleMail);
        return "Mail Sent";
    }
}
