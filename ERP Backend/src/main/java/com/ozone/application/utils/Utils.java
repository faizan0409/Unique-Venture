package com.ozone.application.utils;

import java.util.Random;

public class Utils {

	public static String templateForgotPassword(String mobNumber, String randomPassword) {
		return "Hi " + mobNumber + " Please use this OTP to reset your password " + randomPassword ;
	}

	public static String templateRegistrationOtp(String emailId,String randomPassword) {
		return "Hi " + " Please use this OTP " + randomPassword + " to verify your email Id " + emailId;
	}

	public static String generateOTP(int len) {
		//String str = "abcdefghijklmnopqrstuvwxyzABCD" + "EFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String str = "0123456789";
		String OTP = new String();
		Random rndm_method = new Random();

		for (int i = 1; i <= len; i++)
			OTP += str.charAt(rndm_method.nextInt(str.length()));

		return OTP;
	}
}
