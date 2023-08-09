package kairati.atulya.springbeanlifecycle;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanLifeCycle implements InitializingBean, DisposableBean, BeanNameAware,
        BeanFactoryAware, ApplicationContextAware, BeanPostProcessor {

    private final SomeComponent someComponent;

    public BeanLifeCycle(SomeComponent someComponent) {
        // Called 1st
        this.someComponent = someComponent;
        System.out.println("$$------> Inside BeanLifeCycle constructor");
    }

    @Value("${java.specification.version}")  // look for SpEL
    public void getJavaVersion(String javaVersion) {
        // Called 2nd
        System.out.println("$$------> in getJavaVersion(): Java Version = " + javaVersion);
    }

    @Override
    public void setBeanName(String name) {
        // Comes from BeanNameAware
        // Called 3rd
        System.out.println("$$------> setBeanName(): Bean Name = " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // Comes from BeanFactoryAware
        // Called 4th
        System.out.println("$$------> setBeanFactory(): beanFactory = " + beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // Comes from ApplicationContextAware
        // Called 5th

        SomeComponent someComponentBean = applicationContext.getBean(SomeComponent.class);
//        System.out.println(someComponentBean);
        System.out.println("$$------> setApplicationContext(): applicationContext = " + applicationContext);
    }

    @PostConstruct
    public void postConstruct() {
        // Called 6th
        // Comes from BeanPostProcessor
        // called after all the processing for object construction is done
        // but called before the object is handed to any other entity

        System.out.println("$$------> @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Comes from InitializingBean
        // Called 7th
        System.out.println("$$------> afterPropertiesSet()");
    }

    @PreDestroy
    public void preDestroy() {
        // Comes from BeanPostProcessor

        // Called before destroy method

        System.out.println("$$------> @PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        // Comes from DisposableBean

        System.out.println("$$------> destroy()");
    }

    public String uselessMethod() {
        return someComponent.someMethod();
    }


    // SPECIAL METHODS
    // These will be called for every object in the context

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // Comes from BeanPostProcessor
        // Called for every [bean] in the [ApplicationContext]
        // AFTER it is initialized.

//        System.out.println("$$------> postProcessBeforeInitialization(): beanName = " + beanName);

        // FIXME: Its not picking up SomeComponent bean even though it is present in context
        if (bean instanceof SomeComponent component) {
            System.out.println("$$------> postProcessBeforeInitialization(): beanName = " + beanName);
            component.beforeInit();
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // Comes from BeanPostProcessor
        // Called for every [bean] in the [ApplicationContext]
        // BEFORE it is initialized.

//        System.out.println("$$------> postProcessAfterInitialization(): beanName = " + beanName);

        // FIXME: Its not picking up SomeComponent bean even though it is present in context
        if (bean instanceof SomeComponent component) {
            System.out.println("$$------> postProcessAfterInitialization(): beanName = " + beanName);
            component.afterInit();
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
