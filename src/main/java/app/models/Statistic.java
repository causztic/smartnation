package app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Statistic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private Date dataDate;
	
	@NotNull
	private int count;

	@NotNull
	@ManyToOne
	@Any(metaColumn = @Column(name = "area_type"), fetch = FetchType.LAZY)
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = {
			@MetaValue(targetEntity = FoodArea.class, value = "FoodArea"),
			@MetaValue(targetEntity = MeetingArea.class, value = "MeetingArea") })
	@JoinColumn(name = "area_id")
	private Area area;

	public Statistic(Date dataDate, int count, Area area){
		this.dataDate = dataDate;
		this.count = count;
		this.area = area;
	}
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}

}