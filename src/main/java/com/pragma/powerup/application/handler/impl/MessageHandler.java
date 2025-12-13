package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.MessageRequestDto;
import com.pragma.powerup.application.handler.IMessageHandler;
import com.pragma.powerup.domain.api.IMessageServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageHandler implements IMessageHandler {

    private final IMessageServicePort messageService;

    @Override
    public void send(MessageRequestDto messageRequest) {
        messageService.send(messageRequest.getTo(), messageRequest.getMessage());
    }

}
