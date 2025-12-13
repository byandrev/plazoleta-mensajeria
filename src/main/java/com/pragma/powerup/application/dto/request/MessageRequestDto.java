package com.pragma.powerup.application.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class MessageRequestDto {

    @NotBlank(message = "El 'to' no puede estar vacio")
    @Pattern(
            regexp = "^(\\+\\d{1,12}|\\d{1,13})$",
            message = "Teléfono inválido. Máx 13 caracteres y puede iniciar con +"
    )
    private String to;

    @NotBlank(message = "El 'message' no puede estar vacio")
    private String message;

}
