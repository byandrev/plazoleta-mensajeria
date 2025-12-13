package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.MessageRequestDto;
import com.pragma.powerup.domain.api.IMessageServicePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageHandlerTest {

    @Mock
    private IMessageServicePort messageService;

    @InjectMocks
    private MessageHandler messageHandler;

    private static final String TO_PHONE = "+573001234567";
    private static final String MESSAGE_BODY = "Tu pedido está listo para ser enviado.";

    @Test
    @DisplayName("send() debe delegar la llamada al servicio de dominio con los valores mapeados del DTO")
    void send_ShouldCallMessageServicePort_WithCorrectMapping() {
        MessageRequestDto requestDto = MessageRequestDto.builder().build();
        requestDto.setTo(TO_PHONE);
        requestDto.setMessage(MESSAGE_BODY);

        messageHandler.send(requestDto);

        verify(messageService).send(TO_PHONE, MESSAGE_BODY);
    }

    @Test
    @DisplayName("send() debe manejar DTOs con campos vacíos y delegarlos")
    void send_ShouldHandleEmptyFields() {
        String emptyString = "";
        MessageRequestDto requestDto = MessageRequestDto.builder().build();
        requestDto.setTo(emptyString);
        requestDto.setMessage(emptyString);

        messageHandler.send(requestDto);

        verify(messageService).send(emptyString, emptyString);
    }

    @Test
    @DisplayName("send() debe manejar DTOs con valores nulos y delegarlos")
    void send_ShouldHandleNullFields() {
        MessageRequestDto requestDto = MessageRequestDto.builder().build();
        requestDto.setTo(null);
        requestDto.setMessage(null);

        messageHandler.send(requestDto);

        verify(messageService).send(null, null);
    }

}
