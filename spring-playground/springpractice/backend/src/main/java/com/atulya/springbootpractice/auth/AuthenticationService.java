package com.atulya.springbootpractice.auth;

import com.atulya.springbootpractice.jwt.JWTUtils;
import com.atulya.springbootpractice.mappers.CustomerResponseDTOMapper;
import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final CustomerResponseDTOMapper responseDTOMapper;


    public AuthenticationService(
            AuthenticationManager authenticationManager,
            JWTUtils jwtUtils
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.responseDTOMapper = new CustomerResponseDTOMapper();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.mail(),
                request.password()
        ));

        Customer customer = (Customer) authentication.getPrincipal();

        CustomerResponseDTO responseDTO = responseDTOMapper.apply(customer);

        String token =  jwtUtils.issueToken(responseDTO.userName(), responseDTO.roles());

        return new AuthenticationResponse(token, responseDTO);
    }
}
