package com.atulya.springbootpractice;


import com.atulya.springbootpractice.mappers.CustomerRowMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Random;

@Configuration
public class BeanConfig {
    // example on how to make beans
    @Bean(name = "random")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    // @RequestScope
    public Random getRandom() {
        return new Random(100);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CustomerRowMapper getCustomerRowMapper() {
        return new CustomerRowMapper();
    }
}
