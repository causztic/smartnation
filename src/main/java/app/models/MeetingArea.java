package app.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class MeetingArea extends Area {
	
	@NotNull
	private String meetingCategory;
	
	public MeetingArea(Long id){
		super(id);
	}
	
	public String toString(){
		return super.toString();
	}
}
