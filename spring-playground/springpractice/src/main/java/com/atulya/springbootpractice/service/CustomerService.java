package com.atulya.springbootpractice.service;

import com.atulya.springbootpractice.models.customer.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(int id);

    void insertCustomer(Customer customer);
}
