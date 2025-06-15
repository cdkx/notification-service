package ru.eremin.notificationservice.mail;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static ru.eremin.notificationservice.consts.WebConsts.*;


@Slf4j
@AllArgsConstructor
@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    @SneakyThrows
    public String send(String emailTo, String message) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(new InternetAddress(SENDER_EMAIL, SENDER_TEXT));
        helper.setTo(emailTo);
        helper.setSubject(message);
        helper.setText(message + DO_NOT_REPLY, true);
        javaMailSender.send(mimeMessage);
        return "Mail Sent";
    }
}
