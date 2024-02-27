package com.atulya.springbootpractice.service;

import com.atulya.springbootpractice.dao.CustomerDao;
import com.atulya.springbootpractice.exceptions.DuplicateResourceException;
import com.atulya.springbootpractice.exceptions.ResourceNotFoundException;
import com.atulya.springbootpractice.mappers.CustomerResponseDTOMapper;
import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.models.customer.CustomerRegistrationDTO;
import com.atulya.springbootpractice.models.customer.CustomerResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service  // annotation is used to mark the service classes, so that spring can do DI
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao; // this will be injected by spring
    private final PasswordEncoder passwordEncoder;

    //    @Autowired // will work without constructor if used
    private final Random random;

    private final CustomerResponseDTOMapper responseDTOMapper = new CustomerResponseDTOMapper();

    public CustomerServiceImpl(
            @Qualifier("jdbc") CustomerDao customerDao, PasswordEncoder passwordEncoder,
            Random random
    ) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
        this.random = random;
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerDao.getAllCustomers()
                .stream()
                .map(responseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO getCustomerById(long customerId) {

        return customerDao.getCustomerById(customerId)
                .map(responseDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    @Override
    public void insertCustomer(CustomerRegistrationDTO crDto) {

        if (customerDao.existCustomerByEmail(crDto.mail())) {
            throw new DuplicateResourceException("Mail already exists");
        }

        // TODO: Throw exception if any of the parameters are missing

        Customer customer = new Customer(
                crDto.name(),
                crDto.mail(),
                crDto.age(),
                crDto.gender().toLowerCase(),
                passwordEncoder.encode(crDto.password())
        );
        customerDao.insertCustomer(customer);
    }

    @Override
    public void deleteCustomerById(long id) {

        if (!customerDao.existCustomerById(id)) {
            throw new ResourceNotFoundException("Customer with id %d doesn't exist".formatted(id));
        }

        customerDao.deleteCustomerById(id);
    }

    @Override
    public void updateCustomerById(long id, CustomerRegistrationDTO crDto) {

        // check that customer exists
        if (!customerDao.existCustomerById(id)) {
            throw new ResourceNotFoundException("Customer by id: %d doesn't exist".formatted(id));
        }

        Customer existingCustomer = customerDao.getCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // check for that email is duplicate or not
        // if its duplicate then it must belong to the same user
        if (
                customerDao.existCustomerByEmail(crDto.mail()) &&
                        !existingCustomer.getMail().equals(crDto.mail())
        ) {
            throw new DuplicateResourceException("Mail %s already used".formatted(crDto.mail()));
        }


/*
//        Customer customer = new Customer(
//                id,
//                (crDto.name() != null) ? crDto.name() : existingCustomer.getName(),
//                (crDto.mail() != null) ? crDto.mail() : existingCustomer.getMail(),
//                (crDto.age() != null) ? crDto.age() : existingCustomer.getAge()
//        );
*/

        Customer customer = new Customer(
                id,
                Optional.ofNullable(crDto.name()).orElseGet(existingCustomer::getName),
                Optional.ofNullable(crDto.mail()).orElseGet(existingCustomer::getMail),
                Optional.ofNullable(crDto.mail()).orElseGet(existingCustomer::getPassword),
                Optional.ofNullable(crDto.age()).orElseGet(existingCustomer::getAge),
                Optional.ofNullable(crDto.gender() == null ? null : crDto.gender().toLowerCase())
                        .orElseGet(existingCustomer::getGender)
        );

        if (customer.equals(existingCustomer)) {
            throw new DuplicateResourceException("No data changed");
        }

        customerDao.updateCustomer(customer);
    }
}
