package com.atulya.springbootpractice.service;

import com.atulya.springbootpractice.dao.CustomerDao;
import com.atulya.springbootpractice.exceptions.DuplicateResourceException;
import com.atulya.springbootpractice.exceptions.ResourceNotFoundException;
import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerRegistrationRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service  // annotation is used to mark the service classes, so that spring can do DI
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao; // this will be injected by spring

    //    @Autowired // will work without constructor if used
    private final Random random;

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
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    @Override
    public void insertCustomer(CustomerRegistrationRequest crr) {

        if (customerDao.existCustomerByEmail(crr.mail())) {
            throw new DuplicateResourceException("Mail already exists");
        }

        Customer customer = new Customer(crr.name(), crr.mail(), crr.age());
        customerDao.insertCustomer(customer);
    }

    @Override
    public void deleteCustomerById(int id) {

        if (!customerDao.existCustomerById(id)) {
            throw new ResourceNotFoundException("Customer with id %d doesn't exist".formatted(id));
        }

        customerDao.deleteCustomerById(id);
    }

    @Override
    public void updateCustomerById(int id, CustomerRegistrationRequest crr) {

        // check that customer exists
        if (!customerDao.existCustomerById(id)) {
            throw new ResourceNotFoundException("Customer by id: %d doesn't exist".formatted(id));
        }

        Customer existingCustomer = getCustomerById(id);


        // check for that email is duplicate or not
        // if its duplicate then it must belong to the same user
        if (
                customerDao.existCustomerByEmail(crr.mail()) &&
                        !existingCustomer.getMail().equals(crr.mail())
        ) {
            throw new DuplicateResourceException("Mail %s already used".formatted(crr.mail()));
        }


/*
        Customer customer = new Customer(
                id,
                (crr.name() != null) ? crr.name() : existingCustomer.getName(),
                (crr.mail() != null) ? crr.mail() : existingCustomer.getMail(),
                (crr.age() != null) ? crr.age() : existingCustomer.getAge()
        );
*/

        Customer customer = new Customer(
                id,
                Optional.ofNullable(crr.name()).orElseGet(() -> existingCustomer.getName()),
                Optional.ofNullable(crr.mail()).orElseGet(() -> existingCustomer.getMail()),
                Optional.ofNullable(crr.age()).orElseGet(() -> existingCustomer.getAge())
        );

        if (customer.equals(existingCustomer)) {
            throw new DuplicateResourceException("No data changed");
        }
        customerDao.updateCustomer(customer);
    }
}
