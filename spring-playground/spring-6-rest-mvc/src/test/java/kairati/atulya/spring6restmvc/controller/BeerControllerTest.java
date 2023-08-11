package kairati.atulya.spring6restmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.UUID;


@SpringBootTest
class BeerControllerTest {
    @Autowired
    BeerController controller;

    @Test
    void testController() {
        System.out.println(controller.getBeerById(24));
    }
}