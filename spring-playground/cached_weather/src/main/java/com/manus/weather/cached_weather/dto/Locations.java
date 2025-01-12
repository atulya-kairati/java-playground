package com.manus.weather.cached_weather.dto;

import com.manus.weather.cached_weather.models.City;

import java.util.List;

public record Locations(
        List<City> results
) {
}
