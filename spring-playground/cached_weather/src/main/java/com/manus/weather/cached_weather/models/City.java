package com.manus.weather.cached_weather.models;

import java.io.Serializable;

public record City(
        String name,
        Long latitude,
        Long longitude,
        String timezone
) implements Serializable {
}
