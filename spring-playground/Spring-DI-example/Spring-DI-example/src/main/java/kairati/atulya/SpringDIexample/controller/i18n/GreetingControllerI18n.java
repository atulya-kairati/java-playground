package kairati.atulya.SpringDIexample.controller.i18n;


import kairati.atulya.SpringDIexample.service.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingControllerI18n {

    private final GreetingService greetingService;

    public GreetingControllerI18n(
            @Qualifier("i18nGreetingService") GreetingService greetingService
    ) {
        this.greetingService = greetingService;
    }

    public String greet() {
        return greetingService.sayHello();
    }
}
