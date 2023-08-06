package com.atulya.springbootpractice.service;

import com.atulya.springbootpractice.dao.CustomerDao;
import com.atulya.springbootpractice.exceptions.DuplicateMail;
import com.atulya.springbootpractice.exceptions.ResourceNotFound;
import com.atulya.springbootpractice.models.customer.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service  // annotation is used to mark the service classes, so that spring can do DI
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao; // this will be injected by spring

    //    @Autowired // will work without constructor if used
    private Random random;

    public CustomerServiceImpl(
            @Qualifier("jpa") CustomerDao customerDao,
            Random random
    ) {
        this.customerDao = customerDao;
        this.random = random;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(int customerId) {

        return customerDao.getCustomerById(customerId)
                .orElseThrow(() -> new ResourceNotFound("Customer not found"));
    }

    @Override
    public void insertCustomer(Customer customer) {
        try {
            customerDao.insertCustomer(customer);
        }
        catch (DataIntegrityViolationException dive) {
            dive.printStackTrace();
            throw new DuplicateMail("Mail already exists");
        }
    }
}
