package kairati.atulya.SpringDIexample.controller;

import kairati.atulya.SpringDIexample.service.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManualConstructorInjectionControllerTest {

    ManualConstructorInjectionController controller;

    @BeforeEach
    void setUp() {

        // DI
        controller = new ManualConstructorInjectionController(new GreetingServiceImpl());
    }

    @Test
    void testGreeting(){

        System.out.println(controller.getClass().getSimpleName());
        System.out.println(controller.greet());
    }
}