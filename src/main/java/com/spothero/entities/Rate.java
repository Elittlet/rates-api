package com.spothero.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.*;

@Entity
@Table(name = "rates")
@Data
public class Rate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "days")
    private String days;

    @Column(nullable = false, name = "times")
    private String times;

    @Column(nullable = false, name = "timeZone")
    private String tz;

    @Column(nullable = false, name = "price")
    private int price;

    public String getDays() {
        return days;
    }

    public Integer getPrice() {
        return price;
    }

    /**
     * Method to parse the saved start time to a valid LocalTime
     * The DateTime parser was having trouble parsing the time
     * without a colon so the method builds a new string with a colon inserted
     * @return
     */
    public LocalTime parseRateStartTime() {
        String unformattedStartTime = times.split("-")[0];
        StringBuilder timeWithColonBuilder = new StringBuilder(unformattedStartTime);
        String startTimeWithColon = timeWithColonBuilder.insert(2,":").toString();
        LocalTime formattedStartTime = LocalTime.parse(startTimeWithColon);
        ZoneOffset zoneOffset = ZoneId.of(tz).getRules().getOffset(Instant.now());
        return formattedStartTime.atOffset(zoneOffset).toLocalTime();
    }

    public LocalTime parseRateEndTime() {
        String unformattedEndTime = times.split("-")[1];
        StringBuilder timeWithColonBuilder = new StringBuilder(unformattedEndTime);
        String startTimeWithColon = timeWithColonBuilder.insert(2,":").toString();
        LocalTime formattedEndTime = LocalTime.parse(startTimeWithColon);
        ZoneOffset zoneOffset = ZoneId.of(tz).getRules().getOffset(Instant.now());
        return formattedEndTime.atOffset(zoneOffset).toLocalTime();
    }

}

