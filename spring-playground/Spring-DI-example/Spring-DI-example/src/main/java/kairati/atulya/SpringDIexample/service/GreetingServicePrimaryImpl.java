package kairati.atulya.SpringDIexample.service;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class GreetingServicePrimaryImpl implements GreetingService {
    @Override
    public String sayHello() {
        return "Primary bean says hello.";
    }
}
