package com.atulya.springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@IntegrationComponentScan
@SpringBootApplication
public class SpringIntegrationApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SpringIntegrationApplication.class, args);
		Thread.currentThread().join();
	}

}
