package com.atulya.springintegration;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;


@MessagingGateway(
        defaultRequestChannel = "myMsgChannel",
        defaultReplyChannel = "myMsgChannel"
)
interface HelloClient {

    void sayHello(String text);

}

//@Configuration
public class HideUsingMessagingGateway {

    @Bean(name = "myMsgChannel")
    MessageChannel messageChannel() {
        return MessageChannels.direct().getObject();
    }

    @Bean
    IntegrationFlow buildFlow() {
        return IntegrationFlow
                .from(messageChannel())
                .transform((GenericTransformer<String, String>) String::toUpperCase)
                .handle((payload, headers) -> {
                    System.out.println("Payload buildFlow: " + payload);
                    return null;
                }).get();
    }
}

//@Component
class Runner implements ApplicationRunner {

    private final HelloClient helloClient;

    Runner(HelloClient helloClient) {
        this.helloClient = helloClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 100; i++) {
            helloClient.sayHello("hello");
        }
    }
}
