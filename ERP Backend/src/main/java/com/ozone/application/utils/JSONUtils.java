package com.ozone.application.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONUtils {

	/*
	 * getListFromJsonArray
	 */
	public static List<String> getListFromJsonArray(String jsonString){
		
		List<String> list = new ArrayList<String>();
		
		JsonParser parser = new JsonParser(); 
		JsonArray jArray = (JsonArray)parser. parse(jsonString);
		
		if (jArray != null) { 
		   for(JsonElement jsonElement : jArray) {
			   list.add(jsonElement.getAsString());
		   }
		}
		
		return list;
	}
	
	/*
	 * getHasMapFromJSONString to get timelog entry for every task against project
	 */
	public static Map<String, String[]> getHasMapFromJSONString(String jsonString){
		
		Map<String, String[]> taskTimelogEntry = new HashMap<String, String[]>();
		
		JsonParser parser = new JsonParser(); 
		JsonArray jArray = (JsonArray)parser. parse(jsonString);
		
		for(JsonElement jsonElement : jArray) {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			String[] timelogDetails = new String[2];
			timelogDetails[0] = jsonObject.get("comment").getAsString();
			timelogDetails[1] = jsonObject.get("timelog").getAsString();
			taskTimelogEntry.put(jsonObject.get("task").getAsString(), timelogDetails);
		}
		
		return taskTimelogEntry;
	}
}
