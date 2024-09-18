package com.group_3.cozyHaven.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.FlightSeat;
import com.group_3.cozyHaven.model.FlightSeatBooking;
import com.group_3.cozyHaven.model.Payment;
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
	
	
	public FlightSeatBooking confirmBooking(int bid, int flightSeat) throws InputValidationException, InvalidIdException {
		FlightBooking fBooking = flightBookingService.getById(bid);
		FlightSeat fSeat = flightSeatService.getById(flightSeat);
		
		FlightSeatBooking flightSeatBooking = new FlightSeatBooking();		
		flightSeatBooking.setFlightBooking(fBooking);
		flightSeatBooking.setFlightSeat(fSeat);
		
		flightSeatBookingRepository.save(flightSeatBooking);

		FlightBooking booking = flightBookingService.getById(bid);
		booking.setStatus("Confirm");
		flightBookingRepository.save(booking);
		
		return flightSeatBooking;
	}

	public List<Payment> calculateTotalAmount(int bid) throws InputValidationException {
		// getting all the seats booked for a particular booking
		List<Object[]> list = flightSeatBookingRepository.getBookingSeatsForAParticulaBooking(bid);
		List<Integer> seatIds = new ArrayList<>();
		for(Object[] obj : list) {
			int seat = (int) obj[0];
			System.out.println(seat);
			seatIds.add(seat);
		}
		
		List<Payment> paymentInfo = new ArrayList<>();
		
		for (int i=0;i<seatIds.size();i++) {
			int seatId = seatIds.get(i);

			List<Object[]> list3 = flightSeatBookingRepository.getSeatAmountInfo(seatId);
			for(Object[] obj : list3) {
				String seatNumber = obj[0].toString();
				String classType = obj[1].toString();
				double amount = (double) obj[2];
				
				double totalAmount = amount;
				
				if(classType == "PREMIUM_ECONOMY") {
					totalAmount=totalAmount + (totalAmount*1.2);
				}else if(classType == "BUSINESS") {
					totalAmount=totalAmount + (totalAmount*1.5);
				}else if(classType == "FIRST_CLASS") {
					totalAmount=totalAmount + (totalAmount*2.0);
				}
				
				Payment payment = new Payment(amount, seatNumber, classType, totalAmount);
				paymentInfo.add(payment);
				
			}
			
		}
		
		return paymentInfo;
		
	}

}
