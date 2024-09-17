package com.group_3.cozyHaven.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.BusSeat;

public interface BusSeatRepository extends JpaRepository<BusSeat, Integer>{
	 	
	@Query(value = "select bs.id, bs.seat_number, bs.seat_type from bus_seat bs JOIN bus b ON b.id=bs.bus_id WHERE b.id=?1 AND bs.id NOT IN (select bsb.bus_seat_id from bus_seat_booking bsb JOIN bus_booking bb ON bb.id=bsb.bus_booking_id JOIN bus_seat bs ON bsb.bus_seat_id=bs.id JOIN bus b ON b.id=bs.bus_id AND bb.date=?4 AND ((?2 BETWEEN bb.source_stop_number AND bb.destination_stop_number-1) OR (?3 BETWEEN bb.source_stop_number+1 AND bb.destination_stop_number) OR (?2<source_stop_number AND ?3>destination_stop_number)));", nativeQuery = true)
	List<Object[]> getAvailableSeats(int busId, int sourceStopNumber, int destinationStopNumber, LocalDate bookingDate);

}
