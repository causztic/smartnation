package models;

public class MeetingArea extends Area {
	
	public MeetingArea(){
		
	}
	
	public MeetingArea(String name, double latitude, double longitude){
		this.setName(name);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

}
