package com.group_3.cozyHaven.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.FlightSeat;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Integer>{

	@Query(value = "select fs.id, fs.seat_number, fs.flight_seat_type, fc.type from flight_seat fs JOIN flight_class fc ON fs.flight_class_id=fc.id JOIN flight f ON f.id=fc.flight_id WHERE f.id=?1 AND fs.id NOT IN (select fsb.flight_seat_id from flight_seat_booking fsb JOIN flight_booking fb ON fb.id=fsb.flight_booking_id JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fs.flight_class_id=fc.id JOIN flight f ON f.id=fc.flight_id AND fb.date=?4 AND ((?2 BETWEEN fb.source_city_number AND fb.destination_city_number-1) OR (?3 BETWEEN fb.source_city_number+1 AND fb.destination_city_number) OR (?2<fb.source_city_number AND ?3>fb.destination_city_number)));", nativeQuery = true)
	List<Object[]> getAvailableSeats(int flightId, int sourceCityNumber, int destinationCityNumber, LocalDate bookingDate);
}
