package com.ozone.application.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	public static String getCurrentDateInMMDDYYYYFormat() {
		String formattedDate;

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
		Date dateobj = new java.sql.Date(new Date().getTime());
		formattedDate = formatter.format(dateobj);
		
		return formattedDate;
	}
}
