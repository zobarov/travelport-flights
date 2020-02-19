package com.awg.j20.travelport.flights.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Basic configuration for remote API server.
 */
@Configuration
public class FlightApiConfig {
	
	@Value("${flight-api.url}")
	private String flightApiUrl;
	
	public String flightApiUrl() {
		return flightApiUrl;
	}
	
	public String availabilityEndpoint() {
		return flightApiUrl + "/{origin}/{destination}/{start}/{end}/{pax}";
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}
}
