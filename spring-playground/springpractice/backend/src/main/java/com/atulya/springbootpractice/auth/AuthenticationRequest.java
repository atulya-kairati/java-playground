package com.atulya.springbootpractice.auth;

public record AuthenticationRequest(
        String mail,
        String password
) {
}
