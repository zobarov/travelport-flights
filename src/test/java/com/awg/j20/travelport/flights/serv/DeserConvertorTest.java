package com.awg.j20.travelport.flights.serv;

import com.awg.j20.travelport.flights.cntrl.exp.FlightsAvailabilityExposure;
import com.awg.j20.travelport.flights.cntrl.exp.FlightsAvailabilityExposure.FlightExposure;

public class DeserConvertorTest {
	
	public void exp() {
		FlightsAvailabilityExposure expos = new FlightsAvailabilityExposure();
		
		FlightExposure fligth1 = FlightExposure.withGeneral("RU", "L12345", "LED", "AMS")
											   .withDeparture("01-01-2014", "08:52AM")
											   .withArrival("02-01-2014", "10:00AM")
											   .withFlightTime("03:57")
											   .withFarePriceFirst("100", "USD")
											   .withFarePriceBusiness("200", "EUR")
											   .withFarePriceEconomy("50", "CKZ");
		
		FlightExposure fligth2 = FlightExposure.withGeneral("UA", "UA304", "MUC", "IST")
				   								.withDeparture("10-12-2014", "11:00AM")
				   								.withArrival("10-12-2014", "12:00AM")
				   								.withFlightTime("10:20")
				   								.withFarePriceFirst("1050", "EUR")
												.withFarePriceBusiness("250", "RUB")
												.withFarePriceEconomy("150", "TNG");
		
		expos.withFlight(fligth1)
			 .withFlight(fligth2);
	}

}
