package com.awg.j20.travelport.flights.serv;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import com.awg.j20.travelport.flights.cfg.FlightApiConfig;
import com.awg.j20.travelport.flights.serv.deser.Avaliability;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@RestClientTest(components = ApiCall.class)
@AutoConfigureWebClient(registerRestTemplate=true)
public class ApiCallTest {
	@MockBean
	private FlightApiConfig mockConfig;
    @Autowired
    private MockRestServiceServer mockRestServiceServer;
	@Autowired
	private ApiCall apiCallSrvUnderTest;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void test1() {
		when(mockConfig.availabilityEndpoint()).thenReturn("http://localhost:9001/flights");
		
		this.mockRestServiceServer
			.expect(requestTo(mockConfig.availabilityEndpoint()
					+ "?pax=1&origin=LED&destination=AMS&start=2014-01-02T10:48:00.000Z&end=2015-01-02T10:48:00.000Z"))
			.andRespond(withSuccess(respBasic(), MediaType.APPLICATION_XML));
		
		//when:
		FlightSearchParams searchParams = new FlightSearchParams();
		searchParams.origin = "LED";
		searchParams.destination = "AMS";
		searchParams.dateStart = "2015-01-02T10:48:00.000Z";
		searchParams.dateEnd = "2015-01-02T10:48:00.000Z";
		searchParams.pax = 1;

		Avaliability actual = apiCallSrvUnderTest.call(searchParams);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getFlight()).isNotEmpty();
		assertThat(actual.getFlight().size()).isEqualTo(2);
		assertThat(actual.getFlight().get(0).carrierCode).isEqualTo("EI");
		assertThat(actual.getFlight().get(1).carrierCode).isEqualTo("UA");
		assertThat(actual.getFlight().get(0).flightDesignator).isEqualTo("EI554");
		assertThat(actual.getFlight().get(1).flightDesignator).isEqualTo("UA657");
		assertThat(actual.getFlight().get(0).originAirport).isEqualTo("IST");
		assertThat(actual.getFlight().get(1).originAirport).isEqualTo("DUB");
		assertThat(actual.getFlight().get(0).destinationAirport).isEqualTo("AMS");
		assertThat(actual.getFlight().get(1).destinationAirport).isEqualTo("LED");
		//TODO: dates
		assertThat(actual.getFlight().get(0).departureDate).isNotBlank();
		assertThat(actual.getFlight().get(1).departureDate).isNotBlank();
		assertThat(actual.getFlight().get(0).arrivalDate).isNotBlank();
		assertThat(actual.getFlight().get(1).arrivalDate).isNotBlank();
		//fares:
		assertThat(actual.getFlight().get(0).getFares()).isNotEmpty();
		assertThat(actual.getFlight().get(0).getFares().size()).isEqualTo(3);
		
		assertThat(actual.getFlight().get(0).getFares().get(0).clazz).isEqualTo("FIF");
		assertThat(actual.getFlight().get(0).getFares().get(0).basePrice).isEqualTo("100");
		assertThat(actual.getFlight().get(0).getFares().get(0).fees).isEqualTo("EUR 17.10");
		assertThat(actual.getFlight().get(0).getFares().get(0).tax).isEqualTo("EUR 13.60");
		
		assertThat(actual.getFlight().get(0).getFares().get(1).clazz).isEqualTo("CIF");
		assertThat(actual.getFlight().get(0).getFares().get(1).basePrice).isEqualTo("200");
		assertThat(actual.getFlight().get(0).getFares().get(1).fees).isEqualTo("EUR 27.10");
		assertThat(actual.getFlight().get(0).getFares().get(1).tax).isEqualTo("EUR 23.60");
		
