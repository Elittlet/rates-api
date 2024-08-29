package com.spothero.rates_api;

import com.spothero.entities.Rate;
import com.spothero.entities.RatesList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
/**
 * Having a bit of trouble getting the tests fixed up and running with Junit 5
 */
public class RatesServiceTests {

    @InjectMocks
    private RatesService ratesService;

    @Mock
    private RatesRepository ratesRepository;

    @Test
    public void testGetRates() {
        int id = 1;
        Rate mockRate = new Rate();
        mockRate.setId(1L);
        mockRate.setDays("mon,tues");
        mockRate.setTimes("0900-1400");
        mockRate.setTz("America/Chicago");
        mockRate.setPrice(2000);

        List<Rate> mockRateList = new ArrayList<>();
        mockRateList.add(mockRate);

        Mockito.when(ratesRepository.findAll()).thenReturn(mockRateList);

        RatesList results = new RatesList();
        List<Rate> rates = ratesService.getRates();
        results.setRates(rates);
        assertNotNull(results.getRates());
    }
}
