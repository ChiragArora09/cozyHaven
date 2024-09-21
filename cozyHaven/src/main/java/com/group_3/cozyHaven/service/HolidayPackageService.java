package com.group_3.cozyHaven.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.HolidayPackageDto;
import com.group_3.cozyHaven.dto.PackageDetailsDto;
import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Day;
import com.group_3.cozyHaven.model.Extra;
import com.group_3.cozyHaven.model.HolidayPackage;
import com.group_3.cozyHaven.model.PackageHotel;
import com.group_3.cozyHaven.repository.DayRepository;
import com.group_3.cozyHaven.repository.ExtraRepository;
import com.group_3.cozyHaven.repository.HolidayPackageRepository;
import com.group_3.cozyHaven.repository.PackageHotelRepository;

@Service
public class HolidayPackageService {

	@Autowired
	private HolidayPackageRepository holidayPackageRepository;
	
	@Autowired
    private PackageHotelRepository packageHotelRepository;
	
	@Autowired
	private DayRepository dayRepository;
	
	@Autowired
	private ExtraRepository extraRepository;
	
	public HolidayPackage addPackage(int packageHotelId ,HolidayPackage holidayPackage) throws InputValidationException {
		Optional<PackageHotel> optionalHotel=packageHotelRepository.findById(packageHotelId);
		if(optionalHotel.isEmpty()) {
			throw new InputValidationException("Package Id is not correct");
		}
		PackageHotel packageHotel=optionalHotel.get();
		holidayPackage.setPackageHotel(packageHotel);
		return holidayPackageRepository.save(holidayPackage);
	}

	public Day addDay(int holidayPackageId, Day day) throws InputValidationException {
		Optional<HolidayPackage> optionalHotel=holidayPackageRepository.findById(holidayPackageId);
		if(optionalHotel.isEmpty()) {
			throw new InputValidationException("Package Id is not correct");
		}
		HolidayPackage holidayPackage=optionalHotel.get();
		day.setHolidayPackage(holidayPackage);
		return dayRepository.save(day);
		
	}

	public Extra addExtra(int holidayPackageId, Extra extra) throws InputValidationException {
		Optional<HolidayPackage> optionalHotel=holidayPackageRepository.findById(holidayPackageId);
		if(optionalHotel.isEmpty()) {
			throw new InputValidationException("Package Id is not correct");
		}
		HolidayPackage holidayPackage=optionalHotel.get();
		extra.setHolidayPackage(holidayPackage);
		return extraRepository.save(extra);
		
	}

	public List<HolidayPackageDto> searchPackage(HolidayPackage holidayPackage,String username) {
		String fromLocation=holidayPackage.getFromLocation();
		String toLocation=holidayPackage.getToLocation();
		String type=holidayPackage.getType();
		int numGuests=holidayPackage.getNumGuests();
		int numDays=holidayPackage.getNumDays();
		
		List<Object[]> list=holidayPackageRepository.findByDetails(fromLocation, toLocation, type, numGuests, numDays);
		
	    List<HolidayPackageDto> listDto=new ArrayList<>();
		
		for(Object[] obj:list) {
			int packageHotelId=(int) obj[0];
		    int holidayPackageId=(int) obj[1];
		    String location=obj[2].toString();
			String name=obj[3].toString();
			String info=obj[4].toString();
		    String price=obj[5].toString();
			String numActivities=obj[6].toString();
			String hotelTransfer=obj[7].toString();
			String meals=obj[8].toString();
			HolidayPackageDto dto=new HolidayPackageDto(packageHotelId,holidayPackageId,location,name,info,price,numActivities,hotelTransfer,meals);
			
			//dto.setPackageHotelId(packageHotelId);
			
			listDto.add(dto);
		} 
		return listDto;
	}

	public List<PackageDetailsDto> viewDetails(int holidayPackageId) throws InputValidationException {
		Optional<HolidayPackage> optionalHotel=holidayPackageRepository.findById(holidayPackageId);
		if(optionalHotel.isEmpty()) {
			throw new InputValidationException("Package Id is not correct");
		}
		HolidayPackage holidayPackage=optionalHotel.get();
		
		List<Object[]> list=holidayPackageRepository.findDetailedView(holidayPackageId);
		List<PackageDetailsDto> listDto=new ArrayList<>();
		
		for(Object[] obj:list) {
			String location=obj[0].toString();
			String type=obj[1].toString();
			String info=obj[2].toString();
			String activityName=obj[3]!= null ? obj[3].toString() : null;
			String activityInfo=obj[4]!= null ? obj[3].toString() : null;
			String dayInfo=obj[5].toString();
			
			PackageDetailsDto dto=new PackageDetailsDto(location,type,info,activityName,activityInfo,dayInfo);
			listDto.add(dto);
		}
		return listDto;
		
	}
}

