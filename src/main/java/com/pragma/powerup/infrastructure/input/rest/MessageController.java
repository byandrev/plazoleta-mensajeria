package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.MessageRequestDto;
import com.pragma.powerup.application.handler.IMessageHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class MessageController {

    private final IMessageHandler messageHandler;

    @Operation(summary = "Send message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Message sent", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict to send message", content = @Content)
    })
    @PreAuthorize("hasAnyRole('PROPIETARIO', 'EMPLEADO')")
    @PostMapping("/")
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody MessageRequestDto messageRequest) {
        messageHandler.send(messageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
