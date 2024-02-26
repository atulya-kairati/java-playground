package com.atulya.springbootpractice.models.customer;

import com.atulya.springbootpractice.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerDao customerDao;

    public CustomerUserDetailsService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // We have used email as our username
        return customerDao.getCustomerByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist: " + username));
    }
}
