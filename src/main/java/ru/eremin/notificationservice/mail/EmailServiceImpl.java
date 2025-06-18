package ru.eremin.notificationservice.mail;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static ru.eremin.notificationservice.consts.WebConsts.*;


@Slf4j
@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Override
    @SneakyThrows
    public void sendEmail(String emailTo, String message) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom(new InternetAddress(SENDER_EMAIL, SENDER_TEXT));
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
        mimeMessage.setSubject(message);
        mimeMessage.setHeader("Content-Type", "text/html");
        mimeMessage.setContent(message + DO_NOT_REPLY, "text/html; charset=utf-8");
        mimeMessage.saveChanges();
        javaMailSender.send(mimeMessage);
    }
}
