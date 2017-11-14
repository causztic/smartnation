package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import app.repositories.StatisticRepository;

@RestController
public class StatisticController {
	
	@Autowired
	private StatisticRepository repo;
	
	@RequestMapping(value="/stats/{area}", method=RequestMethod.GET)
	public String getStatsByArea(@PathVariable String area){
		return repo.findByArea(area).toString();
	}
}
