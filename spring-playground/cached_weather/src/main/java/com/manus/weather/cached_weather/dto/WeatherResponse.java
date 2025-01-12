package com.manus.weather.cached_weather.dto;

import java.io.Serializable;

public record WeatherResponse(
        String city,
        String temperature,
        String description
) implements Serializable {
    public WeatherResponse {
        // make sure city always exists
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City can not be blank");
        }
    }
}