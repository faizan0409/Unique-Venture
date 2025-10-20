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
import com.ozone.model.Department;
import com.ozone.util.ApplicationConstant;
import com.ozone.util.EMPRuntimeException;
import com.ozone.util.JsonUtils;

@Controller
public class DepartmentController {
	@RequestMapping("/departmentmanagement")
	public String departmentmanagement(Model model) throws JsonProcessingException {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
		Department form = new Department();
		List<Department> departments = new ArrayList<Department>();
		resp = restTemplate.exchange(ApplicationConstant.URL_DEPARTMENT_PAGE + "getAllDepartments", HttpMethod.GET,
				entity, String.class);
		System.out.println("resp:" + resp);
		String result = resp.getBody();
		ObjectMapper mapper = new ObjectMapper();
		if (resp.getStatusCode() != HttpStatus.OK) {
			try {
				model.addAttribute("departmentlist", mapper.writeValueAsString(departments));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else{
			if (result.contains("content")) {
				JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content").getAsJsonArray();
				System.out.println(contents);
				if (contents.size() > 0) {
					for (JsonElement jsonNode : contents) {
					Department department = new Department();
					JsonObject object = jsonNode.getAsJsonObject();
					department.setId(object.get("id").getAsInt());
					department.setName(object.get("name").getAsString());
					department.setDescription(object.get("description").getAsString());
					department.setDepartmentStatus(object.get("departmentStatus").getAsString());
					department.setCompanyId(object.get("companyId").getAsInt());
					departments.add(department);
				}
			}
			model.addAttribute("departmentlist", mapper.writeValueAsString(departments));

		}
			else{
				model.addAttribute("departmentlist", mapper.writeValueAsString(departments));
			}
			model.addAttribute("myrole", form);
		}
		return "departmentmanagement";
	}

@RequestMapping(value = "/deletedepartment", method = { RequestMethod.POST })
public String deletedepartment(@RequestParam("name") String name) {
	ResponseEntity<String> resp = null;
	try {
		Department department = new Department();
		department.setName(name);
		String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(department);
		HttpHeaders httpHeader = new HttpHeaders(); // HTTPHeader.createHeaders(username, password);
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
		RestTemplate restTemplate = new RestTemplate();

		resp = restTemplate.exchange(ApplicationConstant.URL_DEPARTMENT_PAGE + "deletedepartment", HttpMethod.POST, entity,
				String.class);

		if (resp.getStatusCode() != HttpStatus.OK) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

	} catch (Exception ex) {

		throw new EMPRuntimeException(resp.getBody().toString());
	}

	return "redirect:departmentmanagement";
}

@RequestMapping(value = "/savedepartment", method = { RequestMethod.POST })
public String saveRole(@ModelAttribute("department") Department department, HttpSession session) {
	ResponseEntity<String> resp = null;
	try {
		department.setCompanyId(session.getAttribute("companyId") != null ? Integer.parseInt(session.getAttribute("companyId").toString()) : 0);
		String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(department);
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
		RestTemplate restTemplate = new RestTemplate();

		resp = restTemplate.exchange(ApplicationConstant.URL_DEPARTMENT_PAGE + "savedepartment", HttpMethod.POST, entity,
				String.class);

		if (resp.getStatusCode() != HttpStatus.OK) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

	} catch (Exception ex) {
		ex.printStackTrace();
		throw new EMPRuntimeException(resp.getBody().toString());
	}
	return "redirect:departmentmanagement";
}
}

