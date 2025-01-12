package com.manus.weather.cached_weather.controller;

import com.manus.weather.cached_weather.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final RedisService redisService;

    public TestController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/redis")
    public String testRedis() {
        return redisService.testRedis();
    }
}
