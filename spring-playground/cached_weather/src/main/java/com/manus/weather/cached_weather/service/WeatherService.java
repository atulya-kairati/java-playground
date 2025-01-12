package com.manus.weather.cached_weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.manus.weather.cached_weather.dto.WeatherResponse;
import com.manus.weather.cached_weather.models.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {
    private static final Logger log = LogManager.getLogger(WeatherService.class);
    private final WebClient weatherClient;
    private final GeoCodingService geoCodingService;

    @Autowired
    public WeatherService(WebClient weatherClient, GeoCodingService geoCodingService) {
        this.weatherClient = weatherClient;
        this.geoCodingService = geoCodingService;
    }

    // this creates a cache with name "weather" and saves kv pair cityName:weatherData
    @Cacheable(value = "weather", key = "#cityName", unless = "#result == null")
    public WeatherResponse getWeatherByCityName(String cityName) {
        City city = geoCodingService.getCityByName(cityName);

        try {
            JsonNode response = weatherClient.get()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .queryParam("latitude", city.latitude())
                                    .queryParam("longitude", city.longitude())
                                    .queryParam("current", "temperature_2m")
                                    .queryParam("timezone", city.timezone())
                                    .queryParam("forecast_days", "1")
                                    .build()
                    ).retrieve()
                    .bodyToMono(JsonNode.class)
                    .doOnNext(res -> log.info("Weather data: {}", res))
                    .block();

            assert response != null;
            String temperature = response.path("current").path("temperature_2m").asText()
                    + " " +
                    response.path("current_units").path("temperature_2m").asText();

            return new WeatherResponse(city.name(), temperature, "Bing Chilling!");
        } catch (RuntimeException e) {
            log.error("Error while fetching weather: {}", e.getMessage());
            throw new RuntimeException("Error while fetching weather: " + e);
        }

    }
}
