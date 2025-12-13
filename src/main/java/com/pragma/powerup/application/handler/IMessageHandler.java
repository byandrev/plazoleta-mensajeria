package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.MessageRequestDto;

public interface IMessageHandler {

    void send(MessageRequestDto messageRequest);

}
