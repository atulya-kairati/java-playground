package com.atulya.springbootpractice;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Random;

@Configuration
public class Config {
    // example on how to make beans
    @Bean(name = "random")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    // @RequestScope
    public Random getRandom(){
        return new Random(100);
    }
}
