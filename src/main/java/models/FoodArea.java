package models;

public class FoodArea extends Area {
	
	public FoodArea(){
		
	}
	
	public FoodArea(String name, double latitude, double longitude){
		this.setName(name);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}
	
}
