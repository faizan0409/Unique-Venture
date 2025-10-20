package com.ozone.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.internal.bind.SqlDateTypeAdapter;

public class JsonUtils {

	/**
	 * Method used to get the json object from arguments
	 * @param columnTitle
	 * @param columnData
	 * @param visibility
	 * @return
	 */
	public static JsonObject createJSONObjectOfColumn(String columnTitle, String columnData, boolean visibility) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("data", columnData);
		jsonObject.addProperty("title", columnTitle);
		jsonObject.addProperty("visible", visibility);
		return jsonObject;
	}
	
	public static Gson createGsonObjectForDateFormat() {
		SqlDateTypeAdapter sqlAdapter = new SqlDateTypeAdapter();
		Gson gson = new GsonBuilder()
		   .registerTypeAdapter(java.sql.Date.class, sqlAdapter)
		   .setDateFormat("dd-MM-yy")
		   .create();
		
		return gson;
	}
}
