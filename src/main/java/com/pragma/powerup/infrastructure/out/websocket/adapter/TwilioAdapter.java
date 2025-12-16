package com.pragma.powerup.infrastructure.out.websocket.adapter;

import com.pragma.powerup.domain.spi.IMessageSendPort;
import com.pragma.powerup.infrastructure.exception.InfraException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TwilioAdapter implements IMessageSendPort {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String fromNumber;

    public TwilioAdapter(
            @Value("${twilio.account-sid}") String accountSid,
            @Value("${twilio.auth-token}") String authToken
    ) {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void send(String to, String messageBody) {
        try {
            Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(fromNumber),
                    messageBody
            ).create();
        } catch (Exception e) {
            throw new InfraException(
                "Error al enviar SMS con Twilio: " + e.getMessage(),
                HttpStatus.BAD_REQUEST.value()
            );
        }
    }

}
