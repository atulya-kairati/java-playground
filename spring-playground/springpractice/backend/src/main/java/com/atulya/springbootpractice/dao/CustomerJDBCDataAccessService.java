package com.atulya.springbootpractice.dao;

import com.atulya.springbootpractice.mappers.CustomerRowMapper;
import com.atulya.springbootpractice.models.customer.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class CustomerJDBCDataAccessService implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    // the application context already contains a bean of JdbcTemplate which will be injected here
    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate, CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public List<Customer> getAllCustomers() {

        String selectSQL = """
                SELECT id, name, mail, age, gender, password
                FROM customer;
                """;

        List<Customer> customers = jdbcTemplate.query(selectSQL, customerRowMapper);

        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(long id) {
        String selectSQL = """
                SELECT id, name, mail, age, gender, password
                FROM customer
                WHERE id = ?
                """;

        return jdbcTemplate
                .query(selectSQL, customerRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        String insertSQL = """
                INSERT INTO customer (name, mail, age, gender, password)
                VALUES (?, ?, ?, ?, ?);
                """;

        int linesUpdated = jdbcTemplate.update(
                insertSQL,
                customer.getName(),
                customer.getMail(),
                customer.getAge(),
                customer.getGender(),
                customer.getPassword()
        );

        System.out.println("Lines updated during insert customer: " + linesUpdated);
    }

    @Override
    public boolean existCustomerByEmail(String mail) {

        String selectSQL = """
                SELECT * FROM customer
                WHERE mail = ?
                """;

        return !jdbcTemplate.query(selectSQL, customerRowMapper, mail).isEmpty();
    }

    @Override
    public boolean existCustomerById(long id) {
        String selectSQL = """
                SELECT * FROM customer
                WHERE id = ?
                """;

        return !jdbcTemplate.query(selectSQL, customerRowMapper, id).isEmpty();
    }

    @Override
    public void deleteCustomerById(long id) {
        String deleteSQL = """
                DELETE FROM customer
                WHERE id = ?
                """;

        jdbcTemplate.update(deleteSQL, id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        String updateSQL = """
                UPDATE customer
                SET
                name = ?,
                mail = ?,
                age = ?,
                gender = ?,
                password = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(
                updateSQL,
                customer.getName(),
                customer.getMail(),
                customer.getAge(),
                customer.getGender(),
                customer.getPassword(),
                customer.getId()
        );
    }

    @Override
    public Optional<Customer> getCustomerByMail(String mail) {
        String selectSQL = """
                SELECT id, name, mail, age, gender, password
                FROM customer
                WHERE mail = ?
                """;

        return jdbcTemplate
                .query(selectSQL, customerRowMapper, mail)
                .stream()
                .findFirst();
    }
}
