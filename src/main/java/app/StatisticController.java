package app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.models.Statistic;
import app.repositories.StatisticRepository;

@RestController
public class StatisticController {
	
	@Autowired
	private StatisticRepository repo;
	
	@RequestMapping(value="/stats/{area}", method=RequestMethod.GET, produces="application/json")
	public String getStatsByArea(@PathVariable int area){
		List<Statistic> stats = repo.findByArea(area);
		return new Gson().toJson(stats, new TypeToken<ArrayList<Statistic>>() {}.getType());
	}
	
	@RequestMapping(value="/stats/?from={from}&to={to}", method=RequestMethod.GET, produces="application/json")
	public String getStatsBetweenDates(@PathVariable String from, @PathVariable String to){
		List<Statistic> stats = repo.findBetweenDates(to, from);
		return new Gson().toJson(stats, new TypeToken<ArrayList<Statistic>>() {}.getType());
	}
}
