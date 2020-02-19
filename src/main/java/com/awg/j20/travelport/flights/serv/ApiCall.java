package com.awg.j20.travelport.flights.serv;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;



@Service
public class ApiCall {
	
	private Logger logger = LoggerFactory.getLogger("ApiCall");
	
	private RestTemplate restTemplate = new RestTemplate();;
	
	//https://private-anon-9065052855-mockairline.apiary-mock.com/flights/{origin}/{destination}/{start}/{end}/{pax}
	public void call(FlightSearchParams params) {
		String url = "https://private-anon-9065052855-mockairline.apiary-mock.com/flights/{origin}/{destination}/{start}/{end}/{pax}";
		

		Map<String, String> uriParam = new HashMap<>();
	    uriParam.put("origin", params.origin);
	    uriParam.put("destination", params.destination);
	    uriParam.put("start", "2014-01-02T10:48:00.000Z");
	    uriParam.put("end", "2015-01-02T10:48:00.000Z");
	    uriParam.put("pax", Integer.toHexString(params.pax));
	    
	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
	    for (Map.Entry<String, String> entry : uriParam.entrySet()) {
	        builder.queryParam(entry.getKey(), entry.getValue());
	    }

	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", "application/json");
	    
	    HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity(headers), String.class);
		
		logger.info("Got response:" + response.getBody());
	}

}
