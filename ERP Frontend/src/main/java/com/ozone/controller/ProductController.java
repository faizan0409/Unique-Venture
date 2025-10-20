
package com.ozone.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ozone.model.Enquiry;
import com.ozone.model.Company;
import com.ozone.model.Department;
import com.ozone.util.ApplicationConstant;
import com.ozone.util.EMPRuntimeException;
import com.ozone.util.JsonUtils;

@Controller
public class ProductController {
	@RequestMapping("/sendEnquiry")
	public String sendEnquiry(HttpServletRequest request, Model model) {
		try {
			Enquiry enquiry = new Enquiry();

			enquiry.setName(request.getParameter("name").trim());
			enquiry.setEmail(request.getParameter("email").trim());
			enquiry.setSub(request.getParameter("sub").trim());
			enquiry.setDes(request.getParameter("desc").trim());

			String enquiryJSON = JsonUtils.createGsonObjectForDateFormat().toJson(enquiry);
			HttpHeaders httpHeader = new HttpHeaders(); // HTTPHeader.createHeaders(username, password);
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(enquiryJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> resp = null;

			resp = restTemplate.exchange(ApplicationConstant.URL_PRODUCT_PAGE + "sendEnquiry", HttpMethod.POST, entity,
					String.class);
			String result = resp.getBody();
			String message = JsonParser.parseString(result).getAsJsonObject().get("message").getAsString();

			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());

			} else {
				System.out.println("Message Send successfully ");
				model.addAttribute("msg", message);
				return "index";
				
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "index";
	}
	@RequestMapping("/companytab")	
	public String companytab(Model model) throws JsonProcessingException {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
		Company form = new Company();
		List<Company> companys = new ArrayList<Company>();
		resp = restTemplate.exchange(ApplicationConstant.URL_COMPANYPROFILE + "getAllCompanies", HttpMethod.GET, entity,
				String.class);
		System.out.println("resp:" + resp);
		String result = resp.getBody();
		ObjectMapper mapper = new ObjectMapper();
		if (resp.getStatusCode() != HttpStatus.OK) {

			try {
				model.addAttribute("companylist", mapper.writeValueAsString(companys));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		} else {
			if (result.contains("content")) {
				JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content").getAsJsonArray();
				System.out.println(contents);
				if (contents.size() > 0) {
					for (JsonElement jsonNode : contents) {
						Company company = new Company();
						JsonObject object = jsonNode.getAsJsonObject();
						 company.setName(object.get("name").getAsString());
						    company.setOwnerName(object.get("ownerName").getAsString());
						    company.setRegistrationDate(object.get("registrationDate").getAsString());
						    company.setGstNumber(object.get("gstNumber").getAsString()!= null || object.get("gstNumber").getAsString()!= "" ? object.get("gstNumber").getAsString() :"");
						    company.setAddress(object.get("address").getAsString());
						    company.setEmail(object.get("email").getAsString());
						    company.setContactNumber(object.get("contactNumber").getAsString());
						    if(null != object.get("website")) {
						    	company.setWebsite(object.get("website").getAsString());
						    }else {
						    	company.setWebsite("");
						    }
						    
						    
						    company.setSupportContactNumber(object.get("supportContactNumber").getAsString()!= null || object.get("supportContactNumber").getAsString()!= "" ? object.get("supportContactNumber").getAsString() :"");
						    company.setSupportEmailId(object.get("supportEmailId").getAsString()!= null || object.get("supportEmailId").getAsString()!= "" ? object.get("supportEmailId").getAsString() :"");
						    company.setSupportEmailPassword(object.get("supportEmailPassword").getAsString()!= null || object.get("supportEmailPassword").getAsString()!= "" ? object.get("supportEmailPassword").getAsString() :"");
						    company.setDeleteStatus(object.get("deleteStatus").getAsBoolean());
						    company.setCategory(object.get("category").getAsString());
						    company.setType(object.get("type").getAsString());
						companys.add(company);
					}
				}
				model.addAttribute("companylist", mapper.writeValueAsString(companys));
			} else {
				model.addAttribute("companylist", mapper.writeValueAsString(companys));
			}
			model.addAttribute("myCompany", form);
		}
	
		return "companytab";
	}
	@RequestMapping(value = "/changestatus", method = { RequestMethod.POST })
	public String saveRole(@RequestParam("companyEmailId") String companyEmailId, @RequestParam("companyStatus") boolean companyStatus) {
		ResponseEntity<String> resp = null;
		
		try {
			System.out.println("companyEmailId: "+companyEmailId);
			System.out.println("companyStatus: "+companyStatus);
			Company company = new Company();
			company.setEmail(companyEmailId);
			company.setDeleteStatus(companyStatus);
			String updateJSON = JsonUtils.createGsonObjectForDateFormat().toJson(company);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>(updateJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();
			resp = restTemplate.exchange(ApplicationConstant.URL_COMPANYPROFILE + "changeStatus", HttpMethod.POST, entity,
					String.class);
			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EMPRuntimeException(resp.getBody().toString());
		}
		return "redirect:companytab";
	}
}
