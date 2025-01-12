package com.manus.weather.cached_weather.controller;

import com.manus.weather.cached_weather.dto.WeatherResponse;
import com.manus.weather.cached_weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponse> weatherByCity(@PathVariable String city) {

        WeatherResponse response = weatherService.getWeatherByCityName(city);

        return ResponseEntity.ok(response);
    }
}
