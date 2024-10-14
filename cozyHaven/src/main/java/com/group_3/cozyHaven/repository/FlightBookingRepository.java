package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group_3.cozyHaven.model.FlightBooking;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Integer> {
// select fb.date, fb.source, fb.destination, fc.type, f.name, f.number, fb.amount from flight_booking fb JOIN flight_seat_booking fsb ON fsb.flight_booking_id=fb.id JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fc.id=fs.flight_class_id JOIN flight f ON f.id=fc.flight_id WHERE fb.customer_id=1;
	@Query("SELECT distinct(fb.id), fb.date, fb.source, fb.destination, f.name, f.number, fb.amount, fb.status FROM FlightSeatBooking fsb JOIN fsb.flightBooking fb JOIN flightSeat fs JOIN fs.flightClass fc JOIN fc.flight f WHERE fb.customer.id=?1")
	List<Object[]> getBookings(int customerId);

	@Query("select sp.id FROM FlightSeatBooking fsb JOIN fsb.flightBooking fb JOIN fsb.flightSeat fs JOIN fs.flightClass fc JOIN fc.flight f JOIN f.serviceProvider sp WHERE fb.id=?1")
	List<Object[]> getProviderIdFromBookingId(int bid);

	@Query(value = "SELECT * FROM flight_booking WHERE customer_id=?1", nativeQuery = true)
	List<Object[]> getMyBookings(int customerId);

	@Query(value = "select COUNT(distinct(fsb.flight_booking_id)) as booking_count, CONCAT(f.name, ' (',f.number,')') AS FLIGHT_INFO from flight_booking fb JOIN flight_seat_booking fsb on fsb.flight_booking_id=fb.id JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fc.id=fs.flight_class_id JOIN flight f ON f.id=fc.flight_id JOIN service_provider sp ON sp.id=f.service_provider_id WHERE sp.id=?1 GROUP BY f.id ORDER BY booking_count DESC", nativeQuery = true)
	List<Object[]> getPopularFlights(int serviceProviderId);

	@Query(value = "select COUNT(distinct(fsb.flight_booking_id)) as TOTAL_BOOKINGS from flight_booking fb JOIN flight_seat_booking fsb on fsb.flight_booking_id=fb.id JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fc.id=fs.flight_class_id JOIN flight f ON f.id=fc.flight_id JOIN service_provider sp ON sp.id=f.service_provider_id WHERE sp.id=?1", nativeQuery = true)
	List<Object[]> getMyAllBookings(int serviceProviderId);

	@Query(value = "select COUNT(distinct(fsb.flight_booking_id)) as bookings, CONCAT(f.name, ' (',f.number,')') AS flight_info from flight_booking fb JOIN flight_seat_booking fsb on fsb.flight_booking_id=fb.id JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fc.id=fs.flight_class_id JOIN flight f ON f.id=fc.flight_id JOIN service_provider sp ON sp.id=f.service_provider_id WHERE sp.id=?1 AND fb.date=?2 GROUP BY f.id", nativeQuery = true)
	List<Object[]> getFlightBookigsByDate(int serviceProviderId, String date);

	@Query(value = "select COUNT(distinct(fb.customer_id)) as unique_customers from flight_booking fb JOIN flight_seat_booking fsb on fsb.flight_booking_id=fb.id JOIN flight_seat fs ON fs.id=fsb.flight_seat_id JOIN flight_class fc ON fc.id=fs.flight_class_id JOIN flight f ON f.id=fc.flight_id JOIN service_provider sp ON sp.id=f.service_provider_id WHERE sp.id=?1", nativeQuery = true)
	List<Object[]> getUniqueCustomers(int serviceProviderId);

	@Query(value = "SELECT CONCAT(f.name, ' (', f.number,')') as Flight_info, SUM(b.amount) AS TotalRevenue FROM (SELECT DISTINCT fb.id, fb.amount, fc.flight_id FROM flight_booking fb JOIN flight_seat_booking fsb ON fsb.flight_booking_id = fb.id JOIN flight_seat fs ON fs.id = fsb.flight_seat_id JOIN flight_class fc ON fc.id = fs.flight_class_id JOIN flight f ON f.id=fc.flight_id JOIN service_provider sp ON sp.id=f.service_provider_id WHERE sp.id=?1) AS b JOIN flight f ON f.id = b.flight_id GROUP BY f.id", nativeQuery = true)
	List<Object[]> getFlightRevenue(int serviceProviderId);

	@Query(value = "SELECT SUM(fb.amount), fb.date FROM (SELECT DISTINCT fb.id, fb.amount, f.id as flight_id FROM flight_booking fb JOIN flight_seat_booking fsb ON fsb.flight_booking_id = fb.id JOIN flight_seat fs ON fs.id = fsb.flight_seat_id JOIN flight_class fc ON fc.id = fs.flight_class_id JOIN flight f ON f.id=fc.flight_id JOIN service_provider sp ON sp.id=f.service_provider_id WHERE sp.id=?1) as b JOIN flight_booking fb ON fb.id=b.id JOIN flight f ON f.id=b.flight_id WHERE f.id=?2 GROUP BY fb.date", nativeQuery = true)
	List<Object[]> getFlightRevenueByDates(int serviceProviderId, int flightId);

}
