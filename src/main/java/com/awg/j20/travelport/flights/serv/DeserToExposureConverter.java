package com.awg.j20.travelport.flights.serv;

import com.awg.j20.travelport.flights.cntrl.exp.FlightsAvailabilityExposure;
import com.awg.j20.travelport.flights.cntrl.exp.FlightsAvailabilityExposure.FlightExposure;
import com.awg.j20.travelport.flights.serv.deser.Avaliability;
import com.awg.j20.travelport.flights.serv.deser.Flight;


public class DeserToExposureConverter {
	
	public static FlightsAvailabilityExposure convert(Avaliability xmlStructure) {
		FlightsAvailabilityExposure exposure = new FlightsAvailabilityExposure();
		
		for(Flight xmlFlight : xmlStructure.getFlight()) {
			FlightExposure expFlight = FlightExposure
										.withGeneral(xmlFlight.originAirport, xmlFlight.destinationAirport,
													xmlFlight.departureDate, xmlFlight.destinationAirport);

			exposure.withFlight(expFlight);
		}
		
		return exposure;
	}

}
