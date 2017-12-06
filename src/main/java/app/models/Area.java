package app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.google.gson.Gson;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public abstract class Area {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private double longitude;
	@NotNull
	private double latitude;
	@NotNull
	private String name;
	private String image;
	private String headerImage;
	
	public Area(long id){
		this.id = id;
	}
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
	
}
