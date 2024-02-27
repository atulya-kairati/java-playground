package com.atulya.springbootpractice.mappers;

import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerResponseDTO;

import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomerResponseDTOMapper implements Function<Customer, CustomerResponseDTO> {
    @Override
    public CustomerResponseDTO apply(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getMail(),
                customer.getAge(),
                customer.getGender(),
                customer.getAuthorities().stream().map(e -> e.getAuthority()).collect(Collectors.toList()),
                customer.getUsername()
        );
    }
}
