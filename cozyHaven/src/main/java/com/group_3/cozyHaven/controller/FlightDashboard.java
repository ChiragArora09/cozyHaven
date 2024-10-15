package com.group_3.cozyHaven.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.FlightRevenueDto;
import com.group_3.cozyHaven.dto.PopularFlightsDto;
import com.group_3.cozyHaven.service.FlightBookingService;
import com.group_3.cozyHaven.service.FlightService;
import com.group_3.cozyHaven.utility.GetId;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FlightDashboard {
	
	@Autowired
	private FlightBookingService flightBookingService;
	
	@Autowired
	private GetId getId;
	
	@GetMapping("/flight-popularity")
	public List<PopularFlightsDto> getPopularFlights(Principal principal){
		String serviceProviderUsername = principal.getName();
		int serviceProviderId = getId.getIdByUsername(serviceProviderUsername);
		return flightBookingService.getPopularFlights(serviceProviderId);
	}
	
	@GetMapping("/flightBookingsByDate")
	public List<PopularFlightsDto> getFlightBookingsByDate(Principal principal, @RequestParam String date){
		String serviceProviderUsername = principal.getName();
		int serviceProviderId = getId.getIdByUsername(serviceProviderUsername);
		return flightBookingService.getFlightBookingsByDate(serviceProviderId, date);
	}
	
	@GetMapping("/myTotalBookings")
	public long totalBookings(Principal principal) {
		String serviceProviderUsername = principal.getName();
		int serviceProviderId = getId.getIdByUsername(serviceProviderUsername);
		return flightBookingService.getTotalBookings(serviceProviderId);
	}
	
	@GetMapping("/myTotalCustomers")
	public long uniqueCustomers(Principal principal) {
		String serviceProviderUsername = principal.getName();
		int serviceProviderId = getId.getIdByUsername(serviceProviderUsername);
		return flightBookingService.getUniqueCustomers(serviceProviderId);
	}
	
	@GetMapping("/flightRevenue")
	public List<FlightRevenueDto> getFlightRevenue(Principal principal) {
		String serviceProviderUsername = principal.getName();
		int serviceProviderId = getId.getIdByUsername(serviceProviderUsername);
		return flightBookingService.getFlightRevenue(serviceProviderId);
	}
	
	@GetMapping("/flightRevenue/{flightId}")
	public List<FlightRevenueDto> getFlightRevenueByDate(Principal principal, @PathVariable int flightId) {
		String serviceProviderUsername = principal.getName();
		int serviceProviderId = getId.getIdByUsername(serviceProviderUsername);
		return flightBookingService.getFlightRevenueByDate(serviceProviderId, flightId);
	}
	
	@GetMapping("/popularRoutes")
	public List<PopularFlightsDto> getPopularRoutes(Principal principal){
		String serviceProviderUsername = principal.getName();
		int serviceProviderId = getId.getIdByUsername(serviceProviderUsername);
		return flightBookingService.getPopularRoutes(serviceProviderId);
	}
}
