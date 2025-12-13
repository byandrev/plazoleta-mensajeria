package com.pragma.powerup.domain.spi;

public interface IMessageSendPort {

    void send(String to, String message);

}
