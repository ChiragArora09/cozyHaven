package com.group_3.cozyHaven.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.BookingDetailsDto;
import com.group_3.cozyHaven.enums.BookedStatus;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.exception.RoomUnavailableException;
import com.group_3.cozyHaven.model.Booking;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.repository.BookingRepository;
import com.group_3.cozyHaven.repository.CustomerRepository;
import com.group_3.cozyHaven.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	
	public Booking bookRoom(int roomId, int customerId, Booking booking) 
	        throws InvalidIdException, RoomUnavailableException, InputValidationException {
	    
	    // Validate customer
	    Optional<Customer> customerOpt = customerRepository.findById(customerId);
	    if (customerOpt.isEmpty()) {
	        throw new InvalidIdException("Please sign Up to Book Hotels");
	    }
	    Customer customer = customerOpt.get();
	    
	    // Validate room
	    Optional<Room> roomOpt = roomRepository.findById(roomId);
	    if (roomOpt.isEmpty()) {
	        throw new InvalidIdException("Room is not available");
	    }
	    Room room = roomOpt.get();
	    
	    List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(roomId, booking.getCheckInDate(), booking.getCheckOutDate());
	    if (!overlappingBookings.isEmpty()) {
	        throw new RoomUnavailableException("The room is already booked for the selected dates.");
	    }
	    
	    // Check available rooms
	    int available = room.getTotalRooms() - room.getBookedRooms();
	    if (booking.getNumberOfRooms() > available || available== 0) {
	        throw new RoomUnavailableException("Not enough room available");
	    }
	    
	    if (booking.getNumGuests() > room.getCapacity()) {
	        throw new RoomUnavailableException("The number of guests exceeds the room capacity.");
	    }
	    
	  
	    booking.setCustomer(customer);
	    booking.setRoom(room);
	    booking.setBookedDate(LocalDate.now());
	    booking.setStatus(BookedStatus.CONFIRMED);
	    
	    Booking savedBooking = bookingRepository.save(booking);
	    Integer diff = bookingRepository.findDateDiff(savedBooking.getId());
	    double totalAmount;

	    
	    if (diff == null) {
	        totalAmount = room.getPrice();
	    } else {
	        int numberOfRooms = booking.getNumberOfRooms();
	        double roomPrice = room.getPrice();
	        totalAmount = diff * roomPrice * numberOfRooms;
	    }
	    
	    savedBooking.setTotalAmount(totalAmount);
	    bookingRepository.save(savedBooking);
	    
	    
	    room.setBookedRooms(room.getBookedRooms() + booking.getNumberOfRooms());
	    roomRepository.save(room);
	    
	    return savedBooking;
	}



	public Booking cancelBooking(int customerId,int bookingId) throws InvalidIdException {
		
		Optional<Booking> bookingOpt = bookingRepository.findByBookedDate(customerId,bookingId);
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
	
	public List<BookingDetailsDto> getBookingOfCustomer(int customerId) {
	    List<Object[]> list = bookingRepository.findAllBooking(customerId);  
	    List<BookingDetailsDto> bookingDetails = new ArrayList<>();
	    
	    for (Object[] obj : list) { 
	    	int bid=(int)obj[0];
	        LocalDate bookedDate =LocalDate.parse(obj[1].toString());  
	        String checkInDate = obj[2].toString();
	        LocalDate checkOutDate = LocalDate.parse(obj[3].toString());
	        int numberOfRooms = (int) obj[4];
	        int numGuests = (int) obj[5];
	        String totalAmount = obj[6].toString();
	        String status = obj[7].toString();
	        
	        String hotelName = obj[8].toString();
	        String location = obj[9].toString();
	        int hotelId=(int)obj[10];
	        
	        BookingDetailsDto dto = new BookingDetailsDto(bid,bookedDate,checkInDate,checkOutDate, numberOfRooms, numGuests, totalAmount, status, hotelId, hotelName, location);
	        bookingDetails.add(dto);
	    }

	    return bookingDetails;
	}


	public List<BookingDetailsDto> allBooking(int serviceProviderId) {
		List<Object[]> list=bookingRepository.findBooking(serviceProviderId);
		List<BookingDetailsDto> bookingDetailsDto=new ArrayList<>();	
		
		for(Object[] obj:list) {
			String hotelName = obj[0].toString();
	        String location = obj[1].toString();
			LocalDate bookedDate =LocalDate.parse(obj[2].toString());  
			LocalDate checkInDate = LocalDate.parse(obj[3].toString());
	        String checkOutDate = obj[4].toString();
	        
	        String status = obj[5].toString();
	        
	        BookingDetailsDto dto=new BookingDetailsDto(bookedDate, checkOutDate, checkInDate,status,hotelName, location);
	        bookingDetailsDto.add(dto);
		}
		return bookingDetailsDto;
		}
		
	public List<BookingDetailsDto> getMyBooking(int customerId, String bookingType, String bookingPeriod) {
		if(bookingType.equals("Hotel")) {
			List<Object[]> list=bookingRepository.getBookedDate(customerId);
			List<BookingDetailsDto> bookingDetails = new ArrayList<>();
		    
		    for (Object[] obj : list) { 
		    	int bid=(int)obj[0];
		        LocalDate bookedDate =LocalDate.parse(obj[1].toString());  
		        String checkOutDate = obj[2].toString();
		        LocalDate checkInDate = LocalDate.parse(obj[3].toString());
		        
		        int numberOfRooms = (int) obj[4];
		        int numGuests = (int) obj[5];
		        
		        String totalAmount = obj[6].toString();
		        String status = obj[7].toString();
		        int hotelId=(int)obj[8];
		        String hotelName = obj[9].toString();
		        String location = obj[10].toString();
		        
		        BookingDetailsDto dto = new BookingDetailsDto(bid,bookedDate,checkOutDate,checkInDate, numberOfRooms, numGuests, totalAmount, status,hotelId, hotelName, location);
		        bookingDetails.add(dto);
		    }
		    if(bookingPeriod.equals("Past")) {
		    	List<BookingDetailsDto> filteredPastList=bookingDetails.stream().filter(d->d.getCheckInDate().isBefore(LocalDate.now()) && !d.getStatus().equals("CANCELLED")).toList();
		    	return filteredPastList;
		    }
		    else if(bookingPeriod.equals("Upcoming")) {
		    	List<BookingDetailsDto> filteredUpcomingList=bookingDetails.stream().filter(d->!d.getStatus().equals("CANCELLED") && d.getCheckInDate().isAfter(LocalDate.now())|| d.getBookedDate().isEqual(LocalDate.now())).toList();
		    	return filteredUpcomingList;
		    }
		    else {
		    	List<BookingDetailsDto> filteredCancelledList=bookingDetails.stream().filter(d->d.getStatus().equals("CANCELLED")).toList();
		    	return filteredCancelledList;
		    }
		}
		 return null;
	}
}
