package kairati.atulya.SpringDIexample.controller;

import kairati.atulya.SpringDIexample.service.GreetingService;
import org.springframework.stereotype.Controller;

@Controller
public class SpringManagedConstructorInjectionController {

    private final GreetingService greetingService;

    public SpringManagedConstructorInjectionController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String greet() {
        return greetingService.sayHello();
    }

    public String giveMeRent(){
        return "You will get your rent when fix this damn door!";
    }
}
