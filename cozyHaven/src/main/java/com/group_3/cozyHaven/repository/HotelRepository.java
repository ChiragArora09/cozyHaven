package com.group_3.cozyHaven.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>{
	
	@Query("SELECT h from Hotel h where h.location=?1")
	List<Hotel> findByLocation(String location);

	@Query("select hr.id,hr.roomType,hr.price,h.hotelName,h.location,h.id from Room hr join hr.hotel h where h.location=?1 and hr.bookedRooms < hr.totalRooms")
	List<Object[]> findAvailableHotels(String location);

}


// select h.id,h.location,h.hotel_name,hr.room_type,hr.id,hr.price from hotel h join hotel_room_type hr on hr.hotel_id=h.id left join booking_room br on br.room_id=hr.id
// where hr.booked_rooms < hr.total_rooms;
