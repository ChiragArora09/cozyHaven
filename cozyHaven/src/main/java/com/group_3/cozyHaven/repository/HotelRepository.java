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

	@Query(value = "SELECT hr.id AS room_id, hr.bed_type, " +
            "       hr.room_type, " +
            "       hr.price, " +
            "       (hr.total_rooms - COALESCE(SUM(br.number_of_rooms), 0)) AS available_rooms, " +
            "       h.hotel_name, " +
            "       h.location " +
            "FROM hotel_room_type hr " +
            "JOIN hotel h ON hr.hotel_id = h.id " +
            "LEFT JOIN booking_room br ON hr.id = br.room_id " +
            "       AND br.status = 'CONFIRMED' " +
            "       AND NOT (br.check_in_date >= :checkOutDate OR br.check_out_date <= :checkInDate) " + // Check for overlapping bookings
            "WHERE h.location = :location " +
            "GROUP BY hr.id, hr.bed_type, hr.room_type, hr.price, h.hotel_name, h.location " +
            "HAVING available_rooms >= :numberGuests",
    nativeQuery = true)
	List<Object[]> findAvailableHotels(String location, LocalDate checkInDate, LocalDate checkOutDate,int numberGuests);
}
