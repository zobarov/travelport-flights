package com.awg.j20.travelport.flights.testutil;

import java.util.ArrayList;

import com.awg.j20.travelport.flights.serv.deser.Availability;
import com.awg.j20.travelport.flights.serv.deser.Fare;
import com.awg.j20.travelport.flights.serv.deser.Flight;

public class TestUtilAvailabilityGenerator {
	public static Availability generateMockXmlMapping() {
		Availability mockXmlMapping = new Availability();
		Flight flight1 = new Flight();
		flight1.carrierCode = "RU";
		flight1.flightDesignator = "L12345";
		flight1.originAirport = "LED";
		flight1.destinationAirport = "AMS";
		flight1.arrivalDate = "02-01-2014";
		flight1.departureDate = "01-01-2014";
		
		flight1.fares = new ArrayList<>();
		Fare fareBiz = new Fare();
		fareBiz.clazz = "CIF";
		fareBiz.basePrice = "EUR 200";
		fareBiz.tax = "USD 70";
		fareBiz.fees = "USD 20";
		
		flight1.fares.add(fareBiz);
		
		Fare fareFirst = new Fare();
		fareFirst.clazz = "FIF";
		fareFirst.basePrice = "USD 100";
		fareFirst.tax = "USD 70";
		fareFirst.fees = "USD 20";
		
		flight1.fares.add(fareFirst);
		
		Fare fareEconomy = new Fare();
		fareEconomy.clazz = "YIF";
		fareEconomy.basePrice = "CKZ 50";
		fareEconomy.tax = "CKZ 70";
		fareEconomy.fees = "CKZ 20";
		
		flight1.fares.add(fareEconomy);
		
		
		mockXmlMapping.flight = new ArrayList<>();
		mockXmlMapping.flight.add(flight1);
		
		return mockXmlMapping;
	}

}
