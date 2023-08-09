package kairati.atulya.springbeanlifecycle;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class SomeComponent {

    public String someMethod() {
        return "Lorem Ipsum";
    }

    public void beforeInit() {
        System.out.println("$$-------> SomeComponent beforeInit()");
    }

    public void afterInit() {
        System.out.println("$$-------> SomeComponent afterInit()");
    }
}
