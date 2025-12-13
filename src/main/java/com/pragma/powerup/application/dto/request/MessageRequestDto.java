package com.pragma.powerup.application.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MessageRequestDto {

    @NotBlank(message = "El 'to' no puede estar vacio")
    private String to;

    @NotBlank(message = "El 'message' no puede estar vacio")
    private String message;

}
