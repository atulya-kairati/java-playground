package com.atulya.springbootpractice.dao;

import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.repositories.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class CustomerJPADataAccessService implements CustomerDao{

    private final CustomerRepository customerRepository;

    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public boolean existCustomerByEmail(String mail) {
        return customerRepository.existsCustomerByMail(mail);
    }

    @Override
    public boolean existCustomerById(int id) {
        return customerRepository.existsById(id);
    }

    @Override
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomer(Customer customer) {

        customerRepository.save(customer);
    }
}
