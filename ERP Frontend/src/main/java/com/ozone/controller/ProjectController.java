package com.ozone.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.ozone.model.Client;
import com.ozone.model.Project;
import com.ozone.model.Project;
import com.ozone.util.ApplicationConstant;
import com.ozone.util.EMPRuntimeException;
import com.ozone.util.JsonUtils;
import com.ozone.util.Service;

@Controller
public class ProjectController {

	@RequestMapping("/projectmanagement")
	public String projectmanagement(Model model) throws JsonProcessingException {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
		Project form = new Project();
		List<Project> projects = new ArrayList<Project>();
		resp = restTemplate.exchange(ApplicationConstant.URL_PROJECT + "getprojects", HttpMethod.GET, entity,
				String.class);
		System.out.println("resp:" + resp);
		String result = resp.getBody();
		ObjectMapper mapper = new ObjectMapper();
		if (resp.getStatusCode() != HttpStatus.OK) {
			try {
				model.addAttribute("projectlist", mapper.writeValueAsString(projects));
			} catch (JsonProcessingException e) {
			
				e.printStackTrace();
			}
		}

		else {
			if (result.contains("content")) {
				JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content").getAsJsonArray();
				System.out.println(contents);
				if (contents.size() > 0) {
					for (JsonElement jsonNode : contents) {
						Project project = new Project();
						JsonObject object = jsonNode.getAsJsonObject();
						project.setProjectTitle(object.get("projectTitle").getAsString());
						project.setProjectDescription(object.get("projectDescription").getAsString());
						project.setProjectStartDate(object.get("projectStartDate").getAsString());
						project.setProjectEndDate(object.get("projectEndDate").getAsString());
						project.setProjectType(object.get("projectType").getAsString());
						project.setProjectId(object.get("projectId").getAsString());
						project.setManagerId(object.get("managerId").getAsString());
						project.setClientId(object.get("clientId").getAsString());
						project.setProjectStatus(object.get("projectStatus").getAsString());

						projects.add(project);
					}
				}
				model.addAttribute("projectlist", mapper.writeValueAsString(projects));
			
			} else {
				model.addAttribute("projectlist", mapper.writeValueAsString(projects));
			}
			model.addAttribute("myproject", form);
			
		}
		model.addAttribute("clients", Service.getClientList());
		model.addAttribute("managers", Service.getmanagerList());
		return "projectmanagement";
	}
@RequestMapping(value = "/deleteproject", method = { RequestMethod.POST })
public String deleterole(@RequestParam("projectId") String projectId) {
	ResponseEntity<String> resp = null;
	try {
		Project project = new Project();
		project.setProjectId(projectId);
		String ProjectJSON = JsonUtils.createGsonObjectForDateFormat().toJson(project);
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(ProjectJSON, httpHeader);
		RestTemplate restTemplate = new RestTemplate();

		resp = restTemplate.exchange(ApplicationConstant.URL_PROJECT + "delete", HttpMethod.POST, entity,
				String.class);

		if (resp.getStatusCode() != HttpStatus.OK) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

	} catch (Exception ex) {
		ex.printStackTrace();
		throw new EMPRuntimeException(resp.getBody().toString());
	}

	return "redirect:projectmanagement";
}

@RequestMapping(value = "/saveproject", method = { RequestMethod.POST })
public String saveClient(@ModelAttribute("project") Project project) {
	ResponseEntity<String> resp = null;
	try {
		
		System.out.println("project: "+ project);
		String ProjectJSON = JsonUtils.createGsonObjectForDateFormat().toJson(project);
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(ProjectJSON, httpHeader);
		RestTemplate restTemplate = new RestTemplate();

		resp = restTemplate.exchange(ApplicationConstant.URL_PROJECT + "saveproject", HttpMethod.POST, entity,
				String.class);
		System.out.println(resp);
		if (resp.getStatusCode() != HttpStatus.OK) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

	} catch (Exception ex) {
		ex.printStackTrace();
		throw new EMPRuntimeException(resp.getBody().toString());
	}
	return "redirect:projectmanagement";
}
}

