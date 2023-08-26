package com.atulya.springbootpractice.dao;

import com.atulya.springbootpractice.AbstractTestContainers;
import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.repositories.CustomerRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Random;

class CustomerJPADataAccessServiceTest {

    private CustomerJPADataAccessService underTest;

    @Mock
    private CustomerRepository customerRepository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerJPADataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close(); // this will destroy the existing mock, so we get new mock everytime
    }

    @Test
    void getAllCustomers() {
        // When this method is called
        underTest.getAllCustomers();

        // Then findAll method on customerRepository must be invoked
        Mockito.verify(customerRepository).findAll();
    }

    @Test
    void getCustomerById() {
        long id = new Random().nextLong();

        underTest.getCustomerById(id);

        Mockito.verify(customerRepository).findById(id);

    }

    @Test
    void insertCustomer() {
        Customer mockedCustomer = Mockito.mock(Customer.class);

        underTest.insertCustomer(mockedCustomer);

        Mockito.verify(customerRepository).save(mockedCustomer);
    }

    @Test
    void existCustomerByEmail() {
        String email = new Faker().internet().emailAddress();

        underTest.existCustomerByEmail(email);

        Mockito.verify(customerRepository).existsCustomerByMail(email);
    }

    @Test
    void existCustomerById() {
        long id = new Random().nextLong();

        underTest.existCustomerById(id);

        Mockito.verify(customerRepository).existsById(id);
    }

    @Test
    void deleteCustomerById() {
        long id = new Random().nextLong();

        underTest.deleteCustomerById(id);

        Mockito.verify(customerRepository).deleteById(id);
    }

    @Test
    void updateCustomer() {
        Customer mockedCustomer = Mockito.mock(Customer.class);

        underTest.updateCustomer(mockedCustomer);

        Mockito.verify(customerRepository).save(mockedCustomer);
    }
}