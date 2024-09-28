package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer> {

	@Query("SELECT bs.arrival, bs.departure, bs.distance, b.name, b.number, b.type, b.description, s.stopName, b.id, bs.stopNumber FROM BusStop bs JOIN bs.bus b JOIN bs.stop s WHERE b.id=?1 AND (s.stopName=?2 OR s.stopName=?3) ORDER BY bs.stopNumber")
	List<Object[]> getBusBetweenStops(int id, String source, String destination);

	@Query("SELECT b.id FROM BusStop bs JOIN bs.bus b JOIN bs.stop s WHERE s.stopName=?1 AND b.id IN (SELECT b.id FROM BusStop bs JOIN bs.bus b JOIN bs.stop s WHERE s.stopName=?2)")
	List<Object[]> getBusIdsForSourceAndDestination(String source, String destination);

	@Query("SELECT b.name, b.number, b.type, bp.name, bp.age, bs.seatNumber, bs.seatType, bb.date, bb.source, bb.destination, bb.status FROM BusSeatBooking bsb JOIN bsb.busBooking bb JOIN bsb.busSeat bs JOIN bs.bus b JOIN BusPassenger bp ON bp.busBooking.id=bb.id WHERE bb.id=?1")
	List<Object[]> getBookingTicket(int bid);
	
} 