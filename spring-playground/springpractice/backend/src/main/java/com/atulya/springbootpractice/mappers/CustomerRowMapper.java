package com.atulya.springbootpractice.mappers;


import com.atulya.springbootpractice.models.customer.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("mail"),
                rs.getString("password"),
                rs.getInt("age"),
                rs.getString("gender").toLowerCase()
        );
    }
}
