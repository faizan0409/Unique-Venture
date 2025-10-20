package com.ozone.application.utils;

public class NumberUtils {

	/**
	 * Method used to pad zeroes for pattern of employee id
	 * @param num
	 * @return
	 */
	public static String padZeroes(int num) {
		String number = Integer.toString(num);
		
		int length = number.length();
		switch (length) {

		case 1:
			number = "00" + number;
			break;
		case 2:
			number = "0" + number;
			break;
		}
		
		return number;
	}
}
