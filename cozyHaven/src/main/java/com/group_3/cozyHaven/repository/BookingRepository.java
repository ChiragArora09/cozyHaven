package com.group_3.cozyHaven.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>{

	List<Booking> findByRoomId(int roomId);
	
	@Query(value="select DATEDIFF(br.check_out_date,br.check_in_date) from booking_room br where br.id=:bookingId",nativeQuery=true)
	Integer findDateDiff(int bookingId);

	List<Booking> findByCheckOutDate(LocalDate date);

	 @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId " +
	           "AND (b.checkInDate <= :checkOutDate AND b.checkOutDate > :checkInDate)")
	   List<Booking> findOverlappingBookings(int roomId, LocalDate checkInDate, LocalDate checkOutDate);

}
