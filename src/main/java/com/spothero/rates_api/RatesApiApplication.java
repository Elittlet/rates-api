package com.spothero.rates_api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spothero.entities.Rate;
import com.spothero.entities.RatesList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.spothero.*")
@EntityScan("com.spothero.*")
@EnableJpaRepositories
public class RatesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatesApiApplication.class, args);
	}

	/**
	 * Load the in memory database with the rates parsed from initialRates.json
	 * @param ratesService Rates Service
	 * @return
	 */
	@Bean
	CommandLineRunner runner(RatesService ratesService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			TypeReference<RatesList> typeReference = new TypeReference<RatesList>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/initialRates.json");
			try {
				RatesList rates = mapper.readValue(inputStream, typeReference);
				ratesService.saveRates(rates.getRates());
			}
			catch(IOException e) {
				throw(e);
			}
		};
	}

}
