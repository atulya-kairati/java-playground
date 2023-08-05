package com.atulya.springbootpractice.controller;

import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService service;


    @GetMapping(value = "/customers")
    public List<Customer> getCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping(value = "customers/{customerId}")
    public Customer getCustomerById(
            @PathVariable("customerId") int customerId
    ) {
        return service.getCustomerById(customerId);
    }
}
