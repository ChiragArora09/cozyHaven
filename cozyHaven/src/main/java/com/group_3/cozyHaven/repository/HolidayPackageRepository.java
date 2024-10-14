package com.group_3.cozyHaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group_3.cozyHaven.dto.HolidayPackageDto;
import com.group_3.cozyHaven.model.HolidayPackage;
import com.group_3.cozyHaven.model.PackageHotel;

@Repository
public interface HolidayPackageRepository extends JpaRepository<HolidayPackage, Integer> {

	/*@Query(value="select ph.id,h.id,ph.location,ph.name,ph.price,ph.info,e.num_activities,e.hotel_transfer,e.meals\r\n"
	+ "from holiday_package h join package_hotel ph on h.package_hotel_id=ph.id join day d on d.holiday_package_id=h.id join extra e on e.holiday_package_id=h.id \r\n"
	+ " where h.from_location=? and h.to_location=?\r\n"
	+ "and h.type=? and h.num_guests=? and h.num_days=?",nativeQuery=true)*/
	
	//@Query("select ph.id, h.id, ph.fromLocation,ph.toLocation, ph.name, ph.price, ph.info,e.numActivities, e.hotelTransfer, e.meals from Extra e join e.holidayPackage h join h.packageHotel ph  where h.fromLocation = ?1 and h.toLocation = ?2 and h.type = ?3 and h.numGuests = ?4 and h.numDays = ?5")
	
	@Query(value="select ph.id,hp.id,hp.from_location,hp.to_location,hp.discount,hp.code,ph.name,ph.info,ph.price, e.num_activities,e.hotel_transfer,e.meals,v.info,v.type from holiday_package hp join package_hotel ph on hp.package_hotel_id = ph.id join holiday_package_vehicle hv ON hp.id = hv.holiday_package_id join package_vehicle v ON hv.package_vehicle_id = v.id join extra e ON e.holiday_package_id = hp.id "
			+ "where hp.from_location = ? AND hp.to_location = ? and hp.type=? and hp.num_guests=? and num_days=?",nativeQuery = true)
	List<Object[]> findByDetails(String fromLocation, String toLocation, String type, int numGuests, int numDays);

	
	//@Query("select hp.location,pv.type,pv.info,a.name,a.info,d.dayInfo from HolidayPackageVehicle hpv join hpv.holidayPackage hp join hpv.packageVehicle pv join Day d on d.holidayPackage.id=hp.id left join d.activites a where hp.id=?1 ")
	
	@Query(value="SELECT  hp.to_location,pv.type,pv.info,a.name,a.info,d.day_info from holiday_package hp join holiday_package_vehicle hpv on hpv.holiday_package_id = hp.id join package_vehicle pv on hpv.package_vehicle_id = pv.id join day d on hp.id = d.holiday_package_id left join activity a on d.id = a.day_id where hp.id=?1",nativeQuery = true)
	List<Object[]> findDetailedView(int holidayPackageId);

}
