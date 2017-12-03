package app.models;

import lombok.Data;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import org.hibernate.annotations.*;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;

import adapters.StatisticAdapter;

@Entity
@Data
@JsonAdapter(StatisticAdapter.class)
public class Statistic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private Date data_date;
	
	@NotNull
	private int count;
	private transient double avg_count;
	private transient Date min_date;

	@NotNull
	@ManyToOne
	@Any(metaColumn = @Column(name = "area_type"), fetch = FetchType.LAZY)
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = {
			@MetaValue(targetEntity = FoodArea.class, value = "FoodArea"),
			@MetaValue(targetEntity = MeetingArea.class, value = "MeetingArea") })
	@JoinColumn(name = "area_id")
	private Area area;
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}

}