package com.atulya.springbootpractice.controller;

import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerRegistrationRequest;
import com.atulya.springbootpractice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/customers/{customerId}")
    public Customer getCustomerById(
            @PathVariable("customerId") long customerId
    ) {
        return service.getCustomerById(customerId);
    }

    @PostMapping(value = "/customers")
    public void insertCustomer(@RequestBody CustomerRegistrationRequest crr) {
        System.out.println(crr);
        service.insertCustomer(crr);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomerById(
            @PathVariable long customerId
    ) {
        service.deleteCustomerById(customerId);
    }

    @PutMapping("/customers/{customerId}")
    public void updateCustomerById(
            @PathVariable long customerId,
            @RequestBody CustomerRegistrationRequest crr
    ){
        service.updateCustomerById(customerId, crr);
    }
}
