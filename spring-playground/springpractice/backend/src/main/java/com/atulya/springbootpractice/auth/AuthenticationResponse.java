package com.atulya.springbootpractice.auth;

import com.atulya.springbootpractice.models.customer.CustomerResponseDTO;

public record AuthenticationResponse(
        String token,
        CustomerResponseDTO customer
) {
}
