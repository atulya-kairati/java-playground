package kairati.atulya.SpringDIexample.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringManagedConstructorInjectionControllerTest {

    @Autowired
    SpringManagedConstructorInjectionController controller;

    @Test
    void testGreeting() {
        System.out.println(controller.getClass().getSimpleName());
        System.out.println(controller.greet());
    }

}