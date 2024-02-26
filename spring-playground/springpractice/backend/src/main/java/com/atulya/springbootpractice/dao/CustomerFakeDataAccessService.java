package com.atulya.springbootpractice.dao;

import com.atulya.springbootpractice.models.customer.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("fake") // annotation is used to mark the DAO classes, so that spring can do DI
public class CustomerFakeDataAccessService implements CustomerDao {

    static List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer(1, "Manus", "Manus@mail.com", "password", 24, "male"));
        customers.add(new Customer(2, "Potas", "potas@mail.com", "password", 27, "chooter"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(long customerId) {

        return customers.stream()
                .filter((c) -> c.getId() == customerId)
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
//        TODO("Unimplemented")
    }

    @Override
    public boolean existCustomerByEmail(String mail) {
        return customers.stream().anyMatch(c -> Objects.equals(c.getMail(), mail));
    }

    @Override
    public boolean existCustomerById(long id) {
        return customers.stream().anyMatch(c -> id == c.getId());
    }

    @Override
    public void deleteCustomerById(long id) {
        customers.stream()
                .filter(c -> id == c.getId())
                .findFirst()
                .ifPresent(customers::remove);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.stream()
                .filter(c -> customer.getId() == c.getId())
                .findFirst().ifPresent(c -> {
                    c.setName(customer.getName());
                    c.setMail(customer.getMail());
                    c.setAge(customer.getAge());
                    c.setGender(customer.getGender());
                });
    }

    @Override
    public Optional<Customer> getCustomerByMail(String mail) {
        return customers.stream()
                .filter((c) -> Objects.equals(c.getMail(), mail))
                .findFirst();
    }
}
