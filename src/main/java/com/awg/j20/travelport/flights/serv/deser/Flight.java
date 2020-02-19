package com.awg.j20.travelport.flights.serv.deser;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Flight {
	
	@JsonProperty("CarrierCode")
	public String carrierCode;
	@JsonProperty("FlightDesignator")
	public String flightDesignator;
	@JsonProperty("OriginAirport")
	public String originAirport;
	@JsonProperty("DestinationAirport")
	public String destinationAirport;
	@JsonProperty("DepartureDate")
	public String departureDate;
	@JsonProperty("ArrivalDate")
	public String arrivalDate;

	@JacksonXmlElementWrapper(useWrapping = true)
	@JsonProperty("Fares")
	public List<Fare> fares;
	
	public List<Fare> getFares() {
		return fares;
	}
}
