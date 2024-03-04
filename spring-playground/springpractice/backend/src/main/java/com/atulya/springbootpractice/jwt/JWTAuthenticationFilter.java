package com.atulya.springbootpractice.jwt;

import com.atulya.springbootpractice.mappers.CustomerResponseDTOMapper;
import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerResponseDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    private final CustomerResponseDTOMapper responseDTOMapper;

    public JWTAuthenticationFilter(
            JWTUtils jwtUtils,
            UserDetailsService userDetailsService
    ) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.responseDTOMapper = new CustomerResponseDTOMapper();
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Extract the auth header
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // move to next filter if no token is present
            filterChain.doFilter(request, response);
            return;
        }

        // get JWT
        String jwt = authHeader.substring(7); // Bearer dwioh238938w3...

        // get subject
        String subject = jwtUtils.getSubject(jwt);

        if (
                subject != null && // subject is present
                        SecurityContextHolder.getContext().getAuthentication() == null // but not authenticated
        ) {
            // we authenticate the subject
            // get details of the subject
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

            // validate token
            boolean tokenValid = jwtUtils.isTokenValid(jwt, userDetails.getUsername());
            if (tokenValid) {

                // add user to the context

                // Create UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // set authentication
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                // setting current customer in the request so it can be used by the controller
                CustomerResponseDTO currentCustomer = responseDTOMapper.apply((Customer) userDetails);
                request.setAttribute("currentCustomer", currentCustomer);
            }
        }

        filterChain.doFilter(request, response);
    }
}
