package com.manus.weather.cached_weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachedWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachedWeatherApplication.class, args);
		System.out.println("Yo");
	}

}
