package com.awg.j20.travelport.flights.serv;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.awg.j20.travelport.flights.cfg.FlightApiConfig;
import com.awg.j20.travelport.flights.serv.deser.Avaliability;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class ApiCall {
	
	private Logger logger = LoggerFactory.getLogger("ApiCall");
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private FlightApiConfig apiConfig;
	
	public Avaliability call(FlightSearchParams params) {
		

		Map<String, String> uriParam = new HashMap<>();
	    uriParam.put("origin", params.origin);
	    uriParam.put("destination", params.destination);
	    uriParam.put("start", "2014-01-02T10:48:00.000Z");
	    uriParam.put("end", "2015-01-02T10:48:00.000Z");
	    uriParam.put("pax", Integer.toHexString(params.pax));
	    
	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiConfig.availabilityEndpoint());
	    for (Map.Entry<String, String> entry : uriParam.entrySet()) {
	        builder.queryParam(entry.getKey(), entry.getValue());
	    }

	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", "application/json");
	    
	    HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
	    													new HttpEntity<String>(headers), String.class);
		
	    String xml = response.getBody();
		logger.info("Got response:" + xml);
		
		
		try {
			XmlMapper xmlMapper = new XmlMapper();
			Avaliability gatewayResponse = xmlMapper.readValue(xml, Avaliability.class);
			return gatewayResponse;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
