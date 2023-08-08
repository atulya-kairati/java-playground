package kairati.atulya.SpringDIexample.controller;

import kairati.atulya.SpringDIexample.service.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManualPropertyInjectionControllerTest {

    ManualPropertyInjectionController controller;

    @BeforeEach
    void setUp() {

        controller = new ManualPropertyInjectionController();

        // DI // we will get an exception if we forget to do this
        controller.greetingService = new GreetingServiceImpl();
    }

    @Test
    void testGreeting(){
        System.out.println(controller.getClass().getSimpleName());
        System.out.println(controller.greet());
    }
}