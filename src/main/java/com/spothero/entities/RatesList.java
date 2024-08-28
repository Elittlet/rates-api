package com.spothero.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Rates are submitted as a JSON array, to serial/deserialize the rates array in the JSON
 * properly I've created a wrapper class for the array which has a list of the actual
 * rates entities.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesList {
    private List<Rate> ratesList;

    public void setRates(List<Rate> rates) {
        ratesList =  rates;
    }

    public List<Rate> getRates() {
        return ratesList;
    }

}
