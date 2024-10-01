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
	
	@Query("select h from Hotel h where h.location=?1")
	List<Hotel> findByLocation(String location);

	@Query("select hr.id,hr.roomType,hr.price,h.hotelName,h.location,r.star,r.rating,h.id from Room hr join hr.hotel h left join Review r on r.hotel.id=h.id left join Booking b on b.room.id=hr.id and b.checkInDate<?3 and b.checkOutDate>?2 where h.location=?1 and (hr.totalRooms-hr.bookedRooms)>=?5 and hr.capacity>=?4 ")
	List<Object[]> findAvailableHotels(String location,LocalDate checkInDate,LocalDate checkOutDate,int numGuests,int numRooms);

	@Query("select h from Hotel h join h.serviceProvider s join s.user u where u.username=?1 ")
	List<Hotel> getAllHotel(String username);

}


// select h.id,h.location,h.hotel_name,hr.room_type,hr.id,hr.price from hotel h join hotel_room_type hr on hr.hotel_id=h.id left join booking_room br on br.room_id=hr.id
// where hr.booked_rooms < hr.total_rooms;

// Review r join r.hotel h join h.room hr