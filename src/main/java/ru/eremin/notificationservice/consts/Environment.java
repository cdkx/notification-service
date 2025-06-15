package ru.eremin.notificationservice.consts;

import lombok.experimental.UtilityClass;


@UtilityClass
public class Environment {
    public static final String GMAIL_FROM = System.getenv("gmailSenderLogin");
}
