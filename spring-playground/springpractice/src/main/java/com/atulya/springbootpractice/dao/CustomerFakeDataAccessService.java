package com.atulya.springbootpractice.dao;

import com.atulya.springbootpractice.models.customer.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fake") // annotation is used to mark the DAO classes, so that spring can do DI
public class CustomerFakeDataAccessService implements CustomerDao {

    static List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer(1, "Manus", "Manus@mail.com", 24));
        customers.add(new Customer(2, "Potas", "potas@mail.com", 27));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(int customerId) {

        return customers.stream()
                .filter((c) -> c.getId() == customerId)
                .findFirst();
    }
}
