package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.enums.ClassType;
import com.group_3.cozyHaven.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query("SELECT f.name, f.number, r.source, r.destination,fr.sourceDeparture, fr.destinationArrival, fr.amount from FlightRoute fr JOIN fr.route r JOIN fr.flight f WHERE r.source = ?1 AND r.destination = ?2")
	List<Object[]> getFlightBetweenStops(String source, String destination, ClassType classType);

//	@Query(value = "select fb.date, fb.source, fb.destination, fb.status, fs.seat_number, fc.type, f.name, ft.name, ft.age from flight_booking fb JOIN flight_seat_booking fsb ON fsb.flight_booking_id=fb.id JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fc.id=fs.flight_class_id JOIN flight f ON f.id=fc.flight_id JOIN flight_route fr ON fr.flight_id=f.id JOIN flight_traveller ft ON ft.flight_booking_id=fb.id WHERE fb.id=?1 GROUP BY ft.id;", nativeQuery = true)
	@Query("select fb.date, fb.source, fb.destination, fb.status, fs.seatNumber, fc.type, f.name, ft.name, ft.age FROM FlightSeatBooking fsb JOIN fsb.flightBooking fb JOIN fsb.flightSeat fs JOIN fs.flightClass fc JOIN fc.flight f JOIN FlightTraveller ft ON ft.flightBooking.id=fb.id JOIN FlightRoute fr ON fr.flight.id=f.id JOIN fr.route r WHERE fb.id=?1 GROUP BY ft.id")
	List<Object[]> getBookingTicket(int bid);
	
}
