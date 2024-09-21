package com.group_3.cozyHaven.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.enums.BookedStatus;
import com.group_3.cozyHaven.enums.RoomType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Amenities;
import com.group_3.cozyHaven.model.Booking;
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.AmenitiesRepository;
import com.group_3.cozyHaven.repository.BookingRepository;
import com.group_3.cozyHaven.repository.HotelRepository;
import com.group_3.cozyHaven.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
    private HotelRepository hotelRepository;
	
	@Autowired
	private AmenitiesRepository amenitiesRepository;
	
	@Autowired
	private ServiceProviderService serviceProviderService;

    
	 @Autowired
	 private BookingRepository bookingRepository;

	public Room addRoom(int hotelId, int serviceProviderId,Room room) throws InputValidationException {
			
		ServiceProvider serviceProvider=serviceProviderService.getById(serviceProviderId);
		 Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
	        if (optionalHotel.isEmpty()) {
	            throw new InputValidationException("Hotel not found with id: " + hotelId);
	        }

	        Hotel hotel = optionalHotel.get();
	        room.setHotel(hotel);  // Associate the room with the hotel
	        return roomRepository.save(room);
		
	}

	public Room updateRoom(int roomId, Room room) throws InvalidIdException {
		Optional<Room> optional=roomRepository.findById(roomId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		
		Room oldRoom=optional.get();
		oldRoom.setCapacity(room.getCapacity());
		oldRoom.setPrice(room.getPrice());
		oldRoom.setRoomType(room.getRoomType());
		oldRoom.setBedType(room.getBedType());
		oldRoom.setHotel(room.getHotel());
		
		return roomRepository.save(oldRoom);
		
		
	}

	public List<Room> findByHotel(int hotelId) {
		
		return roomRepository.findByHotel(hotelId);
	
	}

	/*public List<Amenities> showAmenities(int roomId) {
		return amenitiesRepository.findByRoomId(roomId);
	}
	*/
	
	public Room getById(int roomId) throws InvalidIdException {
		Optional<Room> optional=roomRepository.findById(roomId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		
		return optional.get();
	}

	public List<Room> checkRoomType(int hotelId, RoomType roomType) {
		
		return roomRepository.findByHotelRoomType(hotelId,roomType);
	}

	public void updateRoomBooking(String name) {
		LocalDate date=LocalDate.now();
		
		
		List<Booking> bookings=bookingRepository.findByCheckOutDate(date);
		
		for(Booking booking:bookings) {
			 Room room = booking.getRoom();
	            
			 int numberOfRoomsBooked = booking.getNumberOfRooms();
			 
			 room.setBookedRooms(room.getBookedRooms() - numberOfRoomsBooked);
	         roomRepository.save(room);
	            
	            booking.setStatus(BookedStatus.CHECKED_OUT);
	            bookingRepository.save(booking);
		}
		
	}

}
