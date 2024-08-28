package com.spothero.rates_api;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spothero.entities.Rate;
import com.spothero.entities.RatesList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rates_api/v1")
public class RatesController {

    private final RatesService ratesService;

    public RatesController(RatesService ratesService) {
        this.ratesService = ratesService;
    }

    @PutMapping("/rates")
    public ResponseEntity<List<Rate>> saveRates(@RequestBody RatesList rates) {
        List<Rate> savedRates = ratesService.saveRates(rates.getRates());
        RatesList ratesList = new RatesList();
        ratesList.setRates(savedRates);
        return ResponseEntity.ok(ratesList.getRates());
    }

    @GetMapping("/rates")
    @ResponseBody
    public ResponseEntity<List<Rate>> getAllRates() {
        List<Rate> rates = ratesService.getRates();
        RatesList ratesList = new RatesList();
        ratesList.setRates(rates);
        return ResponseEntity.ok(ratesList.getRates());
    }

    /**
     * Endpoint for fetching a price for a time range
     * @param start ISO-8601 formatted DateTime
     * @param end ISO-8601 formatted DateTime
     * @return Rate Price
     */
    @GetMapping("/price")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> getPrice(@RequestParam String start, @RequestParam String end) {
        Rate rate = ratesService.getPrice(start, end);
        Map<String, Integer> priceResponse = new HashMap<>();
        priceResponse.put("price", rate.getPrice());
        return ResponseEntity.ok(priceResponse);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgument() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unavailable");
    }
}
