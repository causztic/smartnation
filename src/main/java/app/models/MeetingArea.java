package app.models;

import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class MeetingArea extends Area {

	public String toString(){
		return super.toString();
	}
}
