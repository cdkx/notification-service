package ru.eremin.notificationservice.mail;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.eremin.notificationservice.consts.WebConsts.*;


@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    private EmailServiceImpl emailService;

    private final String testEmail = "test@example.com";
    private final String testMessage = "Test Message";

    @BeforeEach
    void setUp() {
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @Test
    void sendEmail_shouldConfigureAndSendEmail() throws MessagingException {
        emailService.sendEmail(testEmail, testMessage);

        verify(mimeMessage).setFrom(any(InternetAddress.class));
        verify(mimeMessage).setRecipient(eq(Message.RecipientType.TO), any(InternetAddress.class));
        verify(mimeMessage).setSubject(testMessage);
        verify(mimeMessage).setHeader("Content-Type", "text/html");
        verify(mimeMessage).setContent(testMessage + DO_NOT_REPLY, "text/html; charset=utf-8");
        verify(mimeMessage).saveChanges();
        verify(javaMailSender).send(mimeMessage);
    }

    @Test
    void sendEmail_shouldSetCorrectSenderAndRecipient() throws Exception {
        ArgumentCaptor<InternetAddress> fromCaptor = ArgumentCaptor.forClass(InternetAddress.class);
        ArgumentCaptor<InternetAddress> toCaptor = ArgumentCaptor.forClass(InternetAddress.class);

        emailService.sendEmail(testEmail, testMessage);

        verify(mimeMessage).setFrom(fromCaptor.capture());
        verify(mimeMessage).setRecipient(eq(Message.RecipientType.TO), toCaptor.capture());
        InternetAddress fromAddress = fromCaptor.getValue();
        InternetAddress toAddress = toCaptor.getValue();
        assertEquals(SENDER_EMAIL, fromAddress.getAddress());
        assertEquals(SENDER_TEXT, fromAddress.getPersonal());
        assertEquals(testEmail, toAddress.getAddress());
    }

    @Test
    void sendEmail_shouldSetCorrectContent() throws Exception {
        ArgumentCaptor<String> contentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> contentTypeCaptor = ArgumentCaptor.forClass(String.class);

        emailService.sendEmail(testEmail, testMessage);

        verify(mimeMessage).setContent(contentCaptor.capture(), contentTypeCaptor.capture());
        String content = contentCaptor.getValue();
        String contentType = contentTypeCaptor.getValue();
        assertEquals(testMessage + DO_NOT_REPLY, content);
        assertEquals("text/html; charset=utf-8", contentType);
    }
}
