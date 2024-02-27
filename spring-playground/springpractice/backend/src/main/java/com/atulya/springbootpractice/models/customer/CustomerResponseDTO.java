package com.atulya.springbootpractice.models.customer;

import java.util.List;

public record CustomerResponseDTO(
        long id,
        String name,
        String mail,
        int age,
        String gender,
        List<String> roles,
        String userName
) {
}
