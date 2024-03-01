package com.atulya.springbootpractice.service;

import com.atulya.springbootpractice.models.customer.CustomerRegistrationDTO;
import com.atulya.springbootpractice.models.customer.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO getCustomerById(long id);

    void insertCustomer(CustomerRegistrationDTO crDto);

    void deleteCustomerById(long id);

    void updateCustomerById(long id, CustomerRegistrationDTO crDto);
}
