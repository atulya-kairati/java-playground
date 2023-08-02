package com.atulya.springbootpractice.service;

import com.atulya.springbootpractice.models.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(int id);
}
