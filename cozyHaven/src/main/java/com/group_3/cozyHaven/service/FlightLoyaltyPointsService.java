package com.group_3.cozyHaven.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.AddFlightLoyaltyPointsDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.FlightLoyaltyPoints;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.FlightBookingRepository;
import com.group_3.cozyHaven.repository.FlightLoyaltyPointsRepository;


@Service
public class FlightLoyaltyPointsService {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private FlightBookingRepository flightBookingRepository;
	
	@Autowired
	private ServiceProviderService serviceProviderService;
	
	@Autowired
	private FlightLoyaltyPointsRepository flightLoyaltyPointsRepository;

	public Object updateLoyaltyPoints(AddFlightLoyaltyPointsDto dto, int customerId) throws InputValidationException {
		List<Object[]> list = flightBookingRepository.getProviderIdFromBookingId(dto.getBookingId());
		Object[] id = list.get(0);
		int serviceProviderId = (int) id[0];
		
		Customer customer = customerService.getById(customerId); // customer for inserting into FlightLoyaltyPoints
		ServiceProvider serviceProvider = serviceProviderService.getById(serviceProviderId); // service provider for inserting into FlightLoyaltyPoints
		int earned = dto.getLoyaltyPointsEarned();
		int used = dto.getLoyaltyPointsUsed();
		int total = earned-used;
		LocalDate transactionDate = LocalDate.parse(dto.getDateOfTransaction());
		
		FlightLoyaltyPoints flightLoyaltyPoints = new FlightLoyaltyPoints(customer, serviceProvider, earned, used, total, transactionDate);
		
		return flightLoyaltyPointsRepository.save(flightLoyaltyPoints);	
	}

}