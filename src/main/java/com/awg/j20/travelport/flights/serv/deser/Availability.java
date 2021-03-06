package com.awg.j20.travelport.flights.serv.deser;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Availability {
	@JacksonXmlElementWrapper(useWrapping = false)
	@JsonProperty("Flight")
	public List<Flight> flight;

	public List<Flight> getFlight() {
		return flight;
	}
	
}
