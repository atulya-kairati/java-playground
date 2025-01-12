package com.manus.weather.cached_weather.service;

import com.manus.weather.cached_weather.dto.Locations;
import com.manus.weather.cached_weather.models.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GeoCodingService {
    private static final Logger log = LogManager.getLogger(GeoCodingService.class);
    private final WebClient geoCodingClient;

    @Autowired
    public GeoCodingService(WebClient geoCodingClient) {
        this.geoCodingClient = geoCodingClient;
    }

    // this creates a cache with name "city" and saves kv pair cityName:CityData
    @Cacheable(value = "city", key = "#cityName", unless = "#result == null")
    public City getCityByName(String cityName) {
        Locations locations;
        try {
            locations = geoCodingClient.get()
                    .uri(uriBuilder -> uriBuilder.queryParam("name", cityName).build())
                    .retrieve()
                    .bodyToMono(Locations.class)
                    .doOnNext(response -> log.info("Geocoding data: {}", response))
                    .block(); // Block to convert reactive response into a synchronous one for simplicity
        } catch (RuntimeException e) {
            log.error("Error while geocoding: {}", e.getMessage());
            throw new RuntimeException("Error while geocoding: " + e.getMessage());
        }

        assert locations != null;
        if (locations.results() == null ||  locations.results().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
        }

        return locations.results().getFirst();
    }
}
