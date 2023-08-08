package kairati.atulya.SpringDIexample;

import kairati.atulya.SpringDIexample.controller.MyControllerWithoutDI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDiExampleApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringDiExampleApplication.class, args);

		MyControllerWithoutDI controller = applicationContext.getBean(MyControllerWithoutDI.class);

		System.out.println(controller.giveMeRent());

	}

}
