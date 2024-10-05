package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.dto.RoomDetailsDto;
import com.group_3.cozyHaven.enums.RoomType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Amenities;
import com.group_3.cozyHaven.model.HotelExtra;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.service.RoomService;
import com.group_3.cozyHaven.utility.GetId;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private GetId getId;
	
	@PostMapping("/updateAvailability")
	public void updateRoomBooking(Principal principal){
		String name=principal.getName();
		roomService.updateRoomBooking(name);
	}
	
	@GetMapping("/amenities/{roomId}")
	public ResponseEntity<?> showAmenities(@PathVariable int roomId){
		List<Amenities> amenities=roomService.showAmenities(roomId);
		return ResponseEntity.ok(amenities);
	}
	
	@GetMapping("/{hotelId}/{roomType}")
	public ResponseEntity<?> checkRoomType(@PathVariable int hotelId,@PathVariable RoomType roomType){
		List<Room> rooms=roomService.checkRoomType(hotelId,roomType);
		return ResponseEntity.ok(rooms);
	}
	
	@GetMapping("/details/{roomId}")
	public List<RoomDetailsDto> viewDetails(@PathVariable int roomId){
		return roomService.viewDetails(roomId);
	}
	
	@PutMapping("/update/{roomId}")
	public ResponseEntity<?> updateRoom(@PathVariable int roomId,@RequestBody Room room,MessageDto dto) {
		
		try {
			Room updateroom=roomService.updateRoom(roomId,room);
			return ResponseEntity.ok(updateroom);
			
		} catch (InvalidIdException e) {
		    dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
	
		}
	}
	@PostMapping("/add/{hotelId}")
	public ResponseEntity<?> addRoom(@PathVariable int hotelId,Principal principal, @RequestBody Room room,MessageDto dto){
	       try {
	    	   String username=principal.getName();
	    	   int serviceProviderId=getId.getIdByUsername(username);
			Room addRoom=roomService.addRoom(hotelId,serviceProviderId,room);
			return ResponseEntity.ok(addRoom);
		} 
	       catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
			
	    }
	
	@PostMapping("/add/extras/{roomId}")
	public ResponseEntity<?> addExtra(@PathVariable int roomId,Principal principal,@RequestBody HotelExtra hotelExtra, MessageDto dto ) {
		 try {
			 String username=principal.getName();
			 int serviceProviderId=getId.getIdByUsername(username);
			HotelExtra addExtra=roomService.addExtra(roomId,serviceProviderId,hotelExtra);
			return ResponseEntity.ok(addExtra);
		} catch (InputValidationException e) {
			return ResponseEntity.badRequest().body(dto);
		}
	}
	   
}