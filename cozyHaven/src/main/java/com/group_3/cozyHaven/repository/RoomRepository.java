package com.group_3.cozyHaven.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.enums.RoomType;
import com.group_3.cozyHaven.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {


    Optional<Room> findByRoomType(RoomType roomType);

    @Query("select r from Room r where r.hotel.id = ?1 and r.roomType = ?2 and (r.totalRooms - r.bookedRooms) > 0")
    List<Room> findByHotelRoomType(int hotelId, RoomType roomType);

     @Query(value="select h.hotel_name,hrt.bed_type,hrt.room_type,hrt.price,a.breafast_lunc,a.breakfast,a.free_wifi,a.gym,a.parking_area,a.spa,a.swimming_pool,r.rating,r.star,he.cancellation_info,he.complimentary,c.fullname from hotel h join hotel_room_type hrt on hrt.hotel_id=h.id join amenities a on a.room_id=hrt.id join hotel_extra he on he.room_id=hrt.id left join review r on r.hotel_id=h.id left join customer c on r.customer_id=c.id where hrt.id=?1",nativeQuery=true)
	 List<Object[]> findAvailableRoom(int roomId);


}



// @Query("select h.hotelName,hrt.bedType,hrt.roomType,hrt.id,a.breaFastLunc,a.breakfast,a.freeWifi,a.gym,a.parkingArea,a.spa,a.swimmingPool,r.rating,r.star,c.fullName from Amenities a join a.hotelRoomType hrt join hrt.hotel h join Review r on r.hotel.id=h.id join r.customer c where hrt.id=?1")
