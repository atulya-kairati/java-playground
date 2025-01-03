package com.atulya.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerFactory;
import org.springframework.integration.file.dsl.FileInboundChannelAdapterSpec;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.time.Duration;

@Configuration
public class FilesAndSpringIntegration {

    @Bean(name = "myMsgChannel")
    MessageChannel messageChannel() {
        return MessageChannels.direct().getObject();
    }

    @Bean
    IntegrationFlow inboundFileSystemFlow() {
        File dir = new File("./junk/in");

        System.out.println(">>>>>>>" + dir.getAbsolutePath());

        FileInboundChannelAdapterSpec files = Files.inboundAdapter(dir)
                .autoCreateDirectory(true);


        return IntegrationFlow
                .from(files, p -> p.poller(pf -> PollerFactory.fixedRate(Duration.ofSeconds(1))))
                .channel(messageChannel())
                .get();
    }

    @Bean
    IntegrationFlow outboundFileSystemFlow() {
        File dir = new File("./junk/out");

        return IntegrationFlow
                .from(messageChannel())
                .handle((payload, headers) -> {
                    System.out.println(headers);
                    return payload;
                })
//                .transform(new FileToStringTransformer())
                .handle(Files.outboundAdapter(dir).autoCreateDirectory(true))
                .get();
    }

}
