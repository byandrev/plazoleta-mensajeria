package com.pragma.powerup.infrastructure.out.websocket.entity;

import lombok.Data;

@Data
public class MessageEntity {

    private String to;

    private String message;

}
