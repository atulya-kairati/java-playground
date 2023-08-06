package com.atulya.springbootpractice;

import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        String[] beans = applicationContext.getBeanDefinitionNames();

        for (String bean : beans) {
//            System.out.println(bean);
        }
    }


    // runs whenever the application is started
    @Bean
    CommandLineRunner runner(CustomerRepository repository) {
        return args -> {

            List<Customer> cunts = List.of(
                    new Customer(
                            1,
                            "Manus",
                            "manus@mail.co",
                            24
                    ),
                    new Customer(
                            8,
                            "Potas",
                            "potas@mail.co",
                            25
                    )
            );

//            repository.saveAll(cunts);
        };

    }
}
