package com.atulya.springbootpractice.integrationtest;

import com.atulya.springbootpractice.auth.AuthenticationRequest;
import com.atulya.springbootpractice.auth.AuthenticationResponse;
import com.atulya.springbootpractice.models.customer.CustomerRegistrationDTO;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT // this will start a full running server which will make the test closer to a production env
)
public class AuthenticationIntegrationTest {

    private static final String AUTH_URI = "/api/v1/auth";
    private static final String CUSTOMERS_URI = "/api/v1/customers";

    @Autowired
    WebTestClient webTestClient;


    @Test
    void canLogin() {
        Faker faker = new Faker();

        // create a customer registration request
        CustomerRegistrationDTO request = new CustomerRegistrationDTO(
                faker.name().fullName(),
                faker.internet().emailAddress("test" + UUID.randomUUID()),
                "password", faker.random().nextInt(100),
                faker.demographic().sex().toLowerCase()
        );

        AuthenticationRequest loginRequest = new AuthenticationRequest(
                request.mail(),
                request.password()
        );

        // try login without registration but should fail
        webTestClient.post()
                .uri(AUTH_URI + "/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginRequest), AuthenticationRequest.class)
                .exchange()
                .expectStatus()
                .isUnauthorized();

        // register a customer
        webTestClient.post()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CustomerRegistrationDTO.class)// parsing obj (sent)
                .exchange() // send a req and gets a res
                .expectStatus()
                .isOk();

        // login
        FluxExchangeResult<Void> result = webTestClient.post()
                .uri(AUTH_URI + "/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginRequest), AuthenticationRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Void.class);

        String token = result.getResponseHeaders().get(HttpHeaders.AUTHORIZATION).get(0);


        // check is token received is valid
        webTestClient.get()
                .uri(CUSTOMERS_URI)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();

    }
}
