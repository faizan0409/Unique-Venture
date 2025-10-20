
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
import com.ozone.model.Team;
import com.ozone.util.ApplicationConstant;
import com.ozone.util.EMPRuntimeException;
import com.ozone.util.JsonUtils;

@Controller
public class TeamController {

	@RequestMapping("/teammanagement")
	public String rolemanagement(Model model) throws JsonProcessingException {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
		Team form = new Team();
		List<Team> teams = new ArrayList<Team>();
		resp = restTemplate.exchange(ApplicationConstant.URL_TEAM + "getAllTeams", HttpMethod.GET, entity,
				String.class);
		System.out.println("resp:" + resp);
		String result = resp.getBody();
		ObjectMapper mapper = new ObjectMapper();
		if (resp.getStatusCode() != HttpStatus.OK) {

			try {
				model.addAttribute("teamlist", mapper.writeValueAsString(teams));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		} else {
			if (result.contains("content")) {
				JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content").getAsJsonArray();
				System.out.println(contents);
				if (contents.size() > 0) {
					for (JsonElement jsonNode : contents) {
						Team team = new Team();
						JsonObject object = jsonNode.getAsJsonObject();
						team.setId(object.get("id").getAsInt());
						team.setTeamName(object.get("teamName").getAsString());
						team.setDescription(object.get("description").getAsString());
						team.setTeamStatus(object.get("teamStatus").getAsString());
						team.setCompanyId(object.get("companyId").getAsInt());
						teams.add(team);
					}
				}
				model.addAttribute("teamlist", mapper.writeValueAsString(teams));
			} else {
				model.addAttribute("teamlist", mapper.writeValueAsString(teams));
			}
			model.addAttribute("myTeam", form);
		}
		return "teammanagement";
	}

	@RequestMapping(value = "/deleteteam", method = { RequestMethod.POST })
	public String deleterole(@RequestParam("name") String name) {
		ResponseEntity<String> resp = null;
		try {
			Team team = new Team();
			team.setTeamName(name);
			String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(team);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_TEAM + "deleteteam", HttpMethod.POST, entity,
					String.class);

			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

		return "redirect:teammanagement";
	}

	@RequestMapping(value = "/saveteam", method = { RequestMethod.POST })
	public String saveTeam(@ModelAttribute("team") Team team, HttpSession session) {
		ResponseEntity<String> resp = null;
		try {
			team.setCompanyId(session.getAttribute("companyId") != null ? Integer.parseInt(session.getAttribute("companyId").toString()) : 0);
			String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(team);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_TEAM + "saveteam", HttpMethod.POST, entity,
					String.class);

			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EMPRuntimeException(resp.getBody().toString());
		}
		return "redirect:teammanagement";
	}
}
