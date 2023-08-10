package com.atulya.springbootpractice.service;

import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerRegistrationRequest;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(int id);

    void insertCustomer(CustomerRegistrationRequest customerRegistrationRequest);

    void deleteCustomerById(int id);

    void updateCustomerById(int id, CustomerRegistrationRequest crr);
}
