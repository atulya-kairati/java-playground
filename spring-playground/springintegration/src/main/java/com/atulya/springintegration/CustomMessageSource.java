package com.atulya.springintegration;

import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CustomMessageSource implements MessageSource<String> {
    @Override
    public Message<String> receive() {
        return MessageBuilder.withPayload("hello there!! @" + Instant.now()).build();
    }
}
