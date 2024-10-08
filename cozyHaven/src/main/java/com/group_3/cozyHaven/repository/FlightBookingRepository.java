package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.FlightBooking;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Integer> {
// select fb.date, fb.source, fb.destination, fc.type, f.name, f.number, fb.amount from flight_booking fb JOIN flight_seat_booking fsb ON fsb.flight_booking_id=fb.id JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fc.id=fs.flight_class_id JOIN flight f ON f.id=fc.flight_id WHERE fb.customer_id=1;
	@Query("SELECT distinct(fb.id), fb.date, fb.source, fb.destination, f.name, f.number, fb.amount, fb.status FROM FlightSeatBooking fsb JOIN fsb.flightBooking fb JOIN flightSeat fs JOIN fs.flightClass fc JOIN fc.flight f WHERE fb.customer.id=?1")
	List<Object[]> getBookings(int customerId);

	@Query("select sp.id FROM FlightSeatBooking fsb JOIN fsb.flightBooking fb JOIN fsb.flightSeat fs JOIN fs.flightClass fc JOIN fc.flight f JOIN f.serviceProvider sp WHERE fb.id=?1")
	List<Object[]> getProviderIdFromBookingId(int bid);

}
