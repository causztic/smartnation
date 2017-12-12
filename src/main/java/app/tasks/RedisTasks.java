package app.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostLoad;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import app.models.FoodArea;
import app.models.MeetingArea;
import app.models.Statistic;
import app.repositories.StatisticRepository;
import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.RedisClient;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Component
public class RedisTasks {
    private static final Logger log = LoggerFactory.getLogger(RedisTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public static RedisClient client;
    private StatefulRedisConnection<String, String> connection;
    
    
    @Autowired
    StatisticRepository repo;
    
    @PersistenceContext
    private EntityManager em;
    
	public RedisTasks(){
		if (System.getenv("REDISTOGO_URL") == null)
			client = RedisClient.create("redis://localhost");
		else
			client = RedisClient.create(System.getenv("REDISTOGO_URL"));
	}
	
    @PostLoad
    public void onStartup() {
       updateDBFromRedis();
    }
    
    
    @Scheduled(cron="0 0 * * * *")
    //@Scheduled(fixedRate = 5000)
    public void onSchedule(){
    	updateDBFromRedis();
    }
	
	/*
	 * Gets the real-time statistics and writes to the database.
	 */

	public void updateDBFromRedis() {
        log.info("Updating Database at {}", dateFormat.format(new Date()));

    	connection = client.connect();
    	RedisCommands<String, String> cmd = connection.sync();
    	try {
	    	KeyScanCursor<String> cursor = cmd.scan(ScanArgs.Builder.limit(100).match("stat:*"));
	    	getValuesFromKeys(cursor.getKeys());
	    	
	    	while (!cursor.isFinished()){
	    		// move the cursor
	    		cursor = cmd.scan(cursor);
	        	getValuesFromKeys(cursor.getKeys());
	    	}
    	}
	    catch (IllegalArgumentException iae){
	    	//iae.printStackTrace();
	    	log.info("No keys were found.");
	    }
    	
    	connection.close();
//    	client.shutdown();
    }
	
	@SuppressWarnings("unchecked")
	public Map<String, Object[]> getIdAndName(){
		Query query = em.createNativeQuery("SELECT id, dtype, name FROM Area");
		List<Object[]> objs = query.getResultList();
		Map<String, Object[]> results = new HashMap<>();
		for (Object[] obj: objs){
			Object[] tmp = { obj[0], obj[1] };
			results.put((String) obj[2], tmp);
		}
		return results;
	}

	@Transactional
	public void getValuesFromKeys(List<String> keys){
		// get all name and id from postgres
		Map<String, Object[]> hm = getIdAndName();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()){
			String key = it.next();
		
			String[] keyParts = key.split(":");
			Integer value = Integer.parseInt(connection.sync().get(key));
			
			if (keyParts[2].equals("latest")){
				// remove this item from the list. We won't store it.
				it.remove();
				continue;
			}
			
			long milliseconds = Long.parseLong(keyParts[2]);
			
			Object[] item = hm.get(keyParts[1]);
			if (item != null){
				Long id = Long.valueOf((Integer)item[0]);
				String type = (String)item[1];
				Statistic s = new Statistic(new Date(milliseconds), value, 
						type.equals("FoodArea") ? new FoodArea(id) : new MeetingArea(id));
				repo.save(s);
			}
		}
		// flush redis
		connection.sync().del(keys.toArray(new String[keys.size()]));
	}
}