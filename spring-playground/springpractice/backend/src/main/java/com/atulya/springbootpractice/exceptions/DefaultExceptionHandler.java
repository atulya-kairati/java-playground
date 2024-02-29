package com.atulya.springbootpractice.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorMsg> handleAllException(
            Exception e,
            HttpServletRequest request
    ) {

        System.out.println(e);

        ApiErrorMsg errorMsg = new ApiErrorMsg(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorMsg> handleResourceNotFoundException(
            ResourceNotFoundException rnfe,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        ApiErrorMsg errorMsg = new ApiErrorMsg(
                request.getRequestURI(),
                rnfe.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ApiErrorMsg> handleInsufficientAuthenticationException(
            InsufficientAuthenticationException e,
            HttpServletRequest request
    ) {

        ApiErrorMsg errorMsg = new ApiErrorMsg(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMsg, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiErrorMsg> handleDuplicateResourceException(
            DuplicateResourceException e,
            HttpServletRequest request
    ) {

        ApiErrorMsg errorMsg = new ApiErrorMsg(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMsg, HttpStatus.CONFLICT);
    }
}
