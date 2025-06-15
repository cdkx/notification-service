package ru.eremin.notificationservice.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eremin.notificationservice.mail.MailService;

import static ru.eremin.notificationservice.consts.WebConsts.*;


@AllArgsConstructor
@RestController
@RequestMapping(API + EMAILS)
public class EmailController {
    private final MailService mailService;

    @PostMapping(CREATE)
    public ResponseEntity<String> sendMailCreated(@RequestParam String to) {
        String response = mailService.send(to, USER_CREATED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(DELETE)
    public ResponseEntity<String> sendMailDeleted(@RequestParam String to) {
        String response = mailService.send(to, USER_DELETED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
