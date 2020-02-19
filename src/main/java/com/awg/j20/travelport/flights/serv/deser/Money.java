package com.awg.j20.travelport.flights.serv.deser;

public class Money {
	private String currency;
	private String sum;
	
	public Money(String sum, String curr) {
		this.sum = sum;
		this.currency = curr;
	}

	public String currency() {
		return currency;
	}

	public String sum() {
		return sum;
	}
}
