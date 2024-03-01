package com.atulya.springbootpractice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
public class CorsConfig {

    @Value("#{'${cors.allowed-origins}'.split(',')}") // this is a SpEL expression :< Fuuuuuuuuuk
    List<String> allowedOrigins;

    @Value("#{'${cors.allowed-methods}'.split(',')}")
    List<String> allowedMethods;

    @Value("#{'${cors.allowed-headers}'.split(',')}")
    List<String> allowedHeader;

    @Value("#{'${cors.exposed-headers}'.split(',')}")
    List<String> exposedHeaders;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowedOrigins);
        configuration.setAllowedMethods(allowedMethods);
        configuration.setAllowedHeaders(allowedHeader);
        configuration.setExposedHeaders(exposedHeaders);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}
