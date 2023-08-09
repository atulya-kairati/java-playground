package kairati.atulya.SpringDIexample.controller.i18n;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


//@ActiveProfiles("SND")
@SpringBootTest
class GreetingControllerI18nTest {

    @Autowired
    GreetingControllerI18n controller;

    @Test
    void testGreeting(){
        System.out.println(controller.greet());;
    }

}