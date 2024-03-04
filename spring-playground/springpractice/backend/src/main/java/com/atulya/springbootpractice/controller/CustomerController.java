package com.atulya.springbootpractice.controller;

import com.atulya.springbootpractice.jwt.JWTUtils;
import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerRegistrationDTO;
import com.atulya.springbootpractice.models.customer.CustomerResponseDTO;
import com.atulya.springbootpractice.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService service;

    private final JWTUtils jwtUtils;

    public CustomerController(CustomerService service, JWTUtils jwtUtils) {
        this.service = service;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/whoami")
    public CustomerResponseDTO currentCustomer(@RequestAttribute("currentCustomer") CustomerResponseDTO currentCustomer) {
        return currentCustomer;
    }

    @GetMapping(value = "/customers")
    public List<CustomerResponseDTO> getCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping(value = "/customers/{customerId}")
    public CustomerResponseDTO getCustomerById(
            @PathVariable("customerId") long customerId
    ) {
        return service.getCustomerById(customerId);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<?> insertCustomer(@RequestBody CustomerRegistrationDTO crDto) {
        System.out.println(crDto);
        service.insertCustomer(crDto);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, jwtUtils.issueToken(crDto.mail(), "ROLE_USER"))
                .build();
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
            @RequestBody CustomerRegistrationDTO crDto
    ) {
        service.updateCustomerById(customerId, crDto);
    }
}
