package com.awg.j20.travelport.flights.serv;

import com.awg.j20.travelport.flights.cntrl.exp.FlightsAvailabilityExposure;
import com.awg.j20.travelport.flights.cntrl.exp.FlightsAvailabilityExposure.FlightExposure;
import com.awg.j20.travelport.flights.serv.deser.Money;

public class DeserConvertorTest {
	
	public void exp() {
		FlightsAvailabilityExposure expos = new FlightsAvailabilityExposure();
		
		FlightExposure fligth1 = FlightExposure.withGeneral("RU", "L12345", "LED", "AMS")
											   .withDeparture("01-01-2014", "08:52AM")
											   .withArrival("02-01-2014", "10:00AM")
											   .withFlightTime("03:57")
											   .withFarePriceFirst(new Money("100", "USD"), new Money("100", "USD"), new Money("100", "USD"))
											   .withFarePriceBusiness(new Money("200", "EUR"), new Money("200", "EUR"), new Money("200", "EUR"))
											   .withFarePriceEconomy(new Money("50", "CKZ"), new Money("50", "CKZ"), new Money("50", "CKZ"));
		
		FlightExposure fligth2 = FlightExposure.withGeneral("UA", "UA304", "MUC", "IST")
				   								.withDeparture("10-12-2014", "11:00AM")
				   								.withArrival("10-12-2014", "12:00AM")
				   								.withFlightTime("10:20")
				   								.withFarePriceFirst(new Money("1050", "EUR"), new Money("1050", "EUR"), new Money("1050", "EUR"))
												.withFarePriceBusiness(new Money("250", "RUB"), new Money("250", "RUB"), new Money("250", "RUB"))
												.withFarePriceEconomy(new Money("150", "TNG"), new Money("150", "TNG"), new Money("150", "TNG"));
		
		expos.withFlight(fligth1)
			 .withFlight(fligth2);
	}

}
