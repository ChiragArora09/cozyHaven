package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.FlightClass;

public interface FlightClassRepository extends JpaRepository<FlightClass, Integer> {

	@Query("SELECT fc.id as class_id, fs.id as seat_id, fc.type, fs.flightSeatType, fs.seatNumber from FlightSeat fs JOIN fs.flightClass fc JOIN fc.flight f WHERE f.id=?1")
	List<Object[]> getClassesAndSeats(int flightId);

}
