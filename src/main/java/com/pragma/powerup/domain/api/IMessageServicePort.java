package com.pragma.powerup.domain.api;

public interface IMessageServicePort {

    void send(String to, String message);

}
