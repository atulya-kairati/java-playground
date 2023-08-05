package com.atulya.springbootpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        String[] beans = applicationContext.getBeanDefinitionNames();

        for (String bean : beans) {
//            System.out.println(bean);
        }
    }
}
