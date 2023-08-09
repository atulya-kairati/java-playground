package kairati.atulya.SpringDIexample.service.i18n;

import kairati.atulya.SpringDIexample.service.GreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Profile("SND")
@Service("i18nGreetingService")
public class SindarinGreetingService implements GreetingService {
    @Override
    public String sayHello() {
        return "Suilad ennas! -SND";
    }
}
