package app;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import app.tasks.RedisTasks;
import io.lettuce.core.api.StatefulRedisConnection;

@Controller
public class DashboardController {
	
    @PersistenceContext
    private EntityManager em;
    
	@RequestMapping(value="/stats")
	public String stats(Model model){
		Query query = em.createNativeQuery("SELECT COUNT(*) FROM Statistic");
		model.addAttribute("rows", query.getSingleResult());
		StatefulRedisConnection<String, String> connection = RedisTasks.client.connect();
		String result = connection.sync().get("stat:Canteen:latest");
		model.addAttribute("canteen", result);
		return "stats";
	}
}
