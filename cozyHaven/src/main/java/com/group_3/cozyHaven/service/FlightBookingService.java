package com.group_3.cozyHaven.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.FlightRevenueDto;
import com.group_3.cozyHaven.dto.MakePaymentDto;
import com.group_3.cozyHaven.dto.PopularFlightsDto;
import com.group_3.cozyHaven.enums.BooleanType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Customer;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.FlightOffer;
import com.group_3.cozyHaven.repository.FlightBookingRepository;
import com.group_3.cozyHaven.repository.FlightOfferRepository;

@Service
public class FlightBookingService {
	
	@Autowired
	private FlightBookingRepository flightBookingRepository;
	
	@Autowired
	private FlightOfferRepository flightOfferRepository;

	@Autowired
	private CustomerService customerService;
	
	public FlightBooking addBooking(int custId, FlightBooking flightBooking) throws InputValidationException {
		Customer customer = customerService.getById(custId);
		flightBooking.setCustomer(customer);
		flightBooking.setStatus("Pending");
		flightBooking.setDate(LocalDate.now());
		return flightBookingRepository.save(flightBooking);
	}
	
	public FlightBooking getById(int bid) throws InputValidationException {
		Optional<FlightBooking> option = flightBookingRepository.findById(bid);
		if(option.isEmpty()) {
			throw new InputValidationException("Invalid ID");
		}
		return option.get();
	}

	public void makePayment(int bid, MakePaymentDto dto) {
		FlightBooking flightBooking = flightBookingRepository.findById(bid).get();
		flightBooking.setDiscount(dto.getDiscount());
		flightBooking.setAmount(dto.getAmount());
		flightBooking.setStatus("Confirmed");
		flightBooking.setDate(dto.getDateOfJourney());
		flightBookingRepository.save(flightBooking);
	}

	public List<FlightOffer> getAllOffers(int flightId) {
		List<FlightOffer> offers = flightOfferRepository.findAll();
		
		List<FlightOffer> filteredFlightOffers = offers.stream()
                .filter(offer -> offer.getFlight().getId() == flightId && offer.getActive().equals(BooleanType.YES))
                .toList();
		
		return filteredFlightOffers;
	}

	public List<?> getMyFlightOffers(int flightId) {
		List<FlightOffer> offers = flightOfferRepository.findAll();
		List<FlightOffer> filteredFlightOffers = offers.stream()
                .filter(offer -> offer.getFlight().getId() == flightId)
                .toList();
		return filteredFlightOffers;
	}

	// POPULAR FLIGHTS LIST
	public List<PopularFlightsDto> getPopularFlights(int serviceProviderId) {
		List<Object[]> list = flightBookingRepository.getPopularFlights(serviceProviderId);
		List<PopularFlightsDto> popularFlightsDtos = new ArrayList<>();
		for(Object[] obj: list) {
			long bookingCount = (long) obj[0];
			String flightInfo = obj[1].toString();
			PopularFlightsDto popularFlightsDto = new PopularFlightsDto(bookingCount, flightInfo);
			popularFlightsDtos.add(popularFlightsDto);
		}
		return popularFlightsDtos;
	}

	// NUMBER OF BOOKINGS FOR SERVICE PROVIDER
	public long getTotalBookings(int serviceProviderId) {
		List<Object[]> list = flightBookingRepository.getMyAllBookings(serviceProviderId);
		long totalBookings = (long) list.get(0)[0]; 
		return totalBookings;
	}

	public List<PopularFlightsDto> getFlightBookingsByDate(int serviceProviderId, String date) {
		List<Object[]> list = flightBookingRepository.getFlightBookigsByDate(serviceProviderId, date);
		List<PopularFlightsDto> popularFlightsDtos = new ArrayList<>();
		for(Object[] obj: list) {
			long bookingCount = (long) obj[0];
			String flightInfo = obj[1].toString();
			PopularFlightsDto popularFlightsDto = new PopularFlightsDto(bookingCount, flightInfo);
			popularFlightsDtos.add(popularFlightsDto);
		}
		return popularFlightsDtos;
	}

	public long getUniqueCustomers(int serviceProviderId) {
		List<Object[]> list = flightBookingRepository.getUniqueCustomers(serviceProviderId);
		long totalCustomers = (long) list.get(0)[0];
		return totalCustomers;
	}

	public List<FlightRevenueDto> getFlightRevenue(int serviceProviderId) {
		List<Object[]> list = flightBookingRepository.getFlightRevenue(serviceProviderId);
		List<FlightRevenueDto> flightRevenueDtos = new ArrayList<>();
		for(Object[] obj: list) {
			String flightInfo = obj[0].toString();
			double totalRevenue = (double) obj[1];
			FlightRevenueDto flightRevenueDto = new FlightRevenueDto(totalRevenue, flightInfo);
			flightRevenueDtos.add(flightRevenueDto);
		}
		return flightRevenueDtos;
		
	}

	public List<FlightRevenueDto> getFlightRevenueByDate(int serviceProviderId, int flightId) {
		List<Object[]> list = flightBookingRepository.getFlightRevenueByDates(serviceProviderId, flightId);
		List<FlightRevenueDto> flightRevenueDtos = new ArrayList<>();
		for(Object[] obj: list) {
			double totalRevenue = (double) obj[0];
			String flightDate = obj[1].toString();
			FlightRevenueDto flightRevenueDto = new FlightRevenueDto(totalRevenue, flightDate);
			flightRevenueDtos.add(flightRevenueDto);
		}
		return flightRevenueDtos;
	}

}
