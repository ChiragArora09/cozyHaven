package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.FlightSeatBooking;

public interface FlightSeatBookingRepository extends JpaRepository<FlightSeatBooking, Integer>{

//	@Query("SELECT fsb FROM FlightSeatBooking fsb WHERE fsb.flightBooking=?1")
//	List<Object[]> getBookingSeatsForAParticulaBooking(FlightBooking flightBooking);
	
	@Query(value = "select flight_seat_id from flight_seat_booking WHERE flight_booking_id = ?1", nativeQuery = true)
	List<Object[]> getBookingSeatsForAParticulaBooking(int bid);

	@Query(value = "select fs.seat_number, fc.type, fr.amount from flight_seat fs JOIN flight_class fc ON fs.flight_class_id=fc.id JOIN flight f ON f.id=fc.flight_id JOIN flight_route fr ON fr.flight_id=f.id WHERE fs.id=?1", nativeQuery = true)
	List<Object[]> getSeatAmountInfo(int seatId);

}
