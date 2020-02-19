package com.awg.j20.travelport.flights.cntrl;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awg.j20.travelport.flights.cntrl.exp.FlightsAvailabilityExposure;
import com.awg.j20.travelport.flights.serv.ApiCall;
import com.awg.j20.travelport.flights.serv.DeserToExposureConverter;
import com.awg.j20.travelport.flights.serv.FlightSearchParams;
import com.awg.j20.travelport.flights.serv.deser.Availability;

@RestController
@RequestMapping("/flights")
public class FlightsController {
	private Logger logger = LoggerFactory.getLogger("FlightsController");
	
	@Autowired
	private ApiCall apiCall;
	
	@Autowired
	private DeserToExposureConverter converter;
	
	@GetMapping(value="/availability/{origin}/{dest}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FlightsAvailabilityExposure requestFlights(@PathVariable("origin") @NotBlank @Size(max = 3) String origin,
								 @PathVariable("dest") 	 @NotBlank @Size(max = 3) String destination, 
								 @RequestParam(name="start", required = false) 
								 @DateTimeFormat(iso=ISO.DATE)
								 String dateStart,
								 @RequestParam(name="end", required = false)
								 @DateTimeFormat(iso=ISO.DATE)
								 String dateEnd,
								 @RequestParam(name="pax", required=false, defaultValue = "1")
								 @Min(1) @Max(20)
								 Integer pax) {
		
		logger.debug("Flights requested: " + origin + ", " + destination + ","
						+ dateStart +"," + dateEnd + ", " + pax);	
		
		FlightSearchParams params = new FlightSearchParams(); 
		params.origin = origin;
		params.destination = destination;
		params.dateStart = dateStart;
		params.dateEnd = dateEnd;
		params.pax = pax;
		
		Availability xmlResponse = apiCall.call(params);
		FlightsAvailabilityExposure exposure = converter.convert(xmlResponse);

		return exposure;
	}

}
