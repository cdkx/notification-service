package ru.eremin.notificationservice.mail;


public interface MailService {
    void send(String emailTo, String message);
}
