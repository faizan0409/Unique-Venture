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
import com.ozone.util.ApplicationConstant;
import com.ozone.util.EMPRuntimeException;
import com.ozone.util.JsonUtils;

@Controller
public class ClientController {
	
	@RequestMapping("/clientmanagement")
	public String clientmanagement(Model model) throws JsonProcessingException {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
		Client form = new Client();
		List<Client> clients = new ArrayList<Client>();
		resp = restTemplate.exchange(ApplicationConstant.URL_CLIENT_PAGE + "getclients", HttpMethod.GET, entity,
				String.class);
		System.out.println("resp:" + resp);
		String result = resp.getBody();
		ObjectMapper mapper = new ObjectMapper();
		if (resp.getStatusCode() != HttpStatus.OK) {
			try {
				model.addAttribute("clientlist", mapper.writeValueAsString(clients));
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
						Client client = new Client();
						JsonObject object = jsonNode.getAsJsonObject();
						client.setClientId(object.get("clientId").getAsString());
						client.setClientName(object.get("clientName").getAsString());
						client.setCompanyName(object.get("companyName").getAsString());
						client.setAddress(object.get("address").getAsString());
						client.setContactNumber(object.get("contactNumber").getAsString());
						client.setEmailId(object.get("emailId").getAsString());
						client.setClientStatus(object.get("clientStatus").getAsString());

						clients.add(client);
					}
				}
				model.addAttribute("clientlist", mapper.writeValueAsString(clients));

			} else {
				model.addAttribute("clientlist", mapper.writeValueAsString(clients));
			}
			model.addAttribute("myclient", form);
		}
		return "clientmanagement";
	}

	@RequestMapping(value = "/deleteclient", method = { RequestMethod.POST })
	public String deleterole(@RequestParam("emailId") String emailId) {
		ResponseEntity<String> resp = null;
		try {
			Client client = new Client();
			client.setEmailId(emailId);
			String ClientJSON = JsonUtils.createGsonObjectForDateFormat().toJson(client);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(ClientJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_CLIENT_PAGE + "delete", HttpMethod.POST, entity,
					String.class);

			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

		return "redirect:clientmanagement";
	}

	@RequestMapping(value = "/saveclient", method = { RequestMethod.POST })
	public String saveClient(@ModelAttribute("client") Client client) {
		ResponseEntity<String> resp = null;
		try {
			if(client.getClientId() == "") {
				client.setClientId("0");
			}
			System.out.println("client: "+ client);
			String ClientJSON = JsonUtils.createGsonObjectForDateFormat().toJson(client);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(ClientJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_CLIENT_PAGE + "save", HttpMethod.POST, entity,
					String.class);

			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EMPRuntimeException(resp.getBody().toString());
		}
		return "redirect:clientmanagement";
	}
}
