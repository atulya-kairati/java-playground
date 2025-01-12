package com.manus.weather.cached_weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String testRedis() {
        redisTemplate.opsForValue().set("sec", "ret");
        return (String) redisTemplate.opsForValue().get("sec");
    }
}
