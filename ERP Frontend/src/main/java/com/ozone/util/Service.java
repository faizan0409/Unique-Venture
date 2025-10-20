package com.ozone.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ozone.model.Client;
import com.ozone.model.Department;
import com.ozone.model.Employee;
import com.ozone.model.Manager;
import com.ozone.model.Project;
import com.ozone.model.Role;

public class Service {

	public static List<Client> getClientList() throws JsonProcessingException {
		List<Client> clients = new ArrayList<Client>();
		try {
			ResponseEntity<String> resp = null;
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeader = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<String>(httpHeader);

			resp = restTemplate.exchange(ApplicationConstant.URL_CLIENT_PAGE + "getclients", HttpMethod.GET, entity,
					String.class);
			String result = resp.getBody();
			if (resp.getStatusCode() != HttpStatus.OK) {
				throw new EMPRuntimeException(resp.getBody().toString());
			} else {
				if (result.contains("content")) {
					JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content")
							.getAsJsonArray();
					System.out.println(contents);
					if (contents.size() > 0) {
						for (JsonElement jsonNode : contents) {
							Client client = new Client();
							JsonObject object = jsonNode.getAsJsonObject();
							client.setClientId(object.get("clientId").getAsString());
							client.setClientName(object.get("clientName").getAsString());
							System.out.println(client);

							clients.add(client);
						}
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println(clients.size());
		return clients;
	}

	public static List<Employee> getEmployeeList() throws JsonProcessingException {

		List<Employee> employees = new ArrayList<Employee>();
		try {
			ResponseEntity<String> resp = null;
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeader = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<String>(httpHeader);

			resp = restTemplate.exchange(ApplicationConstant.URL_EMPLOYEE + "getemployees", HttpMethod.GET, entity,
					String.class);
			System.out.println("resp:" + resp);
			String result = resp.getBody();

			if (resp.getStatusCode() != HttpStatus.OK)
				throw new EMPRuntimeException(resp.getBody().toString());

			else {
				if (result.contains("content")) {
					JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content")
							.getAsJsonArray();
					System.out.println(contents);
					if (contents.size() > 0) {
						for (JsonElement jsonNode : contents) {
							Employee employee = new Employee();
							JsonObject object = jsonNode.getAsJsonObject();
							employee.setFullName(object.get("fullName").getAsString());
							/*
							 * employee.setCompanyId(object.get("companyId").getAsInt());
							 */							employee.setEmployeeId(object.get("employeeId").getAsString());
							employee.setPersonalEmailId(object.get("personalEmailId").getAsString());
							employee.setOrganizationEmailId(object.get("organizationEmailId").getAsString());
							employee.setContactNumber(object.get("contactNumber").getAsString());
							employee.setAddress(object.get("address").getAsString());
							employee.setPincode(object.get("pincode").getAsString());
							employee.setState(object.get("state").getAsString());
							employee.setCity(object.get("city").getAsString());
							employee.setCountry(object.get("country").getAsString());
							employee.setContractBased(object.get("contractBased").getAsString());
							employee.setDateOfBirth(object.get("dateOfBirth").getAsString());
							employee.setGender(object.get("gender").getAsString());
							employee.setHighestQualification(object.get("highestQualification").getAsString());
							employee.setBloodGroup(object.get("bloodGroup").getAsString());
							employee.setEmergencyContactNo(object.get("emergencyContactNo").getAsString());
							employee.setJoiningDate(object.get("joiningDate").getAsString());
							employee.setExperienceInMonth(object.get("experienceInMonth").getAsString());
							//employee.setSkillSet(object.get("skillSet").getAsString());
							employee.setDepartmentId(object.get("departmentId").getAsInt());
							employee.setRoleId(object.get("roleId").getAsInt());
							employee.setPfAccount(object.get("pfAccount").getAsString());
							employee.setMedicalStatus(object.get("medicalStatus").getAsString());
							employees.add(employee);
						}
					}
				}

			}
		} catch (EMPRuntimeException e) {

			e.printStackTrace();
		}

		return employees;
	}

	public static List<Manager> getmanagerList() throws JsonProcessingException {

		List<Manager> managers = new ArrayList<Manager>();
		try {
			ResponseEntity<String> resp = null;
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeader = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<String>(httpHeader);

			resp = restTemplate.exchange(ApplicationConstant.URL_EMPLOYEE + "getmanager", HttpMethod.GET, entity,
					String.class);
			System.out.println("resp:" + resp);
			String result = resp.getBody();

			if (resp.getStatusCode() != HttpStatus.OK) {
				throw new EMPRuntimeException(resp.getBody().toString());
			} else {
				if (result.contains("content")) {
					JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content")
							.getAsJsonArray();
					System.out.println(contents);
					if (contents.size() > 0) {
						for (JsonElement jsonNode : contents) {
							Manager manager = new Manager();
							JsonObject object = jsonNode.getAsJsonObject();
							manager.setFullName(object.get("fullName").getAsString());
							// employee.setCompanyId(object.get("companyId").getAsInt());
							manager.setEmployeeId(object.get("employeeId").getAsString());

							managers.add(manager);
						}
					}
				}else {
					Manager manager = new Manager();
					managers.add(manager);
				}

			}
		} catch (EMPRuntimeException e) {
			e.printStackTrace();
		}
		System.out.println("Manager Size:" + managers.size());
		return managers;
	}

	public static List<Project> getProjects() throws JsonProcessingException {
		List<Project> projects = new ArrayList<Project>();
		try {
			ResponseEntity<String> resp = null;
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeader = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<String>(httpHeader);

			resp = restTemplate.exchange(ApplicationConstant.URL_PROJECT + "getprojects", HttpMethod.GET, entity,
					String.class);
			System.out.println("resp:" + resp);
			String result = resp.getBody();

			if (resp.getStatusCode() != HttpStatus.OK)
				throw new EMPRuntimeException(resp.getBody().toString());

			else {
				if (result.contains("content")) {
					JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content")
							.getAsJsonArray();
					System.out.println(contents);
					if (contents.size() > 0) {
						for (JsonElement jsonNode : contents) {
							Project project = new Project();
							JsonObject object = jsonNode.getAsJsonObject();
							project.setProjectTitle(object.get("projectTitle").getAsString());
							project.setProjectId(object.get("projectId").getAsString());

							projects.add(project);
						}
					}
				}

			}
		} catch (EMPRuntimeException e) {
			e.printStackTrace();
		}

		return projects;
	}
public static List<Department> getDepartments()throws JsonProcessingException{
		List<Department> departments = new ArrayList<Department>();
		try {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
				
		resp = restTemplate.exchange(ApplicationConstant.URL_DEPARTMENT_PAGE + "getAllDepartments", HttpMethod.GET,
				entity, String.class);
		System.out.println("resp:Department" + resp);
		String result = resp.getBody();
	
		if (resp.getStatusCode() != HttpStatus.OK) 
			throw new EMPRuntimeException(resp.getBody().toString());
			

		else {
			if (result.contains("content")) {
				JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content").getAsJsonArray();
				System.out.println(contents);
				if (contents.size() > 0) {
					for (JsonElement jsonNode : contents) {
						Department department = new Department();
						JsonObject object = jsonNode.getAsJsonObject();
						
						department.setName(object.get("name").getAsString());
						department.setDescription(object.get("description").getAsString());
						department.setDepartmentStatus(object.get("departmentStatus").getAsString());
						department.setCompanyId(object.get("companyId").getAsInt());
						department.setId(object.get("id").getAsInt());
						departments.add(department);
					}
				}
							}
			
		}} catch (EMPRuntimeException e) {
			e.printStackTrace();
		}
		
		return departments;
	}
	public static List<Role> getRoles()throws JsonProcessingException{
		List<Role> roles = new ArrayList<Role>();
		try {
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
				
		resp = restTemplate.exchange(ApplicationConstant.URL_ROLE_PAGE + "getAllRoles", HttpMethod.GET, entity,
				String.class);
		System.out.println("resp:Role" + resp);
		String result = resp.getBody();
	
		if (resp.getStatusCode() != HttpStatus.OK) 
			throw new EMPRuntimeException(resp.getBody().toString());
			

		else {
			if (result.contains("content")) {
				JsonArray contents = JsonParser.parseString(result).getAsJsonObject().get("content").getAsJsonArray();
				System.out.println(contents);
				if (contents.size() > 0) {
					for (JsonElement jsonNode : contents) {
						Role role=new Role();
						JsonObject object = jsonNode.getAsJsonObject();
						role.setId(object.get("id").getAsInt());
						role.setName(object.get("name").getAsString());
						role.setDescription(object.get("description").getAsString());
						role.setroleStatus(object.get("roleStatus").getAsString());
						role.setCompanyId(object.get("companyId").getAsInt());
						
						System.out.println("id: "+ object.get("id").getAsInt());
						System.out.println("role: "+ role);
						roles.add(role);
					
					}
				}
							}
			
		}} catch (EMPRuntimeException e) {
			e.printStackTrace();
		}
		System.out.println("role :::"+roles);
		return roles;
	}                          
}
