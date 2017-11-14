package app.models;

import lombok.Data;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import org.hibernate.annotations.*;

@Entity
@Data
public class Statistic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private Date data_date;

	@NotNull
	@ManyToOne
	@Any(metaColumn = @Column(name = "area_type"), fetch = FetchType.LAZY)
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = {
			@MetaValue(targetEntity = FoodArea.class, value = "FoodArea"),
			@MetaValue(targetEntity = MeetingArea.class, value = "MeetingArea") })
	@JoinColumn(name = "area_id")
	private Area area;
}