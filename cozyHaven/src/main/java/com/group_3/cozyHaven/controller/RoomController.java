package com.group_3.cozyHaven.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.dto.RoomDetailsDto;
import com.group_3.cozyHaven.enums.RoomType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Amenities;
import com.group_3.cozyHaven.model.Booking;
import com.group_3.cozyHaven.model.HotelExtra;
import com.group_3.cozyHaven.model.Image;
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
	
	@PutMapping("/updateAvailability")
	public ResponseEntity<?> updateRoomBooking(){
		
		
			
		List<Booking> book=	roomService.updateRoomBooking();
		return ResponseEntity.ok(book);
		
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
	public ResponseEntity<?> addExtra(@PathVariable int roomId,@RequestBody HotelExtra hotelExtra, MessageDto dto ) {
		 try {
			 
			HotelExtra addExtra=roomService.addExtra(roomId,hotelExtra);
			return ResponseEntity.ok(addExtra);
		} catch (InputValidationException e) {
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/all/{hotelId}")
	public List<Room> getAllRoom(@PathVariable int hotelId){
		List<Room> rooms=roomService.getAllRooms(hotelId);
		return rooms;
	}
	
	@PostMapping("/image/upload/{roomId}")
	public void addRoomImage(@PathVariable int roomId,@RequestParam MultipartFile file) {
		Room room;
		try {
			room = roomService.getById(roomId);
		String location="F:/Angular Fsd/cozyhaven-ui/cozyhaven-client/cozyhaven-client/src/assets";
		Image img=new Image();
			Files.copy(file.getInputStream(), Path.of(location, file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			img.setFilePath(Path.of(location,file.getOriginalFilename()).toString());
			img.setImageName(file.getOriginalFilename());
			img.setRoom(room);
			roomService.save(img);	
			}
		catch(IOException |InvalidIdException e) {
		      e.printStackTrace();
	}
	}
	
	@GetMapping("/get/image/{roomId}")
	public ResponseEntity<?> getImages(@PathVariable int roomId){
		
		try {
			List<Image> images = roomService.getImageByRoomId(roomId);
			return ResponseEntity.ok(images);
		} catch (InputValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	}
	
	@GetMapping("/get/{id}")
	public Room getRoom(@PathVariable int id) {
		return roomService.getRoomById(id);
	}
}