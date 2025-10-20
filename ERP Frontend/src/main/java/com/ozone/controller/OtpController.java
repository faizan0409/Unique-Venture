package com.ozone.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonParser;
import com.ozone.model.VerifyOtp;
import com.ozone.util.ApplicationConstant;

@Controller
public class OtpController {
	
	  @RequestMapping("/verifyotp") 
	  public String otpVerfication(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
		 try {
			
     	    RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> resp = null;
			HttpHeaders httpHeader = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
			/*
			 * String emailId=verify.getEmailId(); String otp = verify.getOtp();
			 */
			String emailId= session.getAttribute("emailId").toString();
			String otp1=request.getParameter("otp1").trim();
			String otp2=request.getParameter("otp2").trim();
			String otp3=request.getParameter("otp3").trim();
			String otp4=request.getParameter("otp4").trim();
			String otp=otp1.concat(otp2).concat(otp3).concat(otp4);
			
			resp = restTemplate.exchange(ApplicationConstant.URL+"validateotp?emailId="+emailId+"&otp="+otp, HttpMethod.GET, entity, String.class);
			String result = resp.getBody();
			String otpstatus = JsonParser.parseString(result).getAsJsonObject().get("message").getAsString(); 
			if(resp.getStatusCode().value()==200) {
//			model.addAttribute("otpstatus",otpstatus);
			System.out.println("Otp verified Successfully");
			 return "login";	
			}else {
			 model.addAttribute("otpstatus",otpstatus );
			 System.out.println("Otp not verified");
			 return "registerotp"; 
		 }
		   
	  }catch(Exception e) {
			
			return "registerotp"; 
	}
		
  }
}
