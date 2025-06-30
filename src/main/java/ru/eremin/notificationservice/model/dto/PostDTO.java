package ru.eremin.notificationservice.model.dto;

import ru.eremin.notificationservice.model.Operation;


public record PostDTO(String email, Operation operation) {

}
