package com.atulya;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        System.out.println("now = " + now);
        System.out.println(now.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
        System.out.println(now.getDayOfWeek());

        // creating a specific date
        LocalDateTime birthOfTragedy = LocalDateTime.of(2022, 4, 1, 0, 0 ,0);


        // Getting time for different location. Working with zoneIds
        ZoneId.getAvailableZoneIds().forEach(System.out::println);
        ZoneId brazilZoneId = ZoneId.of("Brazil/Acre");
        LocalDateTime brazilTime = LocalDateTime.now(brazilZoneId);
        System.out.println(brazilTime);

        System.out.println(ZonedDateTime.now());

        System.out.println(Instant.now());
    }
}
