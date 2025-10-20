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
import com.ozone.model.Salary;
import com.ozone.util.ApplicationConstant;
import com.ozone.util.EMPRuntimeException;
import com.ozone.util.JsonUtils;
import com.ozone.util.Service;

@Controller
public class SalaryController {
	@RequestMapping("/salarymanagement")
	public String salarymanagement(Model model) throws JsonProcessingException {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
		Salary form = new Salary();
		List<Salary> salarys = new ArrayList<Salary>();
		resp = restTemplate.exchange(ApplicationConstant.URL_SALARY + "getAllEmployeeSalary", HttpMethod.GET, entity,
				String.class);
		System.out.println("resp:Salary" + resp);
		String result = resp.getBody();
		ObjectMapper mapper = new ObjectMapper();
		if (resp.getStatusCode() != HttpStatus.OK) {
			try {
				model.addAttribute("salarylist", mapper.writeValueAsString(salarys));
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
						Salary salary = new Salary();
						JsonObject object = jsonNode.getAsJsonObject();
						salary.setEmployeeId(object.get("employeeId").getAsString());
						salary.setCompanyId(object.get("companyId").getAsInt());
						salary.setCtc(object.get("ctc").getAsString());
						salary.setBasicSalary(object.get("netSalary").getAsString());
						salary.setBasicSalary(object.get("basicSalary").getAsString());
						salary.setHouseRentAllowance(object.get("houseRentAllowance").getAsString());
						salary.setSpecialBenefits(object.get("specialBenefits").getAsString());
						salary.setMonthlyBonus(object.get("monthlyBonus").getAsString());
						salary.setOtherCompensation(object.get("otherCompensation").getAsString());
						salary.setNetSalary(object.get("netSalary").getAsString());
						salary.setPf(object.get("pf").getAsString());
						salarys.add(salary);
					}
				}
				model.addAttribute("salarylist", mapper.writeValueAsString(salarys));

			} else {
				model.addAttribute("salarylist", mapper.writeValueAsString(salarys));
			}
			model.addAttribute("mysalary", form);
		}
		model.addAttribute("employees", Service.getEmployeeList());

		return "salarymanagement";
	}

	@RequestMapping(value = "/deletesalary", method = { RequestMethod.POST })
	public String deleteemployee(@RequestParam("employeeId") String employeeId) {
		ResponseEntity<String> resp = null;
		try {
			System.out.println("Employee Id:"+employeeId);
			Salary salary = new Salary();
			salary.setEmployeeId(employeeId);
			String salaryJSON = JsonUtils.createGsonObjectForDateFormat().toJson(salary);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(salaryJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_SALARY + "deletesalary", HttpMethod.POST, entity,
					String.class);

			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {

			throw new EMPRuntimeException(resp.getBody().toString());
		}

		return "redirect:salarymanagement";
	}

	@RequestMapping(value = "/savesalary", method = { RequestMethod.POST })
	public String saveemployee(@ModelAttribute("salary") Salary salary, HttpSession session) {
		ResponseEntity<String> resp = null;
		try {
			salary.setCompanyId(session.getAttribute("companyId") != null ? Integer.parseInt(session.getAttribute("companyId").toString()) : 0);
			String salaryJSON = JsonUtils.createGsonObjectForDateFormat().toJson(salary);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(salaryJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_SALARY + "savesalary", HttpMethod.POST, entity,
					String.class);

			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EMPRuntimeException(resp.getBody().toString());
		}
		return "redirect:salarymanagement";
	}
}
