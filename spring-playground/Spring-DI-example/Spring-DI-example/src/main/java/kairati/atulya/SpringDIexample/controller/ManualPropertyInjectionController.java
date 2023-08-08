package kairati.atulya.SpringDIexample.controller;

import kairati.atulya.SpringDIexample.service.GreetingService;

public class ManualPropertyInjectionController {

    GreetingService greetingService;

    public String greet() {
        return greetingService.sayHello();
    }

    public String giveMeRent(){
        return "You will get your rent when fix this damn door!";
    }
}
