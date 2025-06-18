package ru.eremin.notificationservice.kafka;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.eremin.notificationservice.mail.EmailService;


@Slf4j
@AllArgsConstructor
@Service
public class KafkaConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listen(ConsumerRecord<String, String> record) {
        log.info("Received message: {} {}", record.key(), record.value());
        emailService.sendEmail(record.key(), record.value());
    }
}
