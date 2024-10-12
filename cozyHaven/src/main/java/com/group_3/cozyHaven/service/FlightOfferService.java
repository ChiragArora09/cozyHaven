package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.enums.BooleanType;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.FlightOffer;
import com.group_3.cozyHaven.repository.FlightOfferRepository;

@Service
public class FlightOfferService {
	
	@Autowired
	private FlightOfferRepository flightOfferRepository;

	@Autowired
	private FlightService flightService;
	
	public FlightOffer createOffer(int flightId, FlightOffer flightOffer) throws InvalidIdException {
		Flight flight = flightService.findById(flightId);
		flightOffer.setFlight(flight);
		flightOffer.setActive(BooleanType.YES);
		flightOfferRepository.save(flightOffer);
		return flightOffer;
	}

	public FlightOffer getOfferDetails(int offerId) {
		FlightOffer flightOffer = flightOfferRepository.findById(offerId).get();
		return flightOffer;
	}

	public FlightOffer editOffer(int offerId, FlightOffer flightOffer) {
		FlightOffer fo = flightOfferRepository.findById(offerId).get(); 
		
		fo.setDescription(flightOffer.getDescription());
		fo.setOfferCondition(flightOffer.getOfferCondition());
		fo.setOfferDiscount(flightOffer.getOfferDiscount());
		fo.setOfferType(flightOffer.getOfferType());
		fo.setLoyaltyPoints(flightOffer.getLoyaltyPoints());
		
		return flightOfferRepository.save(fo);
		
	}

	public FlightOffer changeStatus(int offerId) {
		FlightOffer flightOffer = flightOfferRepository.findById(offerId).get();
		
		BooleanType currentStatus = flightOffer.getActive();
		
		if(currentStatus.equals(BooleanType.YES)) {
			flightOffer.setActive(BooleanType.NO);
		}else {
			flightOffer.setActive(BooleanType.YES);
		}
		
		return flightOfferRepository.save(flightOffer);
	}
	
	
}
