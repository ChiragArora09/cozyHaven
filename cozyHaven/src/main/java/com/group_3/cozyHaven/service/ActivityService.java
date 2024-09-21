package com.group_3.cozyHaven.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InputValidationException;
import com.group_3.cozyHaven.model.Activity;
import com.group_3.cozyHaven.model.Day;
import com.group_3.cozyHaven.model.HolidayPackage;
import com.group_3.cozyHaven.repository.ActivityRepository;
import com.group_3.cozyHaven.repository.DayRepository;

@Service
public class ActivityService {
	
	@Autowired
	private DayRepository dayRepository;
	
	@Autowired
	private ActivityRepository activityRepository;

	public Activity addActivity(int dayId, Activity activity) throws InputValidationException {
		Optional<Day> optionalDay=dayRepository.findById(dayId);
		if(optionalDay.isEmpty()) {
			throw new InputValidationException("Package does not involve this Day");
		}
		Day day=optionalDay.get();
		activity.setDay(day);
		return activityRepository.save(activity);
		
	}

}


