package app.models;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class FoodArea extends Area {
	
	public FoodArea(Long id){
		super(id);
	}
	
	public String toString(){
		return super.toString();
	}
}
