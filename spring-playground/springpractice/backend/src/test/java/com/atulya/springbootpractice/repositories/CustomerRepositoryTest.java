package com.atulya.springbootpractice.repositories;

import com.atulya.springbootpractice.AbstractTestContainers;
import com.atulya.springbootpractice.models.customer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Stop the main database otherwise it will connect to it
 * Also we extend to the AbstractTestContainer to connect to our test DB
 */

@DataJpaTest // to get JPA repository and the components it needs
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // disable use of embedded DB
@Import({TestConfig.class})
class CustomerRepositoryTest extends AbstractTestContainers {

    @Autowired
    CustomerRepository underTest;

    @Test
    void existsCustomerByMail() {
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().emailAddress() + UUID.randomUUID(), // making sure email is unique
                FAKER.number().numberBetween(18, 100),
                FAKER.demographic().sex(),
                "password");

        underTest.save(customer);

        boolean result = underTest.existsCustomerByMail(customer.getMail());

        assertThat(result).isTrue();

    }

    @Test
    void noSuchMailExistsCustomerByMail() {
        String mail = FAKER.internet().emailAddress() + UUID.randomUUID();

        boolean result = underTest.existsCustomerByMail(mail);

        assertThat(result).isFalse();
    }
}