package com.atulya.springbootpractice.integrationtest;


import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerRegistrationRequest;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
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
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                faker.name().fullName(),
                faker.internet().emailAddress("test" + UUID.randomUUID()),
                faker.random().nextInt(100)
        );

        // send a request to the registration endpoint
        webTestClient.post()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CustomerRegistrationRequest.class)// parsing obj
                .exchange() // send a req and gets a res
                .expectStatus()
                .isOk();

        // Now we check if our request was successful or not

        // get all customers
        List<Customer> allCustomers = webTestClient.get()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                }) // is to parse the incoming json into a list of given class
                .returnResult()
                .getResponseBody();


        Customer expectedCustomer = new Customer(
                request.name(),
                request.mail(),
                request.age()
        );


        // make sure that the customer we registered exists
        assertThat(allCustomers).usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .contains(expectedCustomer);

        // getting customer by id

        assert allCustomers != null;
        long id = allCustomers.stream()
                .filter(c -> c.getMail().equals(request.mail()))
                .findFirst()
                .orElseThrow()
                .getId();

        expectedCustomer.setId(id);

        webTestClient.get()
                .uri(CUSTOMERS_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<Customer>() {
                })
                .isEqualTo(expectedCustomer);
    }


    @Test
    void canDeleteCustomer() {
        Faker faker = new Faker();

        // create a customer registration request
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                faker.name().fullName(),
                faker.internet().emailAddress("test" + UUID.randomUUID()),
                faker.random().nextInt(100)
        );

        // send a request to the registration endpoint
        webTestClient.post()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CustomerRegistrationRequest.class)// parsing obj
                .exchange() // send a req and gets a res
                .expectStatus()
                .isOk();

        // Now we check if our request was successful or not

        // get all customers
        List<Customer> allCustomers = webTestClient.get()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                }) // is to parse the incoming json into a list of given class
                .returnResult()
                .getResponseBody();


        // getting customer by id
        assert allCustomers != null;
        long id = allCustomers.stream()
                .filter(c -> c.getMail().equals(request.mail()))
                .findFirst()
                .orElseThrow()
                .getId();

        // deleting customer
        webTestClient.delete()
                .uri(CUSTOMERS_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();


        // make sure the deleted Customer doesn't exist
        webTestClient.get()
                .uri(CUSTOMERS_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void canUpdateCustomer() {
        Faker faker = new Faker();

        // create a customer registration request
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                faker.name().fullName(),
                faker.internet().emailAddress("test" + UUID.randomUUID()),
                faker.random().nextInt(100)
        );

        // send a request to the registration endpoint
        webTestClient.post()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CustomerRegistrationRequest.class)// parsing obj
                .exchange() // send a req and gets a res
                .expectStatus()
                .isOk();

        // Now we check if our request was successful or not
        // get all customers
        List<Customer> allCustomers = webTestClient.get()
                .uri(CUSTOMERS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                }) // is to parse the incoming json into a list of given class
                .returnResult()
                .getResponseBody();


        // getting customer by id
        long id = allCustomers.stream()
                .filter(c -> c.getMail().equals(request.mail()))
                .findFirst()
                .orElseThrow()
                .getId();

        // request to update age
        CustomerRegistrationRequest updateBody = new CustomerRegistrationRequest(
                null,
                null,
                request.age() + 1
        );

        // update customer
        webTestClient.put()
                .uri(CUSTOMERS_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateBody), CustomerRegistrationRequest.class)// parsing obj
                .exchange() // send a req and gets a res
                .expectStatus()
                .isOk();

        // making sure customer got updated
        Customer expectedCustomer = new Customer(
                id,
                request.name(),
                request.mail(),
                updateBody.age()
        );

        webTestClient.get()
                .uri(CUSTOMERS_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Customer.class)
                .isEqualTo(expectedCustomer);
    }
}
