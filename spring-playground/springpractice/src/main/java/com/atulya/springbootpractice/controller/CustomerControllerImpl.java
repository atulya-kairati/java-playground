package com.atulya.springbootpractice.controller;

import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CustomerControllerImpl implements CustomerController{

    private final CustomerService service;

    public CustomerControllerImpl(CustomerService service) {
        this.service = service;
    }


    @Override
    @GetMapping(value = "/api/v1/customers")
    public List<Customer> getCustomers() {
        return service.getAllCustomers();
    }

    @Override
    @GetMapping(value = "/api/v1/customers/{customerId}")
    public Customer getCustomerById(
            @PathVariable("customerId") int customerId
    ) {
        return service.getCustomerById(customerId);
    }
}
