package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.spi.IMessageSendPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageUseCaseTest {

    @Mock
    private IMessageSendPort messageSend;

    @InjectMocks
    private MessageUseCase messageUseCase;

    private static final String DESTINATION_PHONE = "+573001234567";
    private static final String MESSAGE_CONTENT = "Tu pedido ha sido despachado y está en camino.";

    @Test
    @DisplayName("send() debe llamar al puerto de envío de mensajes con los argumentos correctos")
    void send_ShouldCallMessageSendPortWithCorrectArguments() {
        messageUseCase.send(DESTINATION_PHONE, MESSAGE_CONTENT);
        verify(messageSend).send(DESTINATION_PHONE, MESSAGE_CONTENT);
    }

    @Test
    @DisplayName("send() debe manejar correctamente cadenas vacías para el mensaje")
    void send_ShouldHandleEmptyMessage() {
        String emptyMessage = "";
        messageUseCase.send(DESTINATION_PHONE, emptyMessage);
        verify(messageSend).send(DESTINATION_PHONE, emptyMessage);
    }

    @Test
    @DisplayName("send() debe manejar correctamente cadenas nulas para el número de teléfono")
    void send_ShouldHandleNullPhoneNumber() {
        String nullPhone = null;
        messageUseCase.send(nullPhone, MESSAGE_CONTENT);
        verify(messageSend).send(nullPhone, MESSAGE_CONTENT);
    }


}
