package com.atulya.springbootpractice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateMail extends RuntimeException {

    public DuplicateMail(String message) {
        super(message);
    }
}
