package com.awg.j20.travelport.flights.cntrl.exp;

import java.util.ArrayList;
import java.util.List;

import com.awg.j20.travelport.flights.serv.deser.Money;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("flights")
public class FlightsAvailabilityExposure {
	
	@JsonProperty("availability")
	private List<FlightContainerExposure> flights = new ArrayList<>();
	
	public FlightsAvailabilityExposure withFlight(FlightExposure flight) {
		
		FlightContainerExposure flCont = new FlightContainerExposure();
		flCont.wrapFlight(flight);
		
		this.flights.add(flCont);
		
		return this;
		
	}
	
	public static class FlightContainerExposure {
		@JsonProperty("flight")
		private FlightExposure flight;
		
		public FlightContainerExposure() {
		}
		
		public void wrapFlight(FlightExposure fl) {
			this.flight = fl;
		}
		
	}
	
	public static class FlightExposure {
		@JsonProperty("operator")
		private String operator;
		@JsonProperty("flightNumber")
		private String flightNumber;
		@JsonProperty("departsFrom")
		private String departsFrom;
		@JsonProperty("arrivesAt")
		private String arrivesAt;
		@JsonProperty("departsOn")
		private OperationTimeExposure departsOn;
		@JsonProperty("arrivesOn")
		private OperationTimeExposure arrivesOn;
		@JsonProperty("flightTime")
		private String flightTime;
		@JsonProperty("farePrices")
		private FarePrices farePrices = new FarePrices();
		
		private FlightExposure() {
			//to avoid empty entries
		}
		
		public static FlightExposure withGeneral(String operator, String flightNum, String depFrom, String arrAt) {
			FlightExposure flight = new FlightExposure();
			flight.operator = operator;
			flight.flightNumber = flightNum;
			flight.departsFrom = depFrom;
			flight.arrivesAt = arrAt;

			return flight;
		}
		
		public FlightExposure withDeparture(String date, String time) {
			OperationTimeExposure opExp = new OperationTimeExposure();
			opExp.date = date;
			opExp.time = time;
			
			this.departsOn = opExp;
			return this;
		}
		
		public FlightExposure withArrival(String date, String time) {
			OperationTimeExposure opExp = new OperationTimeExposure();
			opExp.date = date;
			opExp.time = time;
			
			this.arrivesOn = opExp;
			return this;
		}
		
		//TODO: calculate it
		public FlightExposure withFlightTime(String fl) {			
			this.flightTime = fl;
			return this;
		}
		
		//TODO: strict to money:
		public FlightExposure withFarePriceFirst(Money ticket, Money bookengFee, Money tax) {	
			FareExposure fareFirst = new FareExposure();
			fareFirst.ticket = new FareEntryExposure(ticket.sum(), ticket.currency());
			fareFirst.bookingFee = new FareEntryExposure(bookengFee.sum(), bookengFee.currency());
			fareFirst.tax = new FareEntryExposure(tax.sum(), tax.currency());
			this.farePrices.first = fareFirst;
			return this;
		}
		
		public FlightExposure withFarePriceBusiness(Money ticket, Money bookengFee, Money tax) {
			
			FareExposure fareBusiness = new FareExposure();
			fareBusiness.ticket = new FareEntryExposure(ticket.sum(), ticket.currency());
			fareBusiness.bookingFee = new FareEntryExposure(bookengFee.sum(), bookengFee.currency());
			fareBusiness.tax = new FareEntryExposure(tax.sum(), tax.currency());
			this.farePrices.business = fareBusiness;
			return this;
		}
		
		public FlightExposure withFarePriceEconomy(Money ticket, Money bookengFee, Money tax) {
			
			FareExposure fareEconomy = new FareExposure();
			fareEconomy.ticket = new FareEntryExposure(ticket.sum(), ticket.currency());
			fareEconomy.bookingFee = new FareEntryExposure(bookengFee.sum(), bookengFee.currency());
			fareEconomy.tax = new FareEntryExposure(tax.sum(), tax.currency());
			this.farePrices.economy = fareEconomy;
			return this;
		}
	}
	
	public static class OperationTimeExposure {
		@JsonProperty("date")
		private String date;
		@JsonProperty("time")
		private String time;
	}
	
	private static class FarePrices {
		@JsonProperty("first")
		private FareExposure first;
		@JsonProperty("business")
		private FareExposure business;
		@JsonProperty("economy")
		private FareExposure economy;
	}
	
	public static class FareExposure {
		@JsonProperty("ticket")
		private FareEntryExposure ticket;
		@JsonProperty("bookingFee")
		private FareEntryExposure bookingFee;
		@JsonProperty("tax")
		private FareEntryExposure tax;
	}
	
	public static class FareEntryExposure {
		@JsonProperty("currency")
		private String currency;
		@JsonProperty("amount")
		private String amount;
		
		private FareEntryExposure(String amount, String curr) {
			this.amount = amount;
			this.currency = curr;
		}
	}
}
