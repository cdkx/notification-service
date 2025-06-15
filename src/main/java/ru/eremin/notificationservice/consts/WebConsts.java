package ru.eremin.notificationservice.consts;

import lombok.experimental.UtilityClass;


@UtilityClass
public class WebConsts {
    public static final String API = "/api/v1";
    public static final String DELETE = "/delete";
    public static final String EMAILS = "/emails";
    public static final String CREATE = "/create";
    public static final String USER_CREATED = "User created.";
    public static final String USER_DELETED = "User deleted.";
    public static final String DO_NOT_REPLY = "<p> Please <b>do not reply</b> directly to this email</p>";
    public static final String SENDER_EMAIL = "no-reply@example.com";
    public static final String SENDER_TEXT = "no-reply";
}
