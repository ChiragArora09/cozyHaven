package com.group_3.cozyHaven.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.FlightPayment;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.FlightSeat;
import com.group_3.cozyHaven.model.FlightSeatBooking;
import com.group_3.cozyHaven.repository.FlightBookingRepository;
import com.group_3.cozyHaven.repository.FlightSeatBookingRepository;

@Service
public class FlightSeatBookingService {

	@Autowired
	private FlightBookingService flightBookingService;
	
	@Autowired
	private FlightSeatService flightSeatService;
	
	@Autowired
	private FlightSeatBookingRepository flightSeatBookingRepository;
	
	@Autowired
	private FlightBookingRepository flightBookingRepository;


	public List<FlightSeatBooking> confirmSeatBooking(int bid, List<Integer> flightSeats) throws InvalidIdException, InputValidationException {
		// list to store all the saved seat-bookings from the array
		List<FlightSeatBooking> flightSeatBookings = new ArrayList<>();
		
		FlightBooking flightBooking = flightBookingService.getById(bid); // getting booking object
		
		// traverse each seat
		for(int seatId : flightSeats) {
			FlightSeatBooking flightSeatBooking = new FlightSeatBooking(); // new object	
			
			FlightSeat flightSeat = flightSeatService.getById(seatId); // getting seat object
			
			flightSeatBooking.setFlightBooking(flightBooking); // set booking in BusSeatBooking model
			flightSeatBooking.setFlightSeat(flightSeat); // set seat in BusSeatBooking model
			
			flightSeatBookings.add(flightSeatBooking);
			
			flightSeatBookingRepository.save(flightSeatBooking);
		}
		flightBooking.setStatus("Confirmed");
		flightBookingRepository.save(flightBooking);
		
		return flightSeatBookings;
	}


	public List<FlightPayment> calculateTotalAmount(int bid) throws InputValidationException {
		List<Object[]> list = flightSeatBookingRepository.getPaymentInfo(bid);
		List<FlightPayment> paymentList = new ArrayList<>();
		
		double totalBookingAmount = 0;
		
		for(Object[] obj : list) {
			double amount = (double) obj[0];
			String seatType = obj[1].toString();
			String classType = obj[2].toString();
			double totalAmount = amount;
			
			// CHECKING THE CLASS TYPE FOR ADDITIONAL COSTS
			if(classType.equals("PREMIUM_ECONOMY")) {
				totalAmount = amount*1.2;
			} else if(classType.equals("BUSINESS")){
				totalAmount = amount*2.0;
			} else if(classType.equals("FIRST_CLASS")){
				totalAmount = amount*3.0;
			}
			
			// CHECKING THE SEAT TYPE FOR ADDITIONAL COSTS
			if(seatType.equals("Aisle_Seat")) {
				totalAmount = totalAmount*1.1;
			} else if(seatType.equals("Window_Seat")){
				totalAmount = totalAmount*1.05;
			}
		
			FlightPayment payment = new FlightPayment(amount, seatType, classType, totalAmount);
			totalBookingAmount+=totalAmount;
			paymentList.add(payment);
		}
		FlightBooking currentBooking = flightBookingService.getById(bid);
		currentBooking.setAmount(totalBookingAmount);
		flightBookingRepository.save(currentBooking);
		
		return paymentList;
	}


}
