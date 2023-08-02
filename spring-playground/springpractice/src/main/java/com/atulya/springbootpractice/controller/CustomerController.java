package com.atulya.springbootpractice.controller;

import com.atulya.springbootpractice.models.customer.Customer;

import java.util.List;

public interface CustomerController {

    List<Customer> getCustomers();

    Customer getCustomerById(int customerId);
}
