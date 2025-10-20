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
import com.ozone.model.Task;
import com.ozone.util.ApplicationConstant;
import com.ozone.util.EMPRuntimeException;
import com.ozone.util.JsonUtils;
import com.ozone.util.Service;

@Controller
public class TaskController {
	@RequestMapping("/taskmanagement")
	public String taskmanagement(Model model) throws JsonProcessingException {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
		Task form = new Task();
		List<Task> tasks = new ArrayList<Task>();
		resp = restTemplate.exchange(ApplicationConstant.URL_TASK + "gettasks", HttpMethod.GET,
				entity, String.class);
		System.out.println("resp:" + resp);
		String result = resp.getBody();
		ObjectMapper mapper = new ObjectMapper();
		if (resp.getStatusCode() != HttpStatus.OK) {
			try {
				model.addAttribute("tasklist", mapper.writeValueAsString(tasks));
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
					Task task = new Task();
					JsonObject object = jsonNode.getAsJsonObject();
					task.setTaskName(object.get("taskName").getAsString());
					task.setTaskDescription(object.get("taskDescription").getAsString());
					task.setProjectId(object.get("projectId").getAsString());
					task.setPriority(object.get("priority").getAsString());
					task.setStartDate(object.get("startDate").getAsString());
					task.setEndDate(object.get("endDate").getAsString());
					task.setTaskStatus(object.get("taskStatus").getAsString());
					task.setCompanyId(object.get("companyId").getAsString());
					tasks.add(task);
				}
			}
			model.addAttribute("tasklist", mapper.writeValueAsString(tasks));

		}
			else{
				model.addAttribute("tasklist", mapper.writeValueAsString(tasks));
			}
			model.addAttribute("mytask", form);
		}
		model.addAttribute("projects", Service.getProjects());
		return "taskmanagement";
	}

@RequestMapping(value = "/deletetask", method = { RequestMethod.POST })
public String deletetask(@RequestParam("taskName") String taskName) {
	ResponseEntity<String> resp = null;
	try {
		Task task = new Task();
		task.setTaskName(taskName);
		String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(task);
		HttpHeaders httpHeader = new HttpHeaders(); // HTTPHeader.createHeaders(username, password);
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
		RestTemplate restTemplate = new RestTemplate();

		resp = restTemplate.exchange(ApplicationConstant.URL_TASK + "deletetask", HttpMethod.POST, entity,
				String.class);

		if (resp.getStatusCode() != HttpStatus.OK) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

	} catch (Exception ex) {

		throw new EMPRuntimeException(resp.getBody().toString());
	}

	return "redirect:taskmanagement";
}

@RequestMapping(value = "/savetask", method = { RequestMethod.POST })
public String saveRole(@ModelAttribute("task") Task task, HttpSession session) {
	ResponseEntity<String> resp = null;
	try {
		//task.setCompanyId(session.getAttribute("companyId") != null ? Integer.parseInt(session.getAttribute("companyId").toString()) : 0);
		task.setCompanyId("19");
		System.out.println("task detail"+task);
		String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(task);
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
		RestTemplate restTemplate = new RestTemplate();

		resp = restTemplate.exchange(ApplicationConstant.URL_TASK + "savetask", HttpMethod.POST, entity,
				String.class);
		System.out.println("Task body:"+resp);
		if (resp.getStatusCode() != HttpStatus.OK) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

	} catch (Exception ex) {

		throw new EMPRuntimeException(resp.getBody().toString());
	}
	return "redirect:taskmanagement";
}
}


