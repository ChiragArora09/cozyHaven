package com.group_3.cozyHaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.enums.RoomType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@PostMapping("/add/{serviceProviderId}/{hotelId}")
	public ResponseEntity<?> addRoom(@PathVariable int serviceProviderId,@PathVariable int hotelId, @RequestBody Room room,MessageDto dto){
	    // MessageDto dto = new MessageDto();
	       try {
			Room addRoom=roomService.addRoom(hotelId, serviceProviderId,room);
			return ResponseEntity.ok(addRoom);
		} 
	       catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
			
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
	
	@GetMapping("/{hotelId}/{roomType}")
	public ResponseEntity<?> checkRoomType(@PathVariable int hotelId,@PathVariable RoomType roomType){
		List<Room> rooms=roomService.checkRoomType(hotelId,roomType);
		System.out.println(rooms);
		return ResponseEntity.ok(rooms);
	}
	
	@GetMapping("/updateAvailability")
	public void updateRoomBooking(){
		roomService.updateRoomBooking();
	}
	
}