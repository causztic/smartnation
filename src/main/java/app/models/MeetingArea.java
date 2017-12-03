package app.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class MeetingArea extends Area {
	
	@NotNull
	private String meetingCategory;
	
	public String toString(){
		return super.toString();
	}
}
