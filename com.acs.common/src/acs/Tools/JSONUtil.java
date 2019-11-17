package acs.Tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONUtil {
	private static Logger logger = LoggerFactory.getLogger(JSONObject.class); 
	public static String get_Hashmap_value (HashMap hmap, String key) {
		String rtn = null;
		try {
		    rtn = (String) hmap.get(key);	
		    return rtn;
		} catch(Exception exception){
			  // how you handle the exception
				logger.debug(exception.getMessage());	
			return rtn;
		}
		
	}
	public static String get_JSONSTR (org.json.JSONObject Jobject, String key) {
		String rtn = null;
		try {
			
		    rtn = Jobject.getString(key);	
		    return rtn;
		} catch(org.json.JSONException exception){
			  // how you handle the exception
			if ((exception.getMessage().indexOf("not found") < 0)){
				logger.debug(exception.getMessage());	
			}
			return rtn;
		}
		
	}
	public static JSONObject get_JSONObj(org.json.JSONObject Jobject, String key) {
		JSONObject rtn = null;
		try {
			
		    rtn = Jobject.getJSONObject(key);	
		    return rtn;
		} catch(org.json.JSONException exception){
			  // how you handle the exception
			//if ((exception.getMessage().indexOf("not found") < 0)){
				logger.debug(exception.getMessage());	
			//}
			return rtn;
		}
		
	}
	
	 public static Map<String, Object> getJSONObject(JSONObject jsonObject) {
	        Map<String, Object> map = new HashMap<String, Object>();

	        Iterator<String> keyIterator = jsonObject.keys();
	        while (keyIterator.hasNext()) {
	            String key = keyIterator.next();
	            try {
	                Object obj = jsonObject.get(key);

	                if (obj instanceof JSONObject) {
	                    map.put(key, getJSONObject((JSONObject) obj));
	                }
	                else if (obj instanceof JSONArray) {
	                    map.put(key, getJSONObject((JSONArray) obj));
	                }
	                else {
	                    map.put(key, obj);
	                }
	            }
	            catch (JSONException jsone) {
	            	logger.debug("RequestManager: Failed to get value for " + key + " from JSONObject.(" + jsone.getLocalizedMessage() + ")");
	            }
	        }

	        return map;
	    }
	 public static List<Object> getJSONObject(JSONArray jsonArray) {
	        List<Object> list = new ArrayList<Object>();

	        for (int i = 0; i < jsonArray.length(); i++) {
	            try {
	                Object obj = jsonArray.get(i);

	                if (obj instanceof JSONObject) {
	                    list.add(getJSONObject((JSONObject) obj));
	                }
	                else if (obj instanceof JSONArray) {
	                    list.add(getJSONObject((JSONArray) obj));
	                }
	                else {
	                    list.add(obj);
	                }
	            }
	            catch (JSONException jsone) {
	                logger.debug("RequestManager: Failed to get value at index " + i + " from JSONArray.(" + jsone.getLocalizedMessage() + ")");
	            }
	        }

	        return list;
	    }
	public static JSONArray get_JSONArray(org.json.JSONObject Jobject, String key) {
		JSONArray rtn = null;
		try {
			
		    rtn = Jobject.getJSONArray(key);	
		    return rtn;
		} catch(org.json.JSONException exception){
			  // how you handle the exception
			//if ((exception.getMessage().indexOf("not found") < 0)){
				logger.debug(exception.getMessage());	
			//}
			return rtn;
		}
		
	}
}
