package models;

import lombok.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

@Data
@Entity
public class Statistic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private Date data_date;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;
}