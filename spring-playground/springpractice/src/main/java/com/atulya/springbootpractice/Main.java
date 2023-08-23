package com.atulya.springbootpractice;

import com.atulya.springbootpractice.models.customer.Customer;
import com.atulya.springbootpractice.repositories.CustomerRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

            int noOfCunts = 2;

            List<Customer> cunts = new ArrayList<>(noOfCunts);

            for (int i = 0; i < noOfCunts; i++) {
                Random random = new Random(System.currentTimeMillis());
                Faker faker = new Faker(random);
                Customer cunt = new Customer(
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        random.nextInt(100)
                );

                cunts.add(cunt);
            }

            repository.saveAll(cunts);
        };

    }
}
