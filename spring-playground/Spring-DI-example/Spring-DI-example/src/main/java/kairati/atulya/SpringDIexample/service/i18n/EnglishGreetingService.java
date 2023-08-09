package kairati.atulya.SpringDIexample.service.i18n;

import kairati.atulya.SpringDIexample.service.GreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Profile({"EN", "default"})
@Service("i18nGreetingService")
public class EnglishGreetingService implements GreetingService {
    @Override
    public String sayHello() {
        return "Hello there! - EN";
    }
}
