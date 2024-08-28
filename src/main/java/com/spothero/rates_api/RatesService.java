package com.spothero.rates_api;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.spothero.entities.Rate;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.TextStyle;
import java.util.*;


@Service
public class RatesService {

    private final RatesRepository ratesRepository;

    public RatesService(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    public List<Rate> saveRates(List<Rate> rates) {
        ratesRepository.deleteAll();
        return ratesRepository.saveAll(rates);
    }

    public List<Rate> getRates() {
        return ratesRepository.findAll();
    }

    /**
     * Finds a rate price for a given date/time range
     * @param startDate ISO-8601 formatted start date
     * @param endDate ISO-8601 formatted end date
     * @return
     */
    public Rate getPrice(String startDate, String endDate) {
        LocalTime startTime = OffsetDateTime.parse(startDate).toLocalTime();
        LocalTime endTime = OffsetDateTime.parse(endDate).toLocalTime();
        String startDay = OffsetDateTime.parse(startDate).toLocalDateTime().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US).toLowerCase();
        String endDay = OffsetDateTime.parse(endDate).toLocalDateTime().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US).toLowerCase();

        // if user input spans multiple days the API must return "Unavailable"
        if(!startDay.equals(endDay)) {
            throw new IllegalArgumentException();
        }
        // Impractical to fetch all records for large tables but for the purpose
        // of this exercise fetching all records and filtering works sufficiently
        List<Rate> rates = ratesRepository.findAll();
        if(rates.isEmpty()) {
            throw new EntityNotFoundException();
        }
        List<Rate> filteredRatesByDays = rates.stream()
                .filter(rate -> rate.getDays().contains(startDay)).toList();
        List<Rate> filteredRatesByTime = filteredRatesByDays.stream().filter(rate -> startTime.isAfter(rate.parseRateStartTime()) && endTime.isBefore(rate.parseRateEndTime()))
                .toList();
// User input may span multiple rates but in that case the API must return "Unavailable"
        if(filteredRatesByTime.size() != 1)  {
            throw new IllegalArgumentException();
        }
        return filteredRatesByTime.get(0);
    }

}