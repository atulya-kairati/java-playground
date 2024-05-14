package com.atulya.springintegration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.PollerFactory;
import org.springframework.integration.dsl.context.IntegrationFlowContext;

import java.time.Duration;

//@Configuration
public class IntegrationFlowLauncher {

    @Bean
    ApplicationRunner runner(IntegrationFlowContext flowContext, CustomMessageSource messageSource) {
        return args -> {
            IntegrationFlow flow = buildFlow(messageSource, 2);

            flowContext.registration(flow)
                    .register()
                    .start();
        };
    }

    static IntegrationFlow buildFlow(CustomMessageSource messageSource, int seconds) {
        return IntegrationFlow
                .from(messageSource, p -> p.poller(pf -> PollerFactory.fixedRate(Duration.ofSeconds(seconds))))
                .transform((GenericTransformer<String, String>) String::toUpperCase)
                .handle((payload, headers) -> {
                    System.out.println("Payload buildFlow: " + payload);
                    return null;
                }).get();
    }

}
