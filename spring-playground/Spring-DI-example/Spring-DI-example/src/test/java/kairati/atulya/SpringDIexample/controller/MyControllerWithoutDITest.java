package kairati.atulya.SpringDIexample.controller;

import org.junit.jupiter.api.Test;

class MyControllerWithoutDITest {

    @Test
    void testGreeting() {
        MyControllerWithoutDI controller = new MyControllerWithoutDI();
        System.out.println(controller.getClass().getSimpleName());
        System.out.println(controller.greet());
    }

}