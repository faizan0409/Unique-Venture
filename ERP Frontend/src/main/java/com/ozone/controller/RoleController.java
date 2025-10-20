//Created By: Siddhi Suryawanshi APR 01, 2021
package com.ozone.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.ozone.model.Role;
import com.ozone.util.ApplicationConstant;
import com.ozone.util.EMPRuntimeException;
import com.ozone.util.JsonUtils;

@Controller
public class RoleController {

	@RequestMapping("/rolemanagement")
	public String rolemanagement(Model model) throws JsonProcessingException {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
		Role form = new Role();
		List<Role> roles = new ArrayList<Role>();
		resp = restTemplate.exchange(ApplicationConstant.URL_ROLE_PAGE + "getAllRoles", HttpMethod.GET, entity,
				String.class);
		System.out.println("resp:" + resp);
		String result = resp.getBody();
		ObjectMapper mapper = new ObjectMapper();
		if (resp.getStatusCode() != HttpStatus.OK) {

			try {
				model.addAttribute("rolelist", mapper.writeValueAsString(roles));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		} else {
			if (result.contains("content")) {
				JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content").getAsJsonArray();
				System.out.println(contents);
				if (contents.size() > 0) {
					for (JsonElement jsonNode : contents) {
						Role role = new Role();
						JsonObject object = jsonNode.getAsJsonObject();
						role.setId(object.get("id").getAsInt());
						role.setName(object.get("name").getAsString());
						role.setDescription(object.get("description").getAsString());
						role.setroleStatus(object.get("roleStatus").getAsString());
						role.setCompanyId(object.get("companyId").getAsInt());
						roles.add(role);
					}
				}
				model.addAttribute("rolelist", mapper.writeValueAsString(roles));
			} else {
				model.addAttribute("rolelist", mapper.writeValueAsString(roles));
			}
			model.addAttribute("myrole", form);
		}
		return "rolemanagement";
	}

	@RequestMapping(value = "/deleterole", method = { RequestMethod.POST })
	public String deleterole(@RequestParam("name") String name) {
		ResponseEntity<String> resp = null;
		try {
			Role role = new Role();
			role.setName(name);
			String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(role);
			HttpHeaders httpHeader = new HttpHeaders(); // HTTPHeader.createHeaders(username, password);
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_ROLE_PAGE + "deleterole", HttpMethod.POST, entity,
					String.class);

			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

		return "redirect:rolemanagement";
	}

	@RequestMapping(value = "/saverole", method = { RequestMethod.POST })
	public String saveRole(@ModelAttribute("role") Role role, HttpSession session) {
		ResponseEntity<String> resp = null;
		try {
			System.out.println(role);
			role.setCompanyId(session.getAttribute("companyId") != null ? Integer.parseInt(session.getAttribute("companyId").toString()) : 0);
			String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(role);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_ROLE_PAGE + "saverole", HttpMethod.POST, entity,
					String.class);

			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EMPRuntimeException(resp.getBody().toString());
		}
		return "redirect:rolemanagement";
	}
}
