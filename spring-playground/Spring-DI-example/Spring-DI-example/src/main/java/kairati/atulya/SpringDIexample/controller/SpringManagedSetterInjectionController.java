package kairati.atulya.SpringDIexample.controller;

import kairati.atulya.SpringDIexample.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SpringManagedSetterInjectionController {

    private GreetingService greetingService;

    @Autowired  // have to specify this strictly on setter if doing setter DI
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String greet() {
        return greetingService.sayHello();
    }

    public String giveMeRent(){
        return "You will get your rent when fix this damn door!";
    }
}
