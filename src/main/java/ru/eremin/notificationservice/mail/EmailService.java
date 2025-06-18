package ru.eremin.notificationservice.mail;


public interface EmailService {
    void sendEmail(String emailTo, String message);
}
