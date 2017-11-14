package app;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.CohortClass;

@RestController
public class ClassController {

    @RequestMapping("/classes")
    public String[] index() {
    	return CohortClass.CLASSES;
    }
    
    /*
     * Get the class schedule for a particular class.
     * @param klass		the class to get. It should be one of CohortClass.CLASSES.
     */
    @RequestMapping(value="/classes/{klass}", method=RequestMethod.GET)
    public String getClasses(@PathVariable String klass){
    	String result = getTimetable(klass).toString();
    	return result;
    }
    
    @RequestMapping(value="/classes/{klass}/this_week", method=RequestMethod.GET)
    public String getClassesThisWeek(@PathVariable String klass){
    	String result = null;
    	
    	JsonArray timetable = getTimetable(klass);
    	JsonArray coursesInWeek = new JsonArray();
    	
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		// get the current week to check
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		
		Calendar nextWeek = (Calendar) cal.clone();
		nextWeek.add(Calendar.WEEK_OF_YEAR, 1);

    	for (JsonElement element: timetable){
    		JsonObject obj = element.getAsJsonObject();
    		
    		String start = obj.get("start").getAsString();
    		String end = obj.get("end").getAsString();
    		
    		// java8 date parsing
			Date startDate = Date.from(Instant.parse(start+'Z'));
			Date endDate = Date.from(Instant.parse(end+'Z'));
			
			if (startDate.after(cal.getTime()) && endDate.before(nextWeek.getTime())){
				// if startDate occurs after this week and before
				coursesInWeek.add(obj);
			}
    	}

		result = coursesInWeek.toString();
		
    	return result;
    }
    
    private JsonArray getTimetable(String klass){
    	HttpURLConnection connection = null;
    	JsonArray result = null;
    	
    	try {
			URL timetableURL = new URL("http://sutd-timetable.herokuapp.com/group_sections/?" + klass);
			connection = (HttpURLConnection) timetableURL.openConnection();
			connection.connect();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
				JsonElement root = new JsonParser().parse(new InputStreamReader(connection.getInputStream()));
				result = root.getAsJsonObject().get("events").getAsJsonArray();
			} else {
				throw new Exception("Server responded with a response code of: " + connection.getResponseCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
    	
    	return result;
    }
}