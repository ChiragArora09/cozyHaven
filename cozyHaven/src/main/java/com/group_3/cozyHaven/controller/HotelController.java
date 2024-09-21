package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.HotelInputDto;
import com.group_3.cozyHaven.dto.HotelResultDto;
import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Amenities;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.service.CustomerService;
import com.group_3.cozyHaven.service.HotelService;
import com.group_3.cozyHaven.service.RoomService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CustomerService customerService;
	
    @PostMapping("/add/{serviceProviderId}")
	public ResponseEntity<?> addHotel(@PathVariable int serviceProviderId, @RequestBody Hotel hotel) {
	    MessageDto dto = new MessageDto();
	    try {
	        Hotel addedHotel = hotelService.addHotel(serviceProviderId, hotel);
	        return ResponseEntity.ok(addedHotel);
	        }
	    catch (InputValidationException e) {
	        dto.setMsg(e.getMessage());
	       return ResponseEntity.badRequest().body(dto);
	    }
	}	
	
	

   /*@GetMapping("{location}")
	public List<Hotel> searchHotel(@PathVariable String location) {
		
		return hotelService.searchHotelByLocation(location);
		
	}*/
	
	@GetMapping("/rooms/{hotelId}")
	public ResponseEntity<?> searchRooms(@PathVariable int hotelId){
		
		List<Room> rooms=roomService.findByHotel(hotelId);
		
		return ResponseEntity.ok(rooms);
		
	}
	
	/*@GetMapping("/amenities/{roomId}")
	public ResponseEntity<?> showAmenities(@PathVariable int roomId){
		List<Amenities> amenities=roomService.showAmenities(roomId);
		return ResponseEntity.ok(amenities);
	}*/
	

	@GetMapping("/search/{location}")
	public List<HotelResultDto> searchHotels(@PathVariable String location){
		return hotelService.searchHotels(location);
	}
}
