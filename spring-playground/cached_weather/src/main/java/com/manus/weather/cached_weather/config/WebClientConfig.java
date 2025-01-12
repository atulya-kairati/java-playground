package com.manus.weather.cached_weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient weatherClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.open-meteo.com/v1/forecast")
                .defaultHeader("Content-Type", "application/json")
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().responseTimeout(Duration.ofSeconds(2))
                ))
                .build();
    }

    @Bean
    public WebClient geoCodingClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://geocoding-api.open-meteo.com/v1/search")
                .defaultHeader("Content-Type", "application/json")
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().responseTimeout(Duration.ofSeconds(2))
                ))
                .build();
    }

}
