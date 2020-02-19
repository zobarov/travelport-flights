package com.awg.j20.travelport.flights.serv.deser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Fare {
	@JacksonXmlProperty(localName = "class", isAttribute = true)
	public String clazz;
	
	@JsonProperty("BasePrice")
	public String basePrice;
	@JsonProperty("Fees")
	public String fees;
	@JsonProperty("Tax")
	public String tax;

}
