package com.awg.j20.travelport.flights;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TravelportFlightsApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger("TravelportFlightsApplication");

	@Autowired
    private Environment env; 

	public static void main(String[] args) {
		new SpringApplicationBuilder(TravelportFlightsApplication.class)
					.web(WebApplicationType.SERVLET)
					.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> arguments = Arrays.asList(args);
		logger.info("Starting TravelportFlightsApplication with parameters: " + arguments);
		
		logger.info("Active Mode: " + env.getProperty("spring.application.name"));		
	}

}
