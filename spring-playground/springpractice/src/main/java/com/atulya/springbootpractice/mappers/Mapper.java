package com.atulya.springbootpractice.mappers;

import com.atulya.springbootpractice.models.customer.Customer;
import org.springframework.jdbc.core.RowMapper;

public class Mapper {
    public static RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
        return new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("mail"),
                rs.getInt("age")
        );
    };
}
