package kairati.atulya.springbeanlifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BeanLifeCycleTest {

    @Autowired
    BeanLifeCycle beanLifeCycle;

    @Test
    void runUselessTest(){
        System.out.println(beanLifeCycle.uselessMethod());
    }

}