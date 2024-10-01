package com.group_3.cozyHaven.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>{

	@Query("select DATEDIFF(br.checkOutDate,br.checkInDate) from Booking br where br.id=?1")
	Integer findDateDiff(int bookingId);

	 @Query("select br from Booking br where br.room.id=?1 and br.status='CONFIRMED'and (br.checkInDate<?3 and br.checkOutDate>?2)")
	 List<Booking> findOverlappingBookings(int roomId, LocalDate checkInDate, LocalDate checkOutDate);
 
     @Query("select b from Booking b where b.id=?2 AND b.customer.id =?1")
	 Optional<Booking> findByBookedDate(int customerId,int bookingId);

	 @Query("select br.bookedDate,br.checkInDate,br.checkOutDate,br.numberOfRooms,br.numGuests,br.totalAmount,br.status,h.hotelName,h.location from Booking br join br.room hrt join hrt.hotel h where br.customer.id=?1")
	List<Object[]> findAllBooking(int customerId);

	@Query("select b from Booking b where b.status='CONFIRMED' and b.checkOutDate<=?1")
	List<Booking> findByCheckOutDateLessThanEqual(LocalDate date);

	 @Query("select br.id, br.bookedDate,br.checkInDate,br.checkOutDate,br.numberOfRooms,br.numGuests,br.totalAmount,br.status,h.id,h.hotelName,h.location from Booking br join br.room hrt join hrt.hotel h where br.customer.id=?1")
	List<Object[]> getBookedDate(int customerId);
	

// select br.booked_date,br.check_in_date,br.check_out_date,br.number_of_rooms,br.num_guests,br.total_amount,br.status,h.hotel_name,h.location from booking_room br join hotel_room_type hrt on br.room_id=hrt.id join hotel h on hrt.hotel_
//	 id=h.id where br.customer_id=1;
	 
// select DATEDIFF(br.check_out_date,br.check_in_date) from booking_room br where br.id=:bookingId  
// select br from Booking br where b.room.id=?1 and b.checkInDate<=?3 and b.checkOutDate>?2
// select br from Booking br join br.customer c join c.user u where u.username=?1
}
