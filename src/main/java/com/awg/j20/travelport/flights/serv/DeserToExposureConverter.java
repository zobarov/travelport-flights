package com.awg.j20.travelport.flights.serv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.awg.j20.travelport.flights.cntrl.exp.FlightsAvailabilityExposure;
import com.awg.j20.travelport.flights.cntrl.exp.FlightsAvailabilityExposure.FlightExposure;
import com.awg.j20.travelport.flights.serv.deser.Availability;
import com.awg.j20.travelport.flights.serv.deser.Fare;
import com.awg.j20.travelport.flights.serv.deser.Flight;
import com.awg.j20.travelport.flights.serv.deser.Money;


@Service
public class DeserToExposureConverter {
	private Logger logger = LoggerFactory.getLogger(DeserToExposureConverter.class);
	
	public FlightsAvailabilityExposure convert(Availability xmlStructure) {
		FlightsAvailabilityExposure exposure = new FlightsAvailabilityExposure();
		
		for(Flight xmlFlight : xmlStructure.getFlight()) {
			FlightExposure expFlight = FlightExposure
										.withGeneral(xmlFlight.carrierCode, xmlFlight.flightDesignator,
													 xmlFlight.originAirport, xmlFlight.destinationAirport)
										.withArrival(xmlFlight.arrivalDate, xmlFlight.arrivalDate)
										.withDeparture(xmlFlight.departureDate, xmlFlight.departureDate)
										.withFlightTime("CALC");
			if(xmlFlight.getFares() != null) {
				
				for(Fare fare : xmlFlight.getFares()) {
					Money moneyTicket = splitPrice(fare.basePrice);
					Money moneyBookingFee = splitPrice(fare.fees);
					Money moneyTax = splitPrice(fare.tax);
					if(moneyTicket == null) {
						logger.warn("Cannot convert to money:" + fare.basePrice);
						continue;
					}
					
					//TODO: encapsulate to enum:
					if("FIF".equalsIgnoreCase(fare.clazz)) {
						expFlight.withFarePriceFirst(moneyTicket, moneyBookingFee, moneyTax);
					} else if("YIF".equalsIgnoreCase(fare.clazz)) {
						expFlight.withFarePriceEconomy(moneyTicket, moneyBookingFee, moneyTax);
					} else if("CIF".equalsIgnoreCase(fare.clazz)) {
						expFlight.withFarePriceBusiness(moneyTicket, moneyBookingFee, moneyTax);
					}
				}
			}						

			exposure.withFlight(expFlight);
		}
		
		return exposure;
	}
	
	private static Money splitPrice(String priceStr) {
		//EUR 136.00
		String[] splits = priceStr.split(" ");
		if(splits.length != 2) {
			return null;
		}
		return new Money(splits[1], splits[0]);
	}
}
