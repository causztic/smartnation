package app.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.RedisClient;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.api.StatefulRedisConnection;

@Component
public class RedisTasks {
    private static final Logger log = LoggerFactory.getLogger(RedisTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final RedisClient client = RedisClient.create("redis://localhost");
	public RedisTasks(){
		
	}
	
	// this does not work yet
    @PostConstruct
    public void onStartup() {
       updateDBFromRedis();
    }
    
    
    //@Scheduled(cron="0 0 * * * *")
    @Scheduled(fixedRate=1000)
    public void onSchedule(){
    	updateDBFromRedis();
    }
	
	/*
	 * Gets the real-time statistics and writes to the database.
	 */

	public void updateDBFromRedis() {
        log.info("Updating Database at {}", dateFormat.format(new Date()));

    	StatefulRedisConnection<String, String> connection = client.connect();
    	KeyScanCursor<String> cursor = connection.sync().scan(ScanArgs.Builder.limit(50).match("statistic"));
    	while (!cursor.isFinished()) {
    	    cursor = connection.sync().scan(cursor);
    	    System.out.println(cursor.getKeys());
    	}
    	
    	// scan for all keys starting with statistic
    	
    	connection.close();
//    	client.shutdown();
    }
}