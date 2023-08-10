package com.atulya.springbootpractice.models.customer;

public record CustomerRegistrationRequest(
        String name,
        String mail,
        Integer age
) {
}
