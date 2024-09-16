package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Amenities;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.repository.AmenitiesRepository;
import com.group_3.cozyHaven.repository.RoomRepository;

@Service
public class AmenitiesService {

	
	@Autowired
	private AmenitiesRepository amenitiesRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	public Amenities addAmenities(int roomId,Amenities amenities) throws InvalidIdException {
		
		Optional<Room> roomOptional=roomRepository.findById(roomId);
		if(roomOptional.isEmpty())
			throw new InvalidIdException("Room Id is invalid");
		Room room=roomOptional.get();
		amenities.setRoom(room);
		return amenitiesRepository.save(amenities);
		
		
		
	}

}
