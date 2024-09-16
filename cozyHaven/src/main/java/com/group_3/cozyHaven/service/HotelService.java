package com.group_3.cozyHaven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	private ServiceProviderService serviceProviderService;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel addHotel(int serviceProviderId, Hotel hotel) throws InputValidationException {
		
		ServiceProvider serviceProvider=serviceProviderService.getById(serviceProviderId);
		hotel.setServiceProvider(serviceProvider);
		return hotelRepository.save(hotel);
		
		
	}

	public List<Hotel> searchHotelByLocation(String location) {
		return hotelRepository.findByLocation(location);
		
	}

}
