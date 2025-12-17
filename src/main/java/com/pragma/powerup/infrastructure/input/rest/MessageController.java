package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.MessageRequestDto;
import com.pragma.powerup.application.handler.IMessageHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
@Tag(name = "Mensajería", description = "Endpoints para la gestión de notificaciones SMS y comunicaciones")
public class MessageController {

    private final IMessageHandler messageHandler;

    @Operation(
            summary = "Enviar un mensaje de notificación",
            description = "Permite enviar un mensaje SMS a un destinatario específico. Requiere roles de PROPIETARIO o EMPLEADO."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mensaje enviado exitosamente", content = @Content),
            @ApiResponse(responseCode = "403", description = "No tiene permisos para realizar esta acción", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflicto al intentar enviar el mensaje", content = @Content)
    })
    @PreAuthorize("hasAnyRole('PROPIETARIO', 'EMPLEADO')")
    @PostMapping("/")
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody MessageRequestDto messageRequest) {
        messageHandler.send(messageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
