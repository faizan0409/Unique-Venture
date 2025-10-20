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
import com.ozone.model.Employee;
import com.ozone.util.ApplicationConstant;
import com.ozone.util.EMPRuntimeException;
import com.ozone.util.JsonUtils;
import com.ozone.util.Service;

@Controller
public class EmployeeController {
	@RequestMapping("/employeemanagement")
	public String employeemanagement(Model model) throws JsonProcessingException {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
		Employee form = new Employee();
		List<Employee> employees = new ArrayList<Employee>();
		resp = restTemplate.exchange(ApplicationConstant.URL_EMPLOYEE + "getemployees", HttpMethod.GET, entity,
				String.class);
		System.out.println("resp:" + resp);
		String result = resp.getBody();
		ObjectMapper mapper = new ObjectMapper();
		if (resp.getStatusCode() != HttpStatus.OK) {
			try {
				model.addAttribute("employeelist", mapper.writeValueAsString(employees));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			if (result.contains("content")) {
				JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content").getAsJsonArray();
				System.out.println(contents);
				if (contents.size() > 0) {
					for (JsonElement jsonNode : contents) {
						Employee employee = new Employee();
						JsonObject object = jsonNode.getAsJsonObject();	
						employee.setFullName(object.get("fullName").getAsString());
						//employee.setCompanyId(object.get("companyId").getAsInt());
						employee.setEmployeeId(object.get("employeeId").getAsString());
						employee.setPersonalEmailId(object.get("personalEmailId").getAsString());
						employee.setOrganizationEmailId(object.get("organizationEmailId").getAsString());
						employee.setContactNumber(object.get("contactNumber").getAsString());
						employee.setAddress(object.get("address").getAsString());
						employee.setPincode(object.get("pincode").getAsString());
						employee.setState(object.get("state").getAsString());
						employee.setCity(object.get("city").getAsString());
						employee.setCountry(object.get("country").getAsString());
						//employee.setContractBased(object.get("contractBased").getAsString());
						employee.setDateOfBirth(object.get("dateOfBirth").getAsString());
						employee.setGender(object.get("gender").getAsString());
						employee.setHighestQualification(object.get("highestQualification").getAsString());
						employee.setBloodGroup(object.get("bloodGroup").getAsString());
						employee.setEmergencyContactNo(object.get("emergencyContactNo").getAsString());
						employee.setJoiningDate(object.get("joiningDate").getAsString());
						employee.setExperienceInMonth(object.get("experienceInMonth").getAsString());
						
						employee.setDepartmentId(object.get("departmentId").getAsInt());
						employee.setRoleId(object.get("roleId").getAsInt());
						employee.setPfAccount(object.get("pfAccount").getAsString());
						employee.setMedicalStatus(object.get("medicalStatus").getAsString());
						employees.add(employee); 
					}
				}
				model.addAttribute("employeelist", mapper.writeValueAsString(employees));

			} else {
				model.addAttribute("employeelist", mapper.writeValueAsString(employees));
			}
			model.addAttribute("myrole", form);
		}
		model.addAttribute("departments",Service.getDepartments());
		model.addAttribute("roles",Service.getRoles());
		
		return "employeemanagement";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public String deleteemployee(@RequestParam("employeeId") String employeeId) {
		ResponseEntity<String> resp = null;
		try {
			Employee employee = new Employee();
			employee.setEmployeeId(employeeId);
			String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(employee);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_EMPLOYEE + "delete", HttpMethod.POST, entity,
					String.class);

			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

		return "redirect:employeemanagement";
	}

	@RequestMapping(value = "/saveemployee", method = { RequestMethod.POST })
	public String saveemployee(@ModelAttribute("employee") Employee employee, HttpSession session) {
		ResponseEntity<String> resp = null;
		try {
			employee.setCompanyId(session.getAttribute("companyId") != null ? Integer.parseInt(session.getAttribute("companyId").toString()) : 0);
			System.out.println("employeeid:"+ employee.getEmployeeId());
			if(employee.getEmployeeId().equals("")) {
				employee.setEmployeeId("0");
			}
			System.out.println("employee: "+employee);
			String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(employee);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_EMPLOYEE + "register", HttpMethod.POST, entity,
					String.class);

			if (resp.getStatusCode() != HttpStatus.OK) {
				
				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EMPRuntimeException(resp.getBody().toString());
		}
		return "redirect:employeemanagement";
	}
}
