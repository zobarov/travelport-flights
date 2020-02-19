package com.awg.j20.travelport.flights.cntrl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.awg.j20.travelport.flights.serv.ApiCall;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FlightsController.class)
public class FlightsControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	private static MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
			  											  MediaType.APPLICATION_JSON.getSubtype(),
			  											  Charset.forName("UTF-8"));
	@MockBean
	private ApiCall mockApiCall;

	@DisplayName("Main contract for flight endpoint")
    @Test
    void shouldOk_AllValidParams() throws Exception {	
				
		//Mockito
		//	.when(mockApiCall.call(Mockito.any())).
		
		//when:
        mockMvc.perform(get("/flights/availability/{origin}/{dest}", "LED", "DUB")
        			.accept(MediaType.APPLICATION_JSON))
        			//.param("start", "2")
        			//.param("operandB", "3"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.availability").exists())
        
        .andExpect(jsonPath("$.availability[0].flight").exists())
        
        .andExpect(jsonPath("$.availability[0].flight.operator").exists())
        .andExpect(jsonPath("$.availability[0].flight.operator").value("RU"))
        .andExpect(jsonPath("$.availability[0].flight.flightNumber").exists())
        .andExpect(jsonPath("$.availability[0].flight.flightNumber").value("L12345"))
        .andExpect(jsonPath("$.availability[0].flight.departsFrom").exists())
        .andExpect(jsonPath("$.availability[0].flight.departsFrom").value("LED"))
        .andExpect(jsonPath("$.availability[0].flight.arrivesAt").exists())
        .andExpect(jsonPath("$.availability[0].flight.arrivesAt").value("AMS"))
        
        .andExpect(jsonPath("$.availability[0].flight.departsOn").exists())
        .andExpect(jsonPath("$.availability[0].flight.departsOn.date").value("01-01-2014"))
        .andExpect(jsonPath("$.availability[0].flight.departsOn.time").value("08:52AM"))
        
        .andExpect(jsonPath("$.availability[0].flight.arrivesOn").exists())
        .andExpect(jsonPath("$.availability[0].flight.arrivesOn.date").value("02-01-2014"))
        .andExpect(jsonPath("$.availability[0].flight.arrivesOn.time").value("10:00AM"))
        
        .andExpect(jsonPath("$.availability[0].flight.flightTime").exists())
        .andExpect(jsonPath("$.availability[0].flight.flightTime").value("03:57"))
        
        .andExpect(jsonPath("$.availability[0].flight.farePrices").exists())
        //first
        .andExpect(jsonPath("$.availability[0].flight.farePrices.first").exists())
        	.andExpect(jsonPath("$.availability[0].flight.farePrices.first.ticket").exists())
        .andExpect(jsonPath("$.availability[0].flight.farePrices.first.ticket.currency").value("USD"))
        .andExpect(jsonPath("$.availability[0].flight.farePrices.first.ticket.amount").value("100"))
        	.andExpect(jsonPath("$.availability[0].flight.farePrices.first.bookingFee").exists())
        .andExpect(jsonPath("$.availability[0].flight.farePrices.first.bookingFee.currency").value("USD"))
        .andExpect(jsonPath("$.availability[0].flight.farePrices.first.bookingFee.amount").value("100"))
        	.andExpect(jsonPath("$.availability[0].flight.farePrices.first.tax").exists())
        .andExpect(jsonPath("$.availability[0].flight.farePrices.first.tax.currency").value("USD"))
        .andExpect(jsonPath("$.availability[0].flight.farePrices.first.tax.amount").value("100"))
        //biz
        .andExpect(jsonPath("$.availability[0].flight.farePrices.business").exists())
        	.andExpect(jsonPath("$.availability[0].flight.farePrices.business.ticket").exists())
        .andExpect(jsonPath("$.availability[0].flight.farePrices.business.ticket.currency").value("EUR"))
        .andExpect(jsonPath("$.availability[0].flight.farePrices.business.ticket.amount").value("200"))
        	.andExpect(jsonPath("$.availability[0].flight.farePrices.business.bookingFee").exists())
        .andExpect(jsonPath("$.availability[0].flight.farePrices.business.bookingFee.currency").value("EUR"))
        .andExpect(jsonPath("$.availability[0].flight.farePrices.business.bookingFee.amount").value("200"))
        	.andExpect(jsonPath("$.availability[0].flight.farePrices.business.tax").exists())
        .andExpect(jsonPath("$.availability[0].flight.farePrices.business.tax.currency").value("EUR"))
        .andExpect(jsonPath("$.availability[0].flight.farePrices.business.tax.amount").value("200"))
        //economy
        .andExpect(jsonPath("$.availability[0].flight.farePrices.economy").exists())
        	.andExpect(jsonPath("$.availability[0].flight.farePrices.economy.ticket").exists())
        .andExpect(jsonPath("$.availability[0].flight.farePrices.economy.ticket.currency").value("CKZ"))
        .andExpect(jsonPath("$.availability[0].flight.farePrices.economy.ticket.amount").value("50"))
        	.andExpect(jsonPath("$.availability[0].flight.farePrices.economy.bookingFee").exists())
        .andExpect(jsonPath("$.availability[0].flight.farePrices.economy.bookingFee.currency").value("CKZ"))
        .andExpect(jsonPath("$.availability[0].flight.farePrices.economy.bookingFee.amount").value("50"))
        	.andExpect(jsonPath("$.availability[0].flight.farePrices.economy.tax").exists())
        .andExpect(jsonPath("$.availability[0].flight.farePrices.economy.tax.currency").value("CKZ"))
        .andExpect(jsonPath("$.availability[0].flight.farePrices.economy.tax.amount").value("50"));       
    }
}
