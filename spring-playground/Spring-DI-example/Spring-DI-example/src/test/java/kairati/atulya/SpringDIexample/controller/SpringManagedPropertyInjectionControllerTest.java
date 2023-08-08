package kairati.atulya.SpringDIexample.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SpringManagedPropertyInjectionControllerTest {
    @Autowired
    SpringManagedPropertyInjectionController controller;

    @Test
    void testGreeting() {
        System.out.println(controller.getClass().getSimpleName());
        System.out.println(controller.greet());
    }

}