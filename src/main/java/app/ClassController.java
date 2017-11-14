package app;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
    	String result = null;
    	HttpURLConnection connection = null;
    	try {
			URL timetableURL = new URL("http://sutd-timetable.herokuapp.com/group_sections/?" + klass);
			connection = (HttpURLConnection) timetableURL.openConnection();
			connection.connect();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
				JsonElement root = new JsonParser().parse(new InputStreamReader(connection.getInputStream()));
				result = root.getAsJsonObject().get("events").getAsJsonArray().toString();
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