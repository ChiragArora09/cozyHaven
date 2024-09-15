package com.group_3.cozyHaven.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.FlightSeat;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Integer>{

	@Query(value = "select fs.seat_number from flight_seat fs JOIN flight_class fc ON fs.flight_class_id=fc.id JOIN flight f ON f.id=fc.flight_id WHERE f.id=?1 AND fs.id NOT IN (select fs.id from flight_seat_booking fsb JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_booking fb ON fb.id=fsb.flight_booking_id WHERE fb.date=?2)", nativeQuery = true)
	List<Object[]> getAvailableSeats(int fid, LocalDate date);

}
