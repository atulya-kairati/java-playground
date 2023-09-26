package com.atulya.springbootpractice.dao;

import com.atulya.springbootpractice.AbstractTestContainers;
import com.atulya.springbootpractice.mappers.CustomerRowMapper;
import com.atulya.springbootpractice.models.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerJDBCDataAccessServiceTest extends AbstractTestContainers {

    private CustomerJDBCDataAccessService underTest;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        underTest = new CustomerJDBCDataAccessService(
                // this needs a data source
                getJdbcTemplate(),
                customerRowMapper
        );
    }

    @Test
    void getAllCustomers() {

        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().emailAddress() + UUID.randomUUID(), // making sure email is unique
                FAKER.number().numberBetween(18, 100)
        );

        underTest.insertCustomer(customer);

        List<Customer> result = underTest.getAllCustomers();

        // test
        assertThat(result).isNotEmpty();
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().emailAddress() + UUID.randomUUID(), // making sure email is unique
                FAKER.number().numberBetween(18, 100)
        );

        underTest.insertCustomer(customer);

        // getting id for the test
        long id = underTest.getAllCustomers()
                .stream()
                .filter(cus -> cus.getMail().equals(customer.getMail()))
                .findFirst()
                .orElseThrow()
                .getId();

        Customer result = underTest.getCustomerById(id).orElseThrow();

        assertThat(result).satisfies(cus -> {
            assertThat(cus.getId()).isEqualTo(id);
            assertThat(cus.getName()).isEqualTo(customer.getName());
            assertThat(cus.getMail()).isEqualTo(customer.getMail());
            assertThat(cus.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void resultShouldBeEmptyGetCustomerById() {
        long id = -1; // since sequence starts from 1 so customer with id -1 will never exist

        Optional<Customer> result = underTest.getCustomerById(id);

        assertThat(result).isEmpty();
    }

    @Test
    void insertCustomer() {
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().emailAddress() + UUID.randomUUID(), // making sure email is unique
                FAKER.number().numberBetween(18, 100)
        );

        underTest.insertCustomer(customer);

        Customer result = underTest.getAllCustomers()
                .stream()
                .filter(cus -> cus.getMail().equals(customer.getMail()))
                .findFirst()
                .orElseThrow();

        // test
        assertThat(result).satisfies(cus -> {
            assertThat(cus.getName()).isEqualTo(customer.getName());
            assertThat(cus.getMail()).isEqualTo(customer.getMail());
            assertThat(cus.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void existCustomerByEmail() {
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().emailAddress() + UUID.randomUUID(), // making sure email is unique
                FAKER.number().numberBetween(18, 100)
        );

        underTest.insertCustomer(customer);

        boolean result = underTest.existCustomerByEmail(customer.getMail());

        assertThat(result).isTrue();
    }

    @Test
    void noSuchMailExistCustomerByEmail() {
        String email = FAKER.internet().emailAddress();

        boolean result = underTest.existCustomerByEmail(email);

        assertThat(result).isFalse();
    }

    @Test
    void existCustomerById() {
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().emailAddress() + UUID.randomUUID(), // making sure email is unique
                FAKER.number().numberBetween(18, 100)
        );

        underTest.insertCustomer(customer);

        // getting id for the test
        long id = underTest.getAllCustomers()
                .stream()
                .filter(cus -> cus.getMail().equals(customer.getMail()))
                .findFirst()
                .orElseThrow()
                .getId();

        boolean result = underTest.existCustomerById(id);

        assertThat(result).isTrue();
    }

    @Test
    void noSuchIdExistCustomerById() {
        long id = -1;

        boolean result = underTest.existCustomerById(id);

        assertThat(result).isFalse();
    }

    @Test
    void deleteCustomerById() {
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().emailAddress() + UUID.randomUUID(), // making sure email is unique
                FAKER.number().numberBetween(18, 100)
        );

        underTest.insertCustomer(customer);

        // getting id for the test
        long id = underTest.getAllCustomers()
                .stream()
                .filter(cus -> cus.getMail().equals(customer.getMail()))
                .findFirst()
                .orElseThrow()
                .getId();

        underTest.deleteCustomerById(id);

        Optional<Customer> result = underTest.getCustomerById(id);

        assertThat(result).isEmpty();
    }

    @Test
    void updateCustomer() {
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().emailAddress() + UUID.randomUUID(), // making sure email is unique
                FAKER.number().numberBetween(18, 100)
        );

        underTest.insertCustomer(customer);

        // getting id for the test
        long id = underTest.getAllCustomers()
                .stream()
                .filter(cus -> cus.getMail().equals(customer.getMail()))
                .findFirst()
                .orElseThrow()
                .getId();

        customer.setId(id);

        String newName = "Manus Chaubey";
        customer.setName(newName);

        underTest.updateCustomer(customer);

        Customer result = underTest.getCustomerById(customer.getId())
                .orElseThrow();

        assertThat(result).satisfies(cus -> assertThat(cus.getName()).isEqualTo(newName));

    }
}