package app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.models.Area;
import app.repositories.FoodAreaRepository;
import app.repositories.MeetingAreaRepository;

@RestController
public class AreaController {
	@Autowired
	private FoodAreaRepository fa;
	
	@Autowired
	private MeetingAreaRepository ma;
	
	@RequestMapping(value="/area/{type}", method=RequestMethod.GET)
	public String findAreaByType(@PathVariable String type){
		List<Area> area = new ArrayList<>();
		if (type.equals("food"))
			fa.findAll().forEach(area::add);
		else
			ma.findAll().forEach(area::add);
		
		return area.stream().map(Area::toString)
				.collect(Collectors.joining(", "));
	}
}
