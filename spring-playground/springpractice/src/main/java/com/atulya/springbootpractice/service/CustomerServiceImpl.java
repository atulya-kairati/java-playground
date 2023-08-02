package com.atulya.springbootpractice.service;

import com.atulya.springbootpractice.dao.CustomerDao;
import com.atulya.springbootpractice.models.customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(int customerId) {

        return customerDao.getCustomerById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }
}
