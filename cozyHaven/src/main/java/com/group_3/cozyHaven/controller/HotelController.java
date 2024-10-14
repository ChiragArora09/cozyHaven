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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group_3.cozyHaven.dto.HotelInputDto;
import com.group_3.cozyHaven.dto.HotelResultDto;
import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.HotelImage;
import com.group_3.cozyHaven.model.Review;
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
    
    @PostMapping("/search")
	public List<HotelResultDto> searchHotels(@RequestBody HotelInputDto hotelInputDto){
		return hotelService.searchHotels(hotelInputDto.getLocation(),hotelInputDto.getCheckInDate(),hotelInputDto.getCheckOutDate(),hotelInputDto.getNumGuests(),hotelInputDto.getNumRooms());
	}

    @GetMapping("/list/{location}")
    public List<Hotel> hotel(@PathVariable String location){
    	List<Hotel> hotels=hotelService.getAllHotelsByLocation(location);
		return hotels;
    }
    
	@DeleteMapping("/delete/{hotelId}")
	public ResponseEntity<?> deleteHotel(@PathVariable int hotelId,Principal principal) {
    	try {
    		System.out.println(hotelId);
    		System.out.println(principal.getName());
    		String username=principal.getName();
    		System.out.println(username);
	    	int serviceProviderId=getId.getIdByUsername(username);
	    	System.out.println(serviceProviderId);
			Hotel hotel=hotelService.deleteHotel(hotelId,serviceProviderId);
			return ResponseEntity.ok(hotel);
		} catch (InputValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/get/{hotelId}")
	public Hotel getHotel(@PathVariable int hotelId) {
		return hotelService.getHotelById(hotelId);
	}
	
	
	@GetMapping("/all")
	public List<Hotel> getAllHotel(Principal principal) {
		String username=principal.getName();
		List<Hotel> hotel=hotelService.getAllHotels(username);
		return hotel;
	}
	
	@PostMapping("/image/upload/{hotelId}")
	public void addRoomImage(@PathVariable int hotelId,@RequestParam MultipartFile file) {
		
		try {
			Hotel hotel = hotelService.getById(hotelId);
		String location="F:/Angular Fsd/cozyhaven-ui/cozyhaven-client/cozyhaven-client/src/assets";
		HotelImage hotelImage=new HotelImage();
			Files.copy(file.getInputStream(), Path.of(location, file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			hotelImage.setFilePath(Path.of(location,file.getOriginalFilename()).toString());
			hotelImage.setImageName(file.getOriginalFilename());
			hotelImage.setHotel(hotel);
			hotelService.save(hotelImage);	
			}
		catch(IOException | InvalidIdException e) {
		      e.printStackTrace();
	}

}
	
	@GetMapping("/reviews/{hotelId}")
	public List<Review> getReviews(@PathVariable int hotelId) {
		return hotelService.getReviews(hotelId);
		
	}
	}
