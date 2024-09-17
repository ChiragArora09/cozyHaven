package com.group_3.cozyHaven.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.BookingDetailsDto;
import com.group_3.cozyHaven.enums.BookedStatus;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.exception.RoomUnavailableException;
import com.group_3.cozyHaven.model.Booking;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.repository.BookingRepository;
import com.group_3.cozyHaven.repository.CustomerRepository;
import com.group_3.cozyHaven.repository.RoomRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RoomService roomService;
	
	public Booking bookRoom(int customerId, int roomId, Booking booking) throws InvalidIdException, RoomUnavailableException {
		
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		if (customerOpt.isEmpty()) {
			throw new InvalidIdException("Please sign Up to Book Hotels");
		}
		Customer customer = customerOpt.get();
		
		Optional<Room> roomOpt = roomRepository.findById(roomId);
		if (roomOpt.isEmpty()) {
			throw new InvalidIdException("Room is not available");
		}
		Room room = roomOpt.get();
		
		List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(
		        roomId, booking.getCheckInDate(), booking.getCheckOutDate()
		    );
		    if (!overlappingBookings.isEmpty()) {
		        throw new RoomUnavailableException("The room is already booked for the selected dates.");
		    }
		
		int available=room.getTotalRooms()-room.getBookedRooms();
		if (booking.getNumberOfRooms() > available || available<=0) {
			throw new RoomUnavailableException("Not enough room available");
		}
		
		booking.setCustomer(customer);
	    booking.setRoom(room);
	    booking.setBookedDate(LocalDate.now());
		booking.setStatus(BookedStatus.CONFIRMED);
    
		Booking savedBooking = bookingRepository.save(booking);
		
		Integer diff=bookingRepository.findDateDiff(savedBooking.getId());
		double totalAmount;
		
		if(diff==null) {
			totalAmount=room.getPrice();
		}
		else {
			int numberOfRooms=booking.getNumberOfRooms();
			double roomPrice=room.getPrice();
			totalAmount=diff*roomPrice*numberOfRooms;
		}
			savedBooking.setTotalAmount(totalAmount);
			bookingRepository.save(savedBooking);
		room.setBookedRooms(room.getBookedRooms() + booking.getNumberOfRooms());
		roomRepository.save(room);
		
		
		return savedBooking;
	}

	public List<BookingDetailsDto> getBookingOfCustomer(int customerId) {
		    List<Booking> bookings = bookingRepository.findAllBooking(customerId);
	        List<BookingDetailsDto> bookingDetails = new ArrayList<>();

	        for (Booking booking : bookings) {
	            Room room = booking.getRoom();
	            String hotelName = room.getHotel().getHotelName();
	            String location = room.getHotel().getLocation();

	            BookingDetailsDto dto = new BookingDetailsDto(
	                booking.getBookedDate(),
	                booking.getCheckOutDate(),
	                booking.getCheckInDate(),
	                booking.getNumberOfRooms(),
	                booking.getNumGuests(),
	                booking.getTotalAmount(),
	                booking.getStatus().name(), 
	                hotelName,
	                location);

	            bookingDetails.add(dto);
	        }

	        return bookingDetails;
	
		
	}

	public Booking cancelBooking(int customerId,LocalDate bookedDate) throws InvalidIdException {
		
		Optional<Booking> bookingOpt = bookingRepository.findByBookedDate(customerId,bookedDate);
		if (bookingOpt.isEmpty()) {
			throw new InvalidIdException("No booking made");
		}
		Booking booking = bookingOpt.get();
		
		booking.setStatus(BookedStatus.CANCELLED);
		Booking savedBooking=bookingRepository.save(booking);
		
		Optional<Room> roomOpt = roomRepository.findById(booking.getRoom().getId());
		if (roomOpt.isEmpty()) {
			throw new InvalidIdException("Room is not booked");
		}
		Room room = roomOpt.get();
		
		room.setBookedRooms(room.getBookedRooms()-1);
		roomRepository.save(room);
		return booking;
	}
}
