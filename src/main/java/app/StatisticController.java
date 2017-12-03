package app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
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
	public String getStatsByArea(@PathVariable int area, @RequestParam Map<String,String> allRequestParams){
		if (allRequestParams.containsKey("from") && allRequestParams.containsKey("to")){
			Timestamp from = Timestamp.valueOf(allRequestParams.get("from"));
			Timestamp to = Timestamp.valueOf(allRequestParams.get("to"));
			
			if (allRequestParams.containsKey("part")) {
				String part = allRequestParams.get("part");
				switch (part){
				case "hour":
				case "minute":
				case "day":
					List<Object> stats = repo.averagePerHour(area, from, to, part);
					return new Gson().toJson(stats, new TypeToken<ArrayList<Statistic>>() {}.getType());
				default:
					throw new IllegalArgumentException("Part has to be either 'hour', 'minute', or 'day'.");
				}
			} else {
				List<Statistic> stats = repo.findBetweenDates(area, from, to);
				return new Gson().toJson(stats, new TypeToken<ArrayList<Statistic>>() {}.getType());
			}
		}

		List<Statistic> stats = repo.findByArea(area);
		return new Gson().toJson(stats, new TypeToken<ArrayList<Statistic>>() {}.getType());
	}
	
}
