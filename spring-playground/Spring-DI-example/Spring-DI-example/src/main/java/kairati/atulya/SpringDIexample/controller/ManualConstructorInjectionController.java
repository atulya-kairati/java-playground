package kairati.atulya.SpringDIexample.controller;

import kairati.atulya.SpringDIexample.service.GreetingService;

public class ManualConstructorInjectionController {

    private final GreetingService greetingService;

    public ManualConstructorInjectionController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String greet() {
        return greetingService.sayHello();
    }

    public String giveMeRent(){
        return "You will get your rent when fix this damn door!";
    }
}
