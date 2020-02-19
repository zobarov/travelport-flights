package com.awg.j20.travelport.flights.serv.deser;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class Fares {
	
	@JsonProperty("Fare")
	public List<Fare> fare;

}
