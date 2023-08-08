package kairati.atulya.SpringDIexample;

import kairati.atulya.SpringDIexample.controller.MyControllerWithoutDI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringDiExampleApplicationTests {

//	@Autowired // either use field or constructor injection
	ApplicationContext applicationContext;

	@Autowired // either use field or constructor injection
	MyControllerWithoutDI myController;

	public SpringDiExampleApplicationTests(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Test
	void testAutowireApplicationContext() {
		System.out.println(applicationContext.getBeanDefinitionCount());
	}

	@Test
	void testGetControllerFromApplicationContext() {

		MyControllerWithoutDI myController = applicationContext.getBean(MyControllerWithoutDI.class);

		System.out.println(myController.giveMeRent());
	}

	@Test
	void testAutowireMyController() {
		System.out.println(myController.giveMeRent());
	}

}
