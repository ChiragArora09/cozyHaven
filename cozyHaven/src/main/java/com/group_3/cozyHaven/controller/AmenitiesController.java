package com.group_3.cozyHaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Amenities;
import com.group_3.cozyHaven.service.AmenitiesService;

@RestController
@RequestMapping("/amenities")
public class AmenitiesController {
	
	@Autowired
	private AmenitiesService amenitiesService;
	
	@PostMapping("/add/{roomId}")
	public ResponseEntity<?> addAmenities(@PathVariable int roomId, @RequestBody Amenities amenities ) {
		MessageDto dto=new MessageDto();
		try {
			amenities =amenitiesService.addAmenities(roomId,amenities);
			return ResponseEntity.ok(amenities);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
	}
	
	@GetMapping("/{roomId}")
	public List<Amenities> getAmenitiesByRoom(@PathVariable int roomId){
		return amenitiesService.getAmeitiesByRoom(roomId);
	}

}
