package ru.eremin.notificationservice.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.eremin.notificationservice.mail.EmailService;
import ru.eremin.notificationservice.model.dto.PostDTO;

import static ru.eremin.notificationservice.consts.WebConsts.USER_CREATED;
import static ru.eremin.notificationservice.consts.WebConsts.USER_DELETED;


@Slf4j
@AllArgsConstructor
@Service
public class KafkaConsumer {
    private final EmailService emailService;
    private final ObjectMapper mapper;


    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listen(String record) {

        try {
            PostDTO postDTO = mapper.readValue(record, PostDTO.class);
            log.info("Received postDTO: email {} , operation {}", postDTO.email(), postDTO.operation());

            switch (postDTO.operation()) {
                case CREATE:
                    emailService.sendEmail(postDTO.email(), USER_CREATED);
                    break;
                case DELETE:
                    emailService.sendEmail(postDTO.email(), USER_DELETED);
                    break;
            }

        } catch (JsonMappingException e) {
            log.error("JsonProcessingException while sending email to topic");
        } catch (JsonProcessingException e) {
            log.error("JsonMappingException while sending email to topic");
        }
    }
}
