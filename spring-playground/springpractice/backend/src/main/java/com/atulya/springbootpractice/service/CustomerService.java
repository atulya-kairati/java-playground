package com.atulya.springbootpractice.service;

import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerRegistrationRequest;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(long id);

    void insertCustomer(CustomerRegistrationRequest customerRegistrationRequest);

    void deleteCustomerById(long id);

    void updateCustomerById(long id, CustomerRegistrationRequest crr);
}
