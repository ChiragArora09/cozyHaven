package com.group_3.cozyHaven.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.RoomDetailsDto;
import com.group_3.cozyHaven.enums.BookedStatus;
import com.group_3.cozyHaven.enums.RoomType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Amenities;
import com.group_3.cozyHaven.model.Booking;
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.HotelExtra;
import com.group_3.cozyHaven.model.Image;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.AmenitiesRepository;
import com.group_3.cozyHaven.repository.BookingRepository;
import com.group_3.cozyHaven.repository.HotelExtraRepository;
import com.group_3.cozyHaven.repository.HotelRepository;
import com.group_3.cozyHaven.repository.ImageRepository;
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
	private HotelExtraRepository hotelExtraRepository;

    @Autowired
    private ImageRepository imageRepository;
	
	 @Autowired
	 private BookingRepository bookingRepository;

	public Room addRoom(int hotelId, int serviceProviderId,Room room) throws InputValidationException {
			
		ServiceProvider serviceProvider=serviceProviderService.getById(serviceProviderId);
		 Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
	        if (optionalHotel.isEmpty()) {
	            throw new InputValidationException("Hotel not found with id: " + hotelId);
	        }

	        Hotel hotel = optionalHotel.get();
	        room.setHotel(hotel);  
	        return roomRepository.save(room);
		
	}

	public Room updateRoom(int roomId, Room room) throws InvalidIdException {
		Optional<Room> optional=roomRepository.findById(roomId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		
		Room newRoom=optional.get();
		newRoom.setCapacity(room.getCapacity());
		newRoom.setPrice(room.getPrice());
		newRoom.setRoomType(room.getRoomType());
		newRoom.setBedType(room.getBedType());
		newRoom.setHotel(room.getHotel());
		
		return roomRepository.save(newRoom);
		
		
	}

	public List<Amenities> showAmenities(int roomId) {
		return amenitiesRepository.findByRoomId(roomId);
	}
	
	
	public Room getById(int roomId) throws InvalidIdException {
		Optional<Room> optional=roomRepository.findById(roomId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		
		return optional.get();
	}

	public List<Room> checkRoomType(int hotelId, RoomType roomType) {
		
		return roomRepository.findByHotelRoomType(hotelId,roomType);
	}

	public List<Booking> updateRoomBooking()  {
		
		LocalDate date=LocalDate.now();
	    List<Booking> bookings=bookingRepository.findByCheckOutDateLessThanEqual(date);
		
		for(Booking booking:bookings) {
			 Room room = booking.getRoom();
	         int numberOfRoomsBooked = booking.getNumberOfRooms();
			 room.setBookedRooms(room.getBookedRooms() - numberOfRoomsBooked);
	         roomRepository.save(room);
	         booking.setStatus(BookedStatus.CHECKED_OUT);
	         bookingRepository.save(booking);
	        
		}
		 return bookings;
		
	}

	public List<RoomDetailsDto> viewDetails(int roomId) {
		List<Object[]> results=roomRepository.findAvailableRoom(roomId);
		List<RoomDetailsDto> listDto=new ArrayList<>();
		
		for(Object[] obj:results) {
		    String hotelName=obj[0].toString();
		    String bedType=obj[1].toString();
		    String roomType=obj[2].toString();
		    String price=obj[3].toString();
		    String breakFastLunch=obj[4].toString();
		    String breakFast=obj[5].toString();
		    String freeWifi=obj[6].toString();
		    String gym=obj[7].toString();
		    String parkingArea=obj[8].toString();
		    String spa=obj[9].toString();
		    String swimmingPool=obj[10].toString();
		    String cancellationInfo=obj[11].toString();
		    String complimentary=obj[12].toString();   
		    String description=obj[13].toString();
		    RoomDetailsDto dto=new RoomDetailsDto(hotelName, bedType, roomType,price,breakFastLunch,breakFast, freeWifi, gym, parkingArea, spa, swimmingPool, cancellationInfo,complimentary,description);
		    listDto.add(dto);
	}
		return listDto;

}

	public HotelExtra addExtra(int roomId,  HotelExtra hotelExtra) throws InputValidationException {
		 Optional<Room> optionalRoom = roomRepository.findById(roomId);
	        if (optionalRoom.isEmpty()) {
	            throw new InputValidationException("Room not found with id: " + roomId);
	        }

	        Room room = optionalRoom.get();
	        hotelExtra.setRoom(room);
	        return hotelExtraRepository.save(hotelExtra);
	        
	       
	}

	public List<Room> getAllRooms(int hotelId) {
		List<Room> rooms=roomRepository.getAllRooms(hotelId);
		return rooms;
	}

	public void save(Image img) {
		imageRepository.save(img);
		
	}

	public Room getRoomById(int id) {
		return roomRepository.findById(id).get();
	}

	public List<Image> getImageByRoomId(int roomId) throws InputValidationException {
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty()) {
            throw new InputValidationException("Room not found with id: " + roomId);
        }
        return imageRepository.findByRoomId(roomId);
	}

	
	
}
