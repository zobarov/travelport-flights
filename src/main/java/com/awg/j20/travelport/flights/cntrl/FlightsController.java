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
import com.awg.j20.travelport.flights.cntrl.exp.FlightsAvailabilityExposure.FlightExposure;
import com.awg.j20.travelport.flights.serv.ApiCall;
import com.awg.j20.travelport.flights.serv.FlightSearchParams;

@RestController
@RequestMapping("/flights")
public class FlightsController {
	private Logger logger = LoggerFactory.getLogger("FlightsController");
	
	@Autowired
	private ApiCall apiCall;
	
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
		
		apiCall.call(params);
		
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
		
		return expos;
	}

}
