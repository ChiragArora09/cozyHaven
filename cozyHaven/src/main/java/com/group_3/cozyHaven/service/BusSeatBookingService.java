package com.group_3.cozyHaven.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.BusBooking;
import com.group_3.cozyHaven.model.BusSeat;
import com.group_3.cozyHaven.model.BusSeatBooking;
import com.group_3.cozyHaven.repository.BusBookingRepository;
import com.group_3.cozyHaven.repository.BusSeatBookingRepository;

@Service
public class BusSeatBookingService {
	
	@Autowired
	private BusBookingService busBookingService;
	
	@Autowired
	private BusSeatService busSeatService;
	
	@Autowired
	private BusSeatBookingRepository busSeatBookingRepository;
	
	@Autowired
	private BusBookingRepository busBookingRepository;

	public List<BusSeatBooking> confirmSeatBooking(int bid, List<Integer> busSeats) throws InputValidationException, InvalidIdException {
		// list to store all the saved seat-bookings from the array
		List<BusSeatBooking> busSeatBookings = new ArrayList<>();
		
		BusBooking busBooking = busBookingService.getById(bid); // getting booking object
		
		// traverse each seat
		for(int seatId : busSeats) {
			BusSeatBooking busSeatBooking = new BusSeatBooking(); // new object	
			
			BusSeat busSeat = busSeatService.getById(seatId); // getting seat object
			
			busSeatBooking.setBusBooking(busBooking); // set booking in BusSeatBooking model
			busSeatBooking.setBusSeat(busSeat); // set seat in BusSeatBooking model
			
			busSeatBookings.add(busSeatBooking);
			
			busSeatBookingRepository.save(busSeatBooking);
		}
		busBooking.setStatus("Confirmed");
		busBookingRepository.save(busBooking);
		
		return busSeatBookings;
	}
	
}
