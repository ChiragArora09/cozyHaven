package com.group_3.cozyHaven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.group_3.cozyHaven.dto.FlightBetweenStopsDto;
import com.group_3.cozyHaven.enums.ClassType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.FlightRepository;
import com.group_3.cozyHaven.service.FlightService;
import com.group_3.cozyHaven.service.ServiceProviderService;

@SpringBootTest
public class FlightServiceTest {
	
	@Mock
	private FlightRepository flightRepository;
	
	@Mock
	private ServiceProviderService serviceProviderService;
	
	@InjectMocks
	private FlightService flightService;
	
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void addFlightTest() throws InputValidationException {
		int serviceProviderId = 1;
		Flight flight = new Flight();
		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setId(serviceProviderId);
		
		when(serviceProviderService.getById(serviceProviderId)).thenReturn(serviceProvider);
		
		when(flightRepository.save(flight)).thenReturn(flight);
		
		Flight result = flightService.addFlight(serviceProviderId, flight);
		
		verify(serviceProviderService).getById(serviceProviderId);
		verify(flightRepository).save(flight);
		
		assertNotNull(result);
		assertEquals(serviceProvider, result.getServiceProvider());
	}
	
	@Test
	void getFlightsBetweenStopsTest() {
		String source = "Delhi";
		String destination = "Pune";
		ClassType classType = ClassType.ECONOMY;
		
		// Mock response for getFlightIdsForSourceAndDestination
        List<Object[]> mockFlightIdList = new ArrayList<>();
        mockFlightIdList.add(new Object[]{1}); // Flight ID 1
        when(flightRepository.getFlightIdsForSourceAndDestination(source, destination, classType)).thenReturn(mockFlightIdList);
		
		// Mock response for getFlightBetweenStops
        List<Object[]> mockFlightInfo = List.of(
        	    new Object[]{LocalTime.of(8, 0), LocalTime.of(10, 0), 0, "Indigo", "Indigo 2100", "The fastest and the most comfortable", "Delhi", 1, 0},
        	    new Object[]{LocalTime.of(13, 30), LocalTime.of(14, 30), 200, "Indigo", "Indigo 2100", "The fastest and the most comfortable", "Pune", 1, 2}
        	);
        
        when(flightRepository.getFlightBetweenStops(1, source, destination)).thenReturn(mockFlightInfo);
        
        List<FlightBetweenStopsDto> result = flightService.getFlightsBetweenStops(source, destination, classType);
        
        assertEquals(1, result.size());
        FlightBetweenStopsDto dto = result.get(0);
        
        assertEquals("Indigo", dto.getFlightName());
        assertEquals("Indigo 2100", dto.getFlightNumber());
        assertEquals("The fastest and the most comfortable", dto.getFlightDescription());
        assertEquals("Delhi", dto.getSource());
        assertEquals("Pune", dto.getDestination());
        assertEquals(200, dto.getDistance());
        assertEquals(1100.0, dto.getAmount());
        assertEquals(1, dto.getFlightId());
        assertEquals(0, dto.getSourceStopNo());
        assertEquals(2, dto.getDestinationStopNo());
        
        verify(flightRepository).getFlightIdsForSourceAndDestination(source, destination, classType);
        verify(flightRepository).getFlightBetweenStops(1, source, destination);

	}

}
