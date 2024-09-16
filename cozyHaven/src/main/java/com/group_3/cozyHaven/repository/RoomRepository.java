package com.group_3.cozyHaven.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.enums.RoomType;
import com.group_3.cozyHaven.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("select r from Room r where r.hotel.id = ?1")
    List<Room> findByHotel(int hotelId);

    Optional<Room> findByRoomType(RoomType roomType);

    @Query("select r from Room r where r.hotel.id = ?1 and r.roomType = ?2 and (r.totalRooms - r.bookedRooms) > 0")
    List<Room> findByHotelRoomType(int hotelId, RoomType roomType);
}
