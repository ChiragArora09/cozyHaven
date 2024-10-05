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
		flightBookingRepository.save(flightBooking);
		
		return flightSeatBookings;
	}


	public List<FlightPayment> calculateTotalAmount(int bid) throws InputValidationException {
		List<Object[]> list = flightSeatBookingRepository.getPaymentInfo(bid);
		List<FlightPayment> paymentList = new ArrayList<>();
		
		double totalBookingAmount = 0;
		
		for(Object[] obj : list) {
			double amount = (double) obj[0]; // 2600
			String seatType = obj[1].toString();
			String classType = obj[2].toString();
			double totalAmount = amount; // 2600
			
			// CHECKING THE CLASS TYPE FOR ADDITIONAL COSTS
			if(classType.equals("ECONOMY")) {
				totalAmount = amount;
				System.out.println("In economy" + totalAmount);
			} else if(classType.equals("PREMIUM_ECONOMY")) {
				totalAmount = amount*1.2;
				System.out.println("In premium economy" +totalAmount);
			} else if(classType.equals("BUSINESS")){
				totalAmount = amount*2.0;
				System.out.println("In business" + totalAmount);
			} else if(classType.equals("FIRST_CLASS")){
				totalAmount = amount*3.0;
				System.out.println("In first class" + totalAmount);
			}
			
			// CHECKING THE SEAT TYPE FOR ADDITIONAL COSTS
			if(seatType.equals("Aisle_Seat")) {
				totalAmount = totalAmount*1.1; // 2860
				System.out.println("In Aisle_seat" + totalAmount);
			} else if(seatType.equals("Window_Seat")){
				totalAmount = totalAmount*1.05;
				System.out.println("In window seat" + totalAmount);
			}
		
			FlightPayment payment = new FlightPayment(amount, seatType, classType, totalAmount);
			System.out.println("totalBookingAmount 0" + totalBookingAmount);
			totalBookingAmount+=totalAmount; // 2860
			System.out.println("totalBookingAmount 1" + totalBookingAmount);
			paymentList.add(payment);
		}
		FlightBooking currentBooking = flightBookingService.getById(bid);
		currentBooking.setAmount(totalBookingAmount);
		flightBookingRepository.save(currentBooking);
		
		return paymentList;
	}


	public long getLoyaltyPoints(int bid, int customerId) {
		List<Object[]> list = flightBookingRepository.getProviderIdFromBookingId(bid);
		Object[] id = list.get(0);
		int serviceProviderId = (int) id[0];
		
		list = flightSeatBookingRepository.getLoyaltyPoints(serviceProviderId, customerId);
		Object[] points = list.get(0);
		long totalPoints = (long) points[0];
		
		return totalPoints;
	}


}
