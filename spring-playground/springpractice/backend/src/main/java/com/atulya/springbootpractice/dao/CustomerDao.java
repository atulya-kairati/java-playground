package com.atulya.springbootpractice.dao;

import com.atulya.springbootpractice.models.customer.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerDao {

    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(long id);

    void insertCustomer(Customer customer);

    boolean existCustomerByEmail(String mail);

    boolean existCustomerById(long id);

    void deleteCustomerById(long id);

    void updateCustomer(Customer customer);
}
