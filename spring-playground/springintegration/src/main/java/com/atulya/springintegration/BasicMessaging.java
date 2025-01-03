package com.atulya.springintegration;


import ch.qos.logback.core.util.TimeUtil;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.time.Duration;
import java.time.Instant;

//@Configuration
public class BasicMessaging {

    @Bean(name = "myMsgChannel")
    MessageChannel messageChannel() {
        return MessageChannels.direct().getObject();
    }

    @Bean
    IntegrationFlow messageSenderFlow(CustomMessageSource messageSource) {
        return IntegrationFlow.from(
//                        (MessageSource<String>) () -> MessageBuilder.withPayload("hello there! @" + Instant.now()).build(),
                        messageSource,
                        p -> p.poller(pf -> PollerFactory.fixedRate(Duration.ofSeconds(1))) // controls when the message is invoked
                )
                .channel(messageChannel())
                .get();
    }

    @Bean
    IntegrationFlow messageListenerFlow() {
        return IntegrationFlow
                .from(messageChannel())
                .handle((payload, headers) -> {
                    System.out.println("Payload: " + payload);
                    return null;
                })
                .get();
    }

    @Bean
    ApplicationRunner runner1(MessageChannel myMsgChannel) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                myMsgChannel.send(MessageBuilder.withPayload("General Kenobi! @" + Instant.now()).build());
            }
        };
    }

//    @Bean
//    IntegrationFlow messageProcess() {
//        return IntegrationFlow.from(
//                        (MessageSource<String>) () -> MessageBuilder.withPayload("hello me! @" + Instant.now()).build(),
//                        poller -> poller.poller(pm -> pm.fixedRate(1000))
//                )
//                .transform((GenericTransformer<String, String>) String::toUpperCase)
//                .handle((payload, headers) -> {
//                    System.out.println("Payload: " + payload);
//                    return null;
//                })
//                .get();
//    }

}