		assertThat(actual.getFlight().get(0).getFares().get(2).clazz).isEqualTo("YIF");
		assertThat(actual.getFlight().get(0).getFares().get(2).basePrice).isEqualTo("300");
		assertThat(actual.getFlight().get(0).getFares().get(2).fees).isEqualTo("EUR 37.10");
		assertThat(actual.getFlight().get(0).getFares().get(2).tax).isEqualTo("EUR 33.60");
	}
	
	public String respBasic() {
		return "<Availability>"
				+ "<Flight>"
				+ "	<CarrierCode>EI</CarrierCode>"
				+ "	<FlightDesignator>EI554</FlightDesignator>"
				+ " <OriginAirport>IST</OriginAirport>"
				+ " <DestinationAirport>AMS</DestinationAirport>"
				+ " <DepartureDate>2014-01-02T10:48:00.000Z</DepartureDate>"
				+ " <ArrivalDate>2014-01-02T13:04:00.000Z</ArrivalDate>"
				+ "	<Fares>"
				+ "		<Fare class=\"FIF\">"
				+ "      <BasePrice>100</BasePrice>"
				+ "      <Fees>EUR 17.10</Fees>"
				+ "      <Tax>EUR 13.60</Tax>"
				+ "		</Fare>"
				+ "		<Fare class=\"CIF\">"
				+ "      <BasePrice>200</BasePrice>"
				+ "      <Fees>EUR 27.10</Fees>"
				+ "      <Tax>EUR 23.60</Tax>"
				+ "		</Fare>"
				+ "		<Fare class=\"YIF\">"
				+ "      <BasePrice>300</BasePrice>"
				+ "      <Fees>EUR 37.10</Fees>"
				+ "      <Tax>EUR 33.60</Tax>"
				+ "		</Fare>"
				+ "	</Fares>"
				+ "</Flight>"
				+ "<Flight>"
				+ "	<CarrierCode>UA</CarrierCode>"
				+ "	<FlightDesignator>UA657</FlightDesignator>"
				+ " <OriginAirport>DUB</OriginAirport>"
				+ " <DestinationAirport>LED</DestinationAirport>"
				+ " <DepartureDate>2014-01-02T10:48:00.000Z</DepartureDate>"
				+ " <ArrivalDate>2014-01-02T13:04:00.000Z</ArrivalDate>"
				+ "	<Fares>"
				+ "		<Fare>"
				+ "      <BasePrice>200</BasePrice>"
				+ "		</Fare>"
				+ "	</Fares>"
				+ "</Flight>"
				+ "</Availability>";
	}
	
	public String respSingle() {
		return "<Availability>\r\n" + 
				"  <Flight>\r\n" + 
				"    <CarrierCode>EI</CarrierCode>\r\n" + 
				"    <FlightDesignator>EI554</FlightDesignator>\r\n" + 
				"    <OriginAirport>IST</OriginAirport>\r\n" + 
				"    <DestinationAirport>DUB</DestinationAirport>\r\n" + 
				"    <DepartureDate>2014-01-02T10:48:00.000Z</DepartureDate>\r\n" + 
				"    <ArrivalDate>2014-01-02T13:04:00.000Z</ArrivalDate>\r\n" + 
				"    <Fares>\r\n" + 
				"      <Fare class=\"FIF\">\r\n" + 
				"        <BasePrice>EUR 272.00</BasePrice>\r\n" + 
				"        <Fees>EUR 17.00</Fees>\r\n" + 
				"        <Tax>EUR 13.60</Tax>\r\n" + 
				"      </Fare>\r\n" + 
				"      <Fare class=\"CIF\">\r\n" + 
				"        <BasePrice>EUR 136.00</BasePrice>\r\n" + 
				"        <Fees>EUR 17.00</Fees>\r\n" + 
				"        <Tax>EUR 13.60</Tax>\r\n" + 
				"      </Fare>\r\n" + 
				"      <Fare class=\"YIF\">\r\n" + 
				"        <BasePrice>EUR 68.00</BasePrice>\r\n" + 
				"        <Fees>EUR 17.00</Fees>\r\n" + 
				"        <Tax>EUR 13.60</Tax>\r\n" + 
				"      </Fare>\r\n" + 
				"    </Fares>\r\n" + 
				"  </Flight>\r\n" + 
				"</Availability>";
	}

}
