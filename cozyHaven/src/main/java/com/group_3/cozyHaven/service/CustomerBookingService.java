package com.group_3.cozyHaven.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.VehicleBookingDetails;
import com.group_3.cozyHaven.repository.BusBookingRepository;
import com.group_3.cozyHaven.repository.FlightBookingRepository;

@Service
public class CustomerBookingService {
	
	@Autowired
	private BusBookingRepository busBookingRepository;
	
	@Autowired
	private FlightBookingRepository flightBookingRepository;	

	public List<?> getMyBookings(int customerId, String bookingType, String bookingPeriod) {
		System.out.println(bookingPeriod);
		System.out.println(bookingType);
		if(bookingType.equals("Flight")){
			System.out.println("In flight");
			List<Object[]> list = flightBookingRepository.getBookings(customerId);
			List<VehicleBookingDetails> flightBookingDetailsList = new ArrayList<>();
			for(Object[] obj : list) {
				int bookingId = (int) obj[0];
				LocalDate bdate = LocalDate.parse(obj[1].toString());
				String source = obj[2].toString();
				String destination = obj[3].toString();
				String type = obj[4].toString();
				String flightName = obj[5].toString();
				String flightNumber = obj[6].toString();
				double amount = (double) obj[7];
				String status = obj[8].toString();
				VehicleBookingDetails flightBookingDetails = new VehicleBookingDetails(bookingId, bdate, source, destination, type, flightName, flightNumber, amount, status);
				flightBookingDetailsList.add(flightBookingDetails);
			}
			
			if(bookingPeriod.equals("Past")) {
				List<VehicleBookingDetails> filteredPastFlightDetailList = flightBookingDetailsList.stream().filter(detail -> detail.getBooking().isBefore(LocalDate.now()) && !detail.getStatus().equals("Cancelled")).toList();
				return filteredPastFlightDetailList;
			}else if(bookingPeriod.equals("Upcoming")) {
				List<VehicleBookingDetails> filteredUpcomingFlightDetailList = flightBookingDetailsList.stream().filter(detail -> !detail.getStatus().equals("Cancelled") && detail.getBooking().isAfter(LocalDate.now()) || detail.getBooking().isEqual(LocalDate.now())).toList();
				return filteredUpcomingFlightDetailList;
			}else {
				List<VehicleBookingDetails> filteredCancelledFlightDetailList = flightBookingDetailsList.stream().filter(detail -> detail.getStatus().equals("Cancelled")).toList();
				return filteredCancelledFlightDetailList;
			}
			
		} else if(bookingType.equals("Bus")){
			List<Object[]> list = busBookingRepository.getBookings(customerId);
			List<VehicleBookingDetails> busBookingDetailsList = new ArrayList<>();
			for(Object[] obj : list) {
				int bookingId = (int) obj[0];
				LocalDate bdate = LocalDate.parse(obj[1].toString());
				String source = obj[2].toString();
				String destination = obj[3].toString();
				String type = obj[4].toString();
				String busName = obj[5].toString();
				String busNumber = obj[6].toString();
				double amount = (double) obj[7];
				String status = obj[8].toString();
				VehicleBookingDetails busBookingDetails = new VehicleBookingDetails(bookingId, bdate, source, destination, type, busName, busNumber, amount, status);
				busBookingDetailsList.add(busBookingDetails);
			}
			
			if(bookingPeriod.equals("Past")) {
				List<VehicleBookingDetails> filteredPastBusDetailList = busBookingDetailsList.stream().filter(detail -> detail.getBooking().isBefore(LocalDate.now()) && !detail.getStatus().equals("Cancelled") ).toList();
				return filteredPastBusDetailList;
			}else if(bookingPeriod.equals("Upcoming")) {
				List<VehicleBookingDetails> filteredUpcomingBusDetailList = busBookingDetailsList.stream().filter(detail -> !detail.getStatus().equals("Cancelled") && detail.getBooking().isAfter(LocalDate.now()) || detail.getBooking().isEqual(LocalDate.now())).toList();
				return filteredUpcomingBusDetailList;
			}else {
				List<VehicleBookingDetails> filteredCancelledBusDetailList = busBookingDetailsList.stream().filter(detail -> detail.getStatus().equals("Cancelled")).toList();
				return filteredCancelledBusDetailList;
			}


		} else if(bookingType.equals("Hotel")){
			System.out.println("IN Hotel");
			return null;
		} else {
			System.out.println("IN ELSE");
			return null;
		}
		
	}
	

}
