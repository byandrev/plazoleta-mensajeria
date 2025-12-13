package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IMessageServicePort;
import com.pragma.powerup.domain.spi.IMessageSendPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageUseCase implements IMessageServicePort {

    private final IMessageSendPort messageSend;

    @Override
    public void send(String to, String message) {
        messageSend.send(to, message);
    }

}
