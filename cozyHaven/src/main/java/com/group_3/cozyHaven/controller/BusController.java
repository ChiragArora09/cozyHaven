package com.group_3.cozyHaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group_3.cozyHaven.dto.BusBetweenStopsDto;
import com.group_3.cozyHaven.dto.BusInputDto;
import com.group_3.cozyHaven.dto.MessageDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.Bus;
import com.group_3.cozyHaven.model.BusBooking;
import com.group_3.cozyHaven.model.BusSeat;
import com.group_3.cozyHaven.model.BusStop;
import com.group_3.cozyHaven.model.FlightBooking;
import com.group_3.cozyHaven.model.Stop;
import com.group_3.cozyHaven.service.BusBookingService;
import com.group_3.cozyHaven.service.BusSeatService;
import com.group_3.cozyHaven.service.BusService;
import com.group_3.cozyHaven.service.BusStopService;
import com.group_3.cozyHaven.service.StopService;


@RestController
@RequestMapping("/bus")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private StopService stopService;
	
	@Autowired 
	private BusStopService busStopService;
	
	@Autowired
	private BusSeatService busSeatService;
	
	@Autowired
	private BusBookingService busBookingService;
	
	// ADDING A BUS BY SERVICE PROVIDER
	@PostMapping("/add-bus/{serviceProviderId}")
	public ResponseEntity<?> addBus(@PathVariable int serviceProviderId, @RequestBody Bus bus, MessageDto dto){
		try {
			bus = busService.addBus(serviceProviderId, bus);
			return ResponseEntity.ok(bus);
		}catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);	
		}
	}
	
	// ADDING A STOP BY SERVICE PROVIDER
	@PostMapping("/stop")
	public ResponseEntity<?> addStop(@RequestBody Stop stop){
		stop = stopService.addStop(stop);
		return ResponseEntity.ok(stop);
	}
	
	// ADDING BUS AND ITS STOPS
	@PostMapping("/add/bus-stop/{busid}/{stopid}")
	public ResponseEntity<?> addBusAndItsStop(@PathVariable int busid, @PathVariable int stopid, @RequestBody BusStop busStop, MessageDto dto){
		try {
			busStop = busStopService.addBusAndStop(busid, stopid, busStop);
			return ResponseEntity.ok(busStop); 
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto); 
		}
	}
	
	// ADDING SEAT TO A BUS
	@PostMapping("/add/bus-seat/{busId}")
	public ResponseEntity<?> addBusSeat(@PathVariable int busId, @RequestBody BusSeat busSeat, MessageDto dto){
		try {
			busSeat = busSeatService.addBusSeat(busId, busSeat);
			return ResponseEntity.ok(busSeat);
		}catch(InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	// GETTING BUS INFORMATION BETWEEN TWO STOPS
	@GetMapping("/bus-between-stops")
	public List<BusBetweenStopsDto> busBetweenStopsDtos(@RequestBody BusInputDto busInputDto){
		return busService.getBusBetweenStops(busInputDto.getSource(), busInputDto.getDestination());
	}
	
	// Processing the BOOKING
	@PostMapping("/booking/{cust_id}/{busId}")
	public ResponseEntity<?> addBooking(@PathVariable int cust_id, @PathVariable int busId, @RequestBody BusBooking busBooking) throws InputValidationException{
			busBooking = busBookingService.addBooking(cust_id, busId, busBooking);
			return ResponseEntity.ok(busBooking); 
	}
}
