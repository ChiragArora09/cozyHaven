package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.enums.ClassType;
import com.group_3.cozyHaven.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query("SELECT f.name, f.number, r.source, r.destination,fr.sourceDeparture, fr.destinationArrival, fr.amount from FlightRoute fr JOIN fr.route r JOIN fr.flight f WHERE r.source = ?1 AND r.destination = ?2")
	List<Object[]> getFlightBetweenStops(String source, String destination, ClassType classType);
	
}
