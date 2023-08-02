package com.atulya.springbootpractice.dao;

import com.atulya.springbootpractice.models.customer.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CustomerDao {

    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(int id);
}
