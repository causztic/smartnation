package app;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.models.Area;
import app.repositories.FoodAreaRepository;
import app.repositories.MeetingAreaRepository;

@RestController
public class AreaController {
	@Autowired
	private FoodAreaRepository fa;
	
	@Autowired
	private MeetingAreaRepository ma;
	
	@RequestMapping(value="/area/{type}", method=RequestMethod.GET, produces ="application/json")
	public String findAreaByType(@PathVariable String type){
		List<Area> area = new ArrayList<>();
		if (type.equals("food"))
			fa.findAll().forEach(area::add);
		else
			ma.findAll().forEach(area::add);
		
		return new Gson().toJson(area, new TypeToken<ArrayList<Area>>() {}.getType());
	}
}
