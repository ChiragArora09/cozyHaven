package com.group_3.cozyHaven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Hotel;
import com.group_3.cozyHaven.model.Room;
import com.group_3.cozyHaven.model.ServiceProvider;
import com.group_3.cozyHaven.repository.HotelRepository;
import com.group_3.cozyHaven.repository.RoomRepository;
import com.group_3.cozyHaven.service.RoomService;
import com.group_3.cozyHaven.service.ServiceProviderService;

@SpringBootTest
public class RoomServiceTest {
	
	@InjectMocks
	private RoomService roomService;
	
	@Mock
	private RoomRepository roomRepository;
	
	@Mock
	private ServiceProviderService serviceProviderService;
	
	@Mock
	private HotelRepository hotelRepository;
	
	@Test
	public void getAllRoomsTest() {
		int hotelId=1;
	Room room=new Room();
	Hotel hotel=new Hotel();
    hotel.setId(hotelId);		
    
    room.setId(hotelId);
    room.setPrice(1000);
    room.setTotalRooms(10);
    List<Room> list=Arrays.asList(room);
    when(roomRepository.getAllRooms(hotelId)).thenReturn(list);
    int expectedNum=1;
    List<Room> roomlist=roomService.getAllRooms(hotelId);
    assertNotNull(roomlist);
    int actualNum=roomlist.size();
    assertEquals(expectedNum, actualNum);
    
	}
	
	@Test
	public void addRoomTest() throws InvalidIdException, InputValidationException {
		int hotelId=1;
		int serviceProviderId=1;
		ServiceProvider serviceProvider=new ServiceProvider();
		serviceProvider.setId(serviceProviderId);
		Room room=new Room();
		Hotel hotel=new Hotel();
		hotel.setId(hotelId);
		room.setId(hotelId);
		room.setPrice(1000);
		room.setTotalRooms(10);
		when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
		when(roomRepository.save(room)).thenReturn(room);
		Room actualValue=roomService.addRoom(serviceProviderId,hotelId,room);
		assertNotNull(actualValue);
		assertEquals(room,actualValue);
	}

}
