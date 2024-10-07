package com.group_3.cozyHaven.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.BookingTicket;
import com.group_3.cozyHaven.dto.FlightBetweenStopsDto;
import com.group_3.cozyHaven.dto.OfferDto;
import com.group_3.cozyHaven.enums.ClassType;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.FlightCity;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.FlightCityRepository;
import com.group_3.cozyHaven.repository.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	private ServiceProviderService serviceProviderService;
	
	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private FlightCityRepository flightCityRepository;
	
	public Flight addFlight(int serviceProviderId, Flight flight) throws InputValidationException {
		ServiceProvider serviceProvider = serviceProviderService.getById(serviceProviderId); // finding service provider by serviceProviderId
		flight.setServiceProvider(serviceProvider);
		flight.setStatus("Running");
		return flightRepository.save(flight);
	}

	public Flight findById(int fid) throws InvalidIdException {
		Optional<Flight> optional = flightRepository.findById(fid);
		if(optional.isEmpty())
			throw new InvalidIdException("Flight Id invalid");
		
		return optional.get();
	}

	public List<FlightBetweenStopsDto> getFlightsBetweenStops(String source, String destination, ClassType classType) {
		// grabbing all the ids of flights between desired source and destination
		List<Object[]> list = flightRepository.getFlightIdsForSourceAndDestination(source, destination, classType);
		List<Integer> flightIds = new ArrayList<>();
		for(Object[] ids: list) {
			int id = (int) ids[0];
			System.out.println(id);
			flightIds.add(id);
		}
		
		List<FlightBetweenStopsDto> flightBetweenStopsDtoList = new ArrayList<>();
		
		for(int i=0;i<flightIds.size();i++) {
			List<Object[]> flightInfo = flightRepository.getFlightBetweenStops(flightIds.get(i), source, destination);
			
			Object[] sourceInfo = flightInfo.get(0); // Fetched 1st row is for source
			Object[] destinationInfo = flightInfo.get(1); // Fetched 2nd row is for destination
	/*		
			+-----------------+-----------------+----------+--------+-------------+--------------------------------------+-----------+----+-------------+
			| arrival         | departure        | distance | name   | number      | description                          | city_name | id | stop_number |
			+-----------------+-----------------+----------+--------+-------------+--------------------------------------+-----------+----+-------------+
			| 08:00:00.000000 | 10:00:00.000000 |        00 | Indigo | Indigo 2100 | The fastest and the most comfortable | Delhi     |  1 |           0 |
			| 13:30:00.000000 | 14:30:00.000000 |      1500 | Indigo | Indigo 2100 | The fastest and the most comfortable | Pune      |  1 |           2 |
			+-----------------+-----------------+----------+--------+-------------+--------------------------------------+-----------+----+-------------+
	*/		
			String flightName = sourceInfo[3].toString();
			String flightNumber = sourceInfo[4].toString();
			String flightDescription = sourceInfo[5].toString();
			String source1 = sourceInfo[6].toString();
			String destination1 = destinationInfo[6].toString();
			LocalTime sourceArrival = (LocalTime) sourceInfo[0];
			LocalTime destinationArrival = (LocalTime) destinationInfo[0];
			int distance = (int) destinationInfo[2] - (int) sourceInfo[2];
			int flightId = (int) sourceInfo[7];
			int sourceStopNumber = (int) sourceInfo[8];
			int	destinationStopNumber = (int) destinationInfo[8];
			
			double fixedFare = 100;
			double pricePerKm = 5;
			double amount = distance * pricePerKm; // 7500
			amount+=fixedFare; // 7600
			
			FlightBetweenStopsDto flightBetweenStopsDto = new FlightBetweenStopsDto(flightName, flightNumber, flightDescription, source1, destination1, sourceArrival, destinationArrival, distance, amount, flightId, sourceStopNumber, destinationStopNumber);
//			(String flightName, String flightNumber, String flightDescription, String source, String destination, LocalTime sourceArrival, LocalTime destinationArrival, int distance, double amount, int flightId, int sourceStopNo, int destinationStopNo)
			flightBetweenStopsDtoList.add(flightBetweenStopsDto);
		}
        List<FlightBetweenStopsDto> filteredFlightsBySource = flightBetweenStopsDtoList.stream()
                .filter(flight -> flight.getSource().equals(source))
                .collect(Collectors.toList());
		
		return filteredFlightsBySource;
	}
	// select fc.arrival, fc.departure, fc.distance, f.name, f.number, f.description, c.city_name, f.id, fc.stop_number from flight_city fc JOIN flight f ON fc.flight_id=f.id JOIN city c ON c.id=fc.city_id WHERE f.id=1 AND (c.city_name="Delhi" OR c.city_name="Pune") order by fc.stop_number;

	public List<BookingTicket> getBookingTicket(int bid) {
	
		List<Object[]> list = flightRepository.getBookingTicket(bid);
		List<BookingTicket> listDto = new ArrayList<>();

		for(Object[] obj : list) {
			String flightName = obj[0].toString();
			String flightNumber = obj[1].toString();
			String travellerName = obj[2].toString();
			int travellerAge = (int) obj[3];
			String seatNumber = obj[4].toString();
			String seatType = obj[5].toString();
			String classType = obj[6].toString();
			String source = obj[7].toString();
			String destination = obj[8].toString();
			String date = obj[9].toString();
			String status = obj[10].toString();
			
			BookingTicket bookingTicket = new BookingTicket(date, source, destination, status, seatNumber, classType, flightName, travellerName, travellerAge, flightNumber, seatType);
			listDto.add(bookingTicket);
		}	
		return listDto; 

	}

	public List<?> getBookingOffers(int bid) {
		List<Object[]> offers = flightRepository.getOffers(bid);
		List<OfferDto> offerList = new ArrayList<>();
		
		for(Object[] obj : offers) {
			int id = (int) obj[0];
			String description = obj[1].toString();
			double offerCondition = (double) obj[2];
			int offerDiscount = (int) obj[3];
			String offerType = obj[4].toString();
			int loyaltyPoints = (int) obj[5];
			
			OfferDto offerDto = new OfferDto(id, description, offerType, offerDiscount, offerCondition, loyaltyPoints);
			offerList.add(offerDto);
		}
		return offerList;
	}

	public List<Flight> getFlights(int serviceProviderId) throws InputValidationException {
		List<Flight> flightList = flightRepository.findAll();
		
//		ServiceProvider serviceProvider = serviceProviderService.getById(serviceProviderId);
		
		List<Flight> filteredFlights = flightList.stream()
                .filter(flight -> flight.getServiceProvider().getId() == serviceProviderId)
                .toList();
		
		return filteredFlights;
	}

	public List<FlightCity> getCities() {
		return flightCityRepository.findAll();
	}
}
