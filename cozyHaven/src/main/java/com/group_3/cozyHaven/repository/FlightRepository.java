package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.enums.ClassType;
import com.group_3.cozyHaven.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query("SELECT DISTINCT(f.id) FROM FlightCity fc JOIN fc.flight f JOIN fc.city c JOIN FlightClass fcc ON fcc.flight.id=f.id WHERE f.status='Running' AND fcc.type=?3 AND c.cityName=?1 AND f.id IN (SELECT f.id FROM FlightCity fc JOIN fc.flight f JOIN fc.city c WHERE c.cityName=?2)")
	List<Object[]> getFlightIdsForSourceAndDestination(String source, String destination, ClassType classType);

	@Query("SELECT fc.arrival, fc.departure, fc.distance, f.name, f.number, f.description, c.cityName, f.id, fc.stopNumber FROM FlightCity fc JOIN fc.flight f JOIN fc.city c WHERE f.id=?1 AND (c.cityName=?2 OR c.cityName=?3) ORDER BY fc.stopNumber")
	List<Object[]> getFlightBetweenStops(Integer integer, String source, String destination);

//	@Query(value = "select f.name, f.number, ft.name, ft.age, fs.seat_number, fs.flight_seat_type, fc.type, fb.source, fb.destination, fb.date, fb.status from flight_seat_booking fsb JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fc.id=fs.flight_class_id JOIN flight f ON f.id=fc.flight_id JOIN flight_booking fb ON fb.id=fsb.flight_booking_id JOIN flight_traveller ft ON ft.flight_booking_id=fb.id WHERE fb.id=?1 GROUP BY ft.id;", nativeQuery = true)
	@Query("SELECT f.name, f.number, ft.name, ft.age, fs.seatNumber, fs.flightSeatType, fc.type, fb.source, fb.destination, fb.date, fb.status FROM FlightSeatBooking fsb JOIN fsb.flightSeat fs JOIN fsb.flightBooking fb JOIN fs.flightClass fc JOIN fc.flight f JOIN FlightTraveller ft ON ft.flightBooking.id=fb.id WHERE fb.id=?1")
	List<Object[]> getBookingTicket(int bid);

	@Query(value = "select distinct(fo.id), fo.description, fo.offer_condition, fo.offer_discount, fo.offer_type, fo.loyalty_points from flight_booking fb JOIN flight_seat_booking fsb ON fsb.flight_booking_id=fb.id JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fc.id=fs.flight_class_id JOIN flight f ON f.id=fc.flight_id JOIN flight_offer fo ON fo.flight_id=f.id WHERE fb.id=?1 AND fo.active='YES'", nativeQuery = true)
	List<Object[]> getOffers(int bid);

	@Query("SELECT f FROM Flight f WHERE f.serviceProvider.id=?1")
	List<Object[]> getFlights(int serviceProviderId);
}
