package ru.eremin.notificationservice.controller;


import lombok.AllArgsConstructor;
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
    public void sendMailCreated(@RequestParam String to) {
        mailService.send(to, USER_CREATED);
    }

    @PostMapping(DELETE)
    public void sendMailDeleted(@RequestParam String to) {
        mailService.send(to, USER_DELETED);
    }
}
