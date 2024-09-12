package com.group_3.cozyHaven.dto;

import com.group_3.cozyHaven.enums.ClassType;

public class FlightInputDto {
	private String source;
	private String destination;
	private ClassType classType;
	
	public FlightInputDto(String source, String destination, ClassType classType) {
		super();
		this.source = source;
		this.destination = destination;
		this.classType = classType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public ClassType getClassType() {
		return classType;
	}

	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

}
