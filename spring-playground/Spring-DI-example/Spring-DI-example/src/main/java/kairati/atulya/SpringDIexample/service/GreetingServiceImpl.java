package kairati.atulya.SpringDIexample.service;


import org.springframework.stereotype.Service;

@Service("bestBean")
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHello() {
        return "MC BC!!!";
    }
}
