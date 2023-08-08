package kairati.atulya.SpringDIexample.controller;


import kairati.atulya.SpringDIexample.service.GreetingService;
import kairati.atulya.SpringDIexample.service.GreetingServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class MyControllerWithoutDI {

    private GreetingService greetingService;

    public MyControllerWithoutDI() {

        // we are tightly coupling this implementation of GreetingService with our class
        // Here the class has control of how the dependency is being created
        greetingService = new GreetingServiceImpl();
    }

    public String greet() {
        return greetingService.sayHello();
    }

    public String giveMeRent(){
        return "You will get your rent when fix this damn door!";
    }

}
