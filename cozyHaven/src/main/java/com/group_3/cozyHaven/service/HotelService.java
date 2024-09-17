package com.group_3.cozyHaven.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.HotelResultDto;
import com.group_3.cozyHaven.enums.BedType;
import com.group_3.cozyHaven.enums.RoomType;
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


	public List<HotelResultDto> searchHotels(String location, LocalDate checkInDate, LocalDate checkOutDate,int numberGuests) {
		List<Object[]> results = hotelRepository.findAvailableHotels(location,checkInDate,checkOutDate,numberGuests);
        List<HotelResultDto> listDto = new ArrayList<>();
       
        for (Object[] obj : results) {
        	   int roomId = (int) obj[0]; 
        	    String bedType =  obj[1].toString();
        	    String roomType =obj[2].toString();
      	        double price =  (double)obj[3];
      	      BigDecimal availableRoomsDecimal = (BigDecimal) obj[4]; 
      	      int availableRooms = availableRoomsDecimal.intValue(); 
               String hotelName =  obj[5].toString(); 
        	    String hotelLocation = obj[6].toString();  
        	    
        	    HotelResultDto dto = new HotelResultDto(roomId, bedType, roomType, price, availableRooms, hotelName, hotelLocation);
        	    listDto.add(dto);  
	}
        return listDto;

}
}
