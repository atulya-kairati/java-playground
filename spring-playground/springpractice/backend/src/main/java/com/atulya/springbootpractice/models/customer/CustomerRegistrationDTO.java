package com.atulya.springbootpractice.models.customer;

public record CustomerRegistrationDTO(
        String name,
        String mail,
        String password,
        Integer age,
        String gender
) {
}
