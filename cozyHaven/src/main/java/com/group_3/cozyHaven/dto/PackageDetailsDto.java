package com.group_3.cozyHaven.dto;

public class PackageDetailsDto {
	
	private String location;
	
	private String type;

	private String info;
	
	private String activityName;
	
	private String activityInfo;
	
	private String dayInfo;
	
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityInfo() {
		return activityInfo;
	}

	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}

	public String getDayInfo() {
		return dayInfo;
	}

	public void setDayInfo(String dayInfo) {
		this.dayInfo = dayInfo;
	}

	public PackageDetailsDto(String location, String type, String info, String activityName, String activityInfo,
			String dayInfo) {
		super();
		this.location = location;
		this.type = type;
		this.info = info;
		this.activityName = activityName;
		this.activityInfo = activityInfo;
		this.dayInfo = dayInfo;
	}
	
	
	
}
