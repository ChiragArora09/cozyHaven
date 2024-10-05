package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.service.CustomerService;
import com.group_3.cozyHaven.service.HotelService;
import com.group_3.cozyHaven.service.RoomService;
import com.group_3.cozyHaven.utility.GetId;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = {"http://localhost:4200"})
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private GetId getId;
	
	// add hotel
	
    @PostMapping("/addHotel")
	public ResponseEntity<?> addHotel(Principal principal, @RequestBody Hotel hotel) {
	    MessageDto dto = new MessageDto();
	    try {
	    	String username=principal.getName();
	    	int serviceProviderId=getId.getIdByUsername(username);
	        Hotel addedHotel = hotelService.addHotel(serviceProviderId, hotel);
	        return ResponseEntity.ok(addedHotel);
	        }
	    catch (InputValidationException e) {
	        dto.setMsg(e.getMessage());
	       return ResponseEntity.badRequest().body(dto);
	    }
	}	
	
    // search hotel
    
    @GetMapping("/search")
	public List<HotelResultDto> searchHotels(@RequestBody HotelInputDto hotelInputDto){
		return hotelService.searchHotels(hotelInputDto.getLocation(),hotelInputDto.getCheckInDate(),hotelInputDto.getCheckOutDate(),hotelInputDto.getNumGuests(),hotelInputDto.getNumRooms());
	}
    
	@GetMapping("/all")
	public List<Hotel> getAllHotel(Principal principal) {
		String username=principal.getName();
		List<Hotel> hotel=hotelService.getAllHotels(username);
		return hotel;
	}

	
}
