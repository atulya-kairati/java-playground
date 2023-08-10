package com.atulya.springbootpractice.repositories;

import com.atulya.springbootpractice.models.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// @Repository // is optional here
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // JPA Query DSL
    boolean existsCustomerByMail(String mail);

}
