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
	
	@RequestMapping(value="/stats/{area}?from={from}&to={to}", method=RequestMethod.GET, produces="application/json")
	public String getStatsBetweenDates(@PathVariable int area, @PathVariable String from, @PathVariable String to){
		List<Statistic> stats = repo.findBetweenDates(area, to, from);
		return new Gson().toJson(stats, new TypeToken<ArrayList<Statistic>>() {}.getType());
	}
	
	@RequestMapping(value="/stats/{area}?from={from}&to={to}&part={part}", method=RequestMethod.GET, produces="application/json")
	public String getAveragetatsBetweenDates(
			@PathVariable int area, 
			@PathVariable String from, @PathVariable String to,
			@PathVariable String part){
		switch (part){
		case "hour":
		case "minute":
		case "day":
			List<Statistic> stats = repo.averagePerHour(area, to, from, part);
			return new Gson().toJson(stats, new TypeToken<ArrayList<Statistic>>() {}.getType());
		default:
			throw new IllegalArgumentException("Part has to be either 'hour', 'minute', or 'day'.");
		}
	}
}
