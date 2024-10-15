package com.group_3.cozyHaven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.HotelRepository;
import com.group_3.cozyHaven.service.HotelService;
import com.group_3.cozyHaven.service.ServiceProviderService;

@SpringBootTest
public class HotelServiceTest {
	
	@InjectMocks
	private HotelService hotelService;
	
	@Mock
	private HotelRepository hotelRepository;
	
	@Mock
	private ServiceProviderService serviceProviderService;
	
	
	@Test
	public void getallTest() {

		Hotel h1=new Hotel();
		h1.setId(1);
		h1.setHotelName("ABC hotel");
		h1.setDescription("enjoy the stay");
		h1.setLocation("chennai");
		
		Hotel h2=new Hotel();
		h2.setId(2);
		h2.setHotelName("XYZ hotel");
		h2.setDescription("enjoy the stay");
		h2.setLocation("pondicherry");
		String username="albus";
		List<Hotel> list=Arrays.asList(h1,h2);
		when(hotelRepository.getAllHotel(username)).thenReturn(list);
		int expectedNum=2;
		
		List<Hotel> hotelList=hotelService.getAllHotels(username);
		
		assertNotNull(hotelList);
		int actualNum=hotelList.size();
		assertEquals(expectedNum,actualNum);
		
	}
	
	@Test
	public void addHotelTest() throws InputValidationException {
		int serviceProviderId=1;
		Hotel hotel=new Hotel();
		ServiceProvider serviceProvider=new ServiceProvider();
		serviceProvider.setId(serviceProviderId);
		hotel.setId(1);
		hotel.setHotelName("ABC");
		hotel.setDescription("enjoy your stay");
		hotel.setLocation("mumbai");
		when(serviceProviderService.getById(serviceProviderId)).thenReturn(serviceProvider);
		when(hotelRepository.save(hotel)).thenReturn(hotel);
		Hotel actualValue=hotelService.addHotel(serviceProviderId, hotel);
		assertNotNull(actualValue);
		assertEquals(hotel, actualValue);
	}
	
}

		
