package app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app.models.Statistic;
import app.repositories.StatisticRepository;

@RestController
public class StatisticController {
	
	@Autowired
	private StatisticRepository repo;
	
	@RequestMapping(value="/stats/{area}", method=RequestMethod.GET)
	public String getStatsByArea(@PathVariable String area){
		List<Statistic> stats = repo.findByArea(area);
		return stats.stream().map(Statistic::toString)
				.collect(Collectors.joining(", "));
	}
}
