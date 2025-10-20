package com.ozone.notification.component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class SmsGateway {

	private static final String UTF_8 = "UTF-8";
	private static final String UNI = "UNI";
	private static final String TEXT = "TEXT";
	
	//@Value("${spring.sms.username}")
	private String username = "Ozone@999";
	
	//@Value("${spring.sms.password}")
	private String password = "Ozone@999";
	
	//@Value("${spring.sms.senderid}")
	private String senderid = "OzoneS";
	
	private static final String responseRequired = "Y";
	
	
	private RestTemplate restTemplate = new RestTemplate();

	public boolean sendSms(String message,String mobileNumber) throws UnsupportedEncodingException {
		
		boolean smsSentStatus = true;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://www.smsjust.com/sms/user/urlsms.php");
		builder.queryParam("username", username);
		builder.queryParam("pass", password);
		builder.queryParam("senderid", senderid);
		builder.queryParam("message", URLEncoder.encode(replaceSpacialCharacterFromMSG(message), UTF_8));
		//builder.queryParam("message", replaceSpacialCharacterFromMSG(message), UTF_8);
		builder.queryParam("dest_mobileno", mobileNumber);
		builder.queryParam("response", responseRequired);
		builder.queryParam("msgtype", UNI);
		
		ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
			
		if(!response.getStatusCode().is2xxSuccessful()) {
			//throw new RuntimeException("Exception while sending SMS");
			smsSentStatus = false;
		}
		
		return smsSentStatus;
	}
	
	private String replaceSpacialCharacterFromMSG(String message) {
		
		if(message.contains("&")){
			message = message.replace("&", " amp; ");
		}
		
		if(message.contains("#")) {
			message = message.replace("#", " ;hash ");
		}
		
		if(message.contains("+")) {
			message = message.replace("+", " plus; ");
		}
		
		if(message.contains(",")) {
			message = message.replace(",", " comma; ");
		}
		
		//System.out.println("newMsgString : "+message);
		return message;
	}
	
}
