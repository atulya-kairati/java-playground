package com.atulya.springbootpractice.integrationtest;


import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerRegistrationDTO;
import com.atulya.springbootpractice.models.customer.CustomerResponseDTO;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT // this will start a full running server which will make the test closer to a production env
)
public class CustomerIntegrationTest {

    private static final String CUSTOMERS_URI = "/api/v1/customers";

    @Autowired
    WebTestClient webTestClient;

    @Test
    void canRegisterACustomer() {
        Faker faker = new Faker();

        // create a customer registration request
        CustomerRegistrationDTO request = new CustomerRegistrationDTO(
                faker.name().fullName(),
                faker.internet().emailAddress("test" + UUID.randomUUID()),
                "password", faker.random().nextInt(100),
                faker.demographic().sex().toLowerCase()
        );

        // send a request to the registration endpoint
        FluxExchangeResult<Void> result = webTestClient.post()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CustomerRegistrationDTO.class)// parsing obj
                .exchange() // send a req and gets a res
                .expectStatus()
                .isOk()
                .returnResult(Void.class);

        String jwt = result.getResponseHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

        // Now we check if our request was successful or not

        // get all customers
        List<CustomerResponseDTO> allCustomers = webTestClient.get()
                .uri(CUSTOMERS_URI)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<CustomerResponseDTO>() {
                }) // is to parse the incoming json into a list of given class
                .returnResult()
                .getResponseBody();


        assert allCustomers != null;
        long id = allCustomers.stream()
                .filter(c -> c.mail().equals(request.mail()))
                .findFirst()
                .orElseThrow()
                .id();



        CustomerResponseDTO expectedCustomer = new CustomerResponseDTO(
                id,
                request.name(),
                request.mail(),
                request.age(),
                request.gender(),
                List.of("ROLE_USER"),
                request.mail()
                );


        System.out.println(allCustomers.get(allCustomers.size() - 1));
        System.out.println(expectedCustomer);

        // make sure that the customer we registered exists
        assertThat(allCustomers)
                .contains(expectedCustomer);

        webTestClient.get()
                .uri(CUSTOMERS_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<CustomerResponseDTO>() {
                })
                .isEqualTo(expectedCustomer);
    }


    @Test
    void canDeleteCustomer() {
        Faker faker = new Faker();

        // create a customer registration request
        CustomerRegistrationDTO customer = new CustomerRegistrationDTO(
                faker.name().fullName(),
                faker.internet().emailAddress("test" + UUID.randomUUID()),
                "password", faker.random().nextInt(100),
                faker.demographic().sex().toLowerCase()
        );

        // send a request to the registration endpoint
        FluxExchangeResult<Void> result = webTestClient.post()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(customer), CustomerRegistrationDTO.class)// parsing obj
                .exchange() // send a req and gets a res
                .expectStatus()
                .isOk()
                .returnResult(Void.class);

        String jwt = result.getResponseHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

        // Now we check if our request was successful or not

        // get all customers
        List<CustomerResponseDTO> allCustomers = webTestClient.get()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<CustomerResponseDTO>() {
                }) // is to parse the incoming json into a list of given class
                .returnResult()
                .getResponseBody();


        // getting customer by id
        assert allCustomers != null;
        long id = allCustomers.stream()
                .filter(c -> c.mail().equals(customer.mail()))
                .findFirst()
                .orElseThrow()
                .id();

        // deleting customer
        webTestClient.delete()
                .uri(CUSTOMERS_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .exchange()
                .expectStatus()
                .isOk();


        // make sure the deleted Customer doesn't exist
        // since we have deleted the customer hence we can't use its jwt
        // so we register a new customer and use its jwt

        CustomerRegistrationDTO dummyCustomer = new CustomerRegistrationDTO(
                faker.name().fullName(),
                faker.internet().emailAddress("test" + UUID.randomUUID()),
                "password", faker.random().nextInt(100),
                faker.demographic().sex().toLowerCase()
        );

        String newJWT = webTestClient.post()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(dummyCustomer), CustomerRegistrationDTO.class)// parsing obj
                .exchange()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(HttpHeaders.AUTHORIZATION)
                .get(0);


        webTestClient.get()
                .uri(CUSTOMERS_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + newJWT)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void canUpdateCustomer() {
        Faker faker = new Faker();

        // create a customer registration request
        CustomerRegistrationDTO request = new CustomerRegistrationDTO(
                faker.name().fullName(),
                faker.internet().emailAddress("test" + UUID.randomUUID()),
                "password", faker.random().nextInt(100),
                faker.demographic().sex().toLowerCase()
        );

        // send a request to the registration endpoint
        FluxExchangeResult<Void> result = webTestClient.post()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CustomerRegistrationDTO.class)// parsing obj
                .exchange() // send a req and gets a res
                .expectStatus()
                .isOk()
                .returnResult(Void.class);

        String jwt = result.getResponseHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

        // Now we check if our request was successful or not
        // get all customers
        List<CustomerResponseDTO> allCustomers = webTestClient.get()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<CustomerResponseDTO>() {
                }) // is to parse the incoming json into a list of given class
                .returnResult()
                .getResponseBody();


        // getting customer by id
        assert allCustomers != null;
        long id = allCustomers.stream()
                .filter(c -> c.mail().equals(request.mail()))
                .findFirst()
                .orElseThrow()
                .id();

        // request to update age
        CustomerRegistrationDTO updateBody = new CustomerRegistrationDTO(
                null,
                null,
                "password", request.age() + 1,
                null
        );

        // update customer
        webTestClient.put()
                .uri(CUSTOMERS_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .body(Mono.just(updateBody), CustomerRegistrationDTO.class)// parsing obj
                .exchange() // send a req and gets a res
                .expectStatus()
                .isOk();

        // making sure customer got updated
        CustomerResponseDTO expectedCustomer = new CustomerResponseDTO(
                id,
                request.name(),
                request.mail(),
                updateBody.age(),
                request.gender(),
                List.of("ROLE_USER"),
                request.mail()
        );

        webTestClient.get()
                .uri(CUSTOMERS_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CustomerResponseDTO.class)
                .isEqualTo(expectedCustomer);
    }
}
