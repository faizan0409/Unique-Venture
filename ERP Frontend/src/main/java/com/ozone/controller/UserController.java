package com.ozone.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.ozone.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ozone.config.Messages;
import com.ozone.model.Company;
import com.ozone.model.Department;
import com.ozone.model.RolesForm;
import com.ozone.model.Otp;
import com.ozone.model.Role;
import com.ozone.model.VerifyOtp;
import com.ozone.util.ApplicationConstant;
import com.ozone.util.EMPRuntimeException;
import com.ozone.util.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	@Autowired
	Messages messages;

	@RequestMapping("/")
	public String home(HttpSession session) {
		System.out.println("userName: " + session.getAttribute("username"));
		if (session.getAttribute("username") == null) {
			return "index";
		} else {
			return "companydashboard";
		}
	}

	// Company Profile.....
	@RequestMapping("/companyprofile")
	public String companyprofile(Model model,HttpSession session) throws JsonProcessingException {
		String emailId = session.getAttribute("emailId").toString();
		System.out.println("emailId: "+emailId);
		ResponseEntity<String> resp = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
		Company form = new Company();
		List<Company> companys = new ArrayList<Company>();
		try{
			resp = restTemplate.exchange(
					ApplicationConstant.URL_COMPANYPROFILE + "getProfile?email="+emailId+"",
					HttpMethod.GET, entity, String.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
		System.out.println("resp: " + resp);
		String result = resp.getBody();
		ObjectMapper mapper = new ObjectMapper();
		Company company = new Company();
		if (resp.getStatusCode() != HttpStatus.OK) {
			try {

				model.addAttribute("companylist", mapper.writeValueAsString(companys));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			if (result.contains("content")) {
				JsonObject content = JsonParser.parseString(result).getAsJsonObject().get("content").getAsJsonObject();
				System.out.println(content);
				if (content != null) {
					
					JsonObject object = content;
					company.setName(object.get("name").getAsString());
					company.setOwnerName(object.get("ownerName").getAsString());
					company.setRegistrationDate(object.get("registrationDate").getAsString());
					company.setGstNumber(object.get("gstNumber").getAsString());
					company.setAddress(object.get("address").getAsString());
					company.setEmail(object.get("email").getAsString());
					company.setContactNumber(object.get("contactNumber").getAsString());
					company.setWebsite(object.get("website") != null ? object.get("website").getAsString() : "");
					company.setSupportContactNumber(object.get("supportContactNumber").getAsString());
					company.setSupportEmailId(object.get("supportEmailId").getAsString());
					company.setSupportEmailPassword(object.get("supportEmailPassword").getAsString());
					company.setCategory(object.get("category").getAsString());
					company.setType(object.get("type").getAsString());
					company.setPassword(object.get("password").getAsString());
					System.out.println(company);
					companys.add(company);
					model.addAttribute("companylist",mapper.writeValueAsString(companys));
				}
			} else {
				model.addAttribute("companylist", mapper.writeValueAsString(companys));
			}
			model.addAttribute("myrole", form);
		}
		return "companyprofile";
	}

	//save company profile
	@RequestMapping(value = "/updatecompanyprofile", method = { RequestMethod.POST })
	public String updateprofile(@ModelAttribute("companyform") Company company) {
		ResponseEntity<String> resp = null;
		try {
			//System.out.println("inside methid");
			System.out.println("company: "+ company);
			String companyJSON = JsonUtils.createGsonObjectForDateFormat().toJson(company);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(companyJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();

			resp = restTemplate.exchange(ApplicationConstant.URL_COMPANYPROFILE + "updateprofile",HttpMethod.POST, entity, String.class);
			if (resp.getStatusCode() != HttpStatus.OK) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EMPRuntimeException(resp.getBody().toString());
		}
		return "redirect:companyprofile";
	}
	
	// User Profile.....
	@RequestMapping("/userprofile")

  public String	userprofile(Model model)throws JsonProcessingException
	{
		ResponseEntity<String> resp = null; RestTemplate restTemplate = new RestTemplate(); 
		HttpHeaders httpHeader = new HttpHeaders(); 
		 HttpEntity<String> entity = new HttpEntity<String>( httpHeader);
		 Employee form = new Employee();
		 List<Employee> employees = new ArrayList<Employee>();
		 resp = restTemplate.exchange(ApplicationConstant. URL_USERPROFILE + "getemployeeProfile?organizationEmailId=faizan.ozone@gmail.com", HttpMethod.GET, entity, String.class);
		 System.out.println("resp: " +resp);
		 String result = resp.getBody();
		 ObjectMapper mapper = new ObjectMapper();
		 if(resp.getStatusCode() != HttpStatus.OK) {
		 try {
			 model.addAttribute("employeelist",mapper.writeValueAsString(employees));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		 }
		    else
           { 
		    	if (result.contains("content")) {
					JsonObject content = JsonParser.parseString(result).getAsJsonObject().get("content").getAsJsonObject();
					System.out.println(content);
					if (content != null) {
						JsonElement jsonNode = content;
            Employee employee =new Employee(); 
            JsonObject object = jsonNode.getAsJsonObject();
		    employee.setFullName(object.get("fullName").getAsString());
		    employee.setDepartmentId(object.get("departmentId").getAsInt());
		    employee.setDateOfBirth(object.get("dateOfBirth").getAsString());
		    employee.setGender(object.get("gender").getAsString());
		    employee.setHighestQualification(object.get("highestQualification").getAsString());
		    employee.setMedicalStatus(object.get("medicalStatus").getAsString());
		    employee.setBloodGroup(object.get("bloodGroup").getAsString());
		    employee.setCity(object.get("city").getAsString());
		    employee.setPincode(object.get("pincode").getAsString());
		    employee.setState(object.get("state").getAsString());
		    employee.setCountry(object.get("country").getAsString());
		    employee.setAddress(object.get("address").getAsString());
		    employee.setPfAccount(object.get("pfAccount").getAsString());
		    employee.setContactNumber(object.get("contactNumber").getAsString());
		    employee.setOrganizationEmailId(object.get("organizationEmailId").getAsString());
		    employee.setPersonalEmailId(object.get("personalEmailId").getAsString());
		    employee.setEmergencyContactNo(object.get("emergencyContactNo").getAsString());
		    employee.setExperienceInMonth(object.get("experienceInMonth").getAsString());
		    employee.setRoleId(object.get("roleId").getAsInt());
		    System.out.println(employee);
		    employees.add(employee);
		    
			}
			model.addAttribute("employeelist",mapper.writeValueAsString(employees));
		    	}
			else{
				model.addAttribute("employeelist", mapper.writeValueAsString(employees));
			}
			model.addAttribute("myrole", form);
		}
		return "userprofile";
	}
	@RequestMapping(value = "/updateEmployeeProfile", method = { RequestMethod.POST })
		public String updateEmployeeProfile(@ModelAttribute("employee") Employee employee) {
			ResponseEntity<String> resp = null;
			try {
				String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(employee);
				HttpHeaders httpHeader = new HttpHeaders();
				httpHeader.setContentType(MediaType.APPLICATION_JSON);
				httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

				HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
				RestTemplate restTemplate = new RestTemplate();

				resp = restTemplate.exchange(ApplicationConstant.URL_EMPLOYEE + "updateEmployeeProfile", HttpMethod.POST, entity,
						String.class);

				if (resp.getStatusCode() != HttpStatus.OK) {

					throw new EMPRuntimeException(resp.getBody().toString());
				}

			} catch (Exception ex) {

				throw new EMPRuntimeException(resp.getBody().toString());
			}
			return "redirect:userprofile";
		
	}

	// Login
	@RequestMapping("/login")
	public String login(@ModelAttribute("roleForm") RolesForm login, HttpSession session, Model model) {

		System.out.println("login msg: " + login.getMsg());
		String msg1 = login.getMsg();
		model.addAttribute("msg", msg1);

		return "login";

	}

	@RequestMapping("/logout")
	public String logout(@ModelAttribute("roleForm") RolesForm login, HttpSession session, Model model) {

		session.invalidate();

		return "login";

	}

	@RequestMapping("/forgotpassword")
	public String forgotpassword() {
		return "forgotpassword";
	}

	@RequestMapping("/registration")
	public String companyRegistration() {

		return "registration";
	}

	@RequestMapping("/registerotp")
	public String registerotp(HttpServletRequest request, HttpServletResponse response, Model model,
			HttpSession session, RedirectAttributes redirectAttributes, @ModelAttribute VerifyOtp verify) {
		try {

			Company company = new Company();
			company.setName(request.getParameter("cname").trim());
			company.setOwnerName(request.getParameter("owner").trim());
			company.setRegistrationDate(request.getParameter("date").trim());
			company.setGstNumber(request.getParameter("gst") != null || request.getParameter("gst") != ""
					? request.getParameter("gst").trim() : null);
			company.setAddress(request.getParameter("address").trim());
			company.setEmail(request.getParameter("email").trim());
			company.setContactNumber(request.getParameter("contact").trim());
			company.setWebsite(request.getParameter("website") != null || request.getParameter("website") != ""
					? request.getParameter("website").trim() : null);
			company.setSupportContactNumber(
					request.getParameter("s_contact") != null || request.getParameter("s_contact") != ""
							? request.getParameter("s_contact").trim() : null);
			company.setSupportEmailId(request.getParameter("s_email") != null || request.getParameter("s_email") != ""
					? request.getParameter("s_email").trim() : null);
			company.setSupportEmailPassword(
					request.getParameter("s_email_password") != null || request.getParameter("s_email_password") != ""
							? request.getParameter("s_email_password").trim() : null);
			company.setCategory(request.getParameter("company").trim());
			company.setType(request.getParameter("companytype").trim());
			company.setCreatedBy("Admin");
			company.setId(0);
			String notificationJSON = JsonUtils.createGsonObjectForDateFormat().toJson(company);
			HttpHeaders httpHeader = new HttpHeaders(); // HTTPHeader.createHeaders(username,
														// password);
			httpHeader.setContentType(MediaType.APPLICATION_JSON);
			httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>(notificationJSON, httpHeader);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> resp = null;

			resp = restTemplate.exchange(ApplicationConstant.URL + "register", HttpMethod.POST, entity, String.class);

			String result = resp.getBody();
			String message = JsonParser.parseString(result).getAsJsonObject().get("message").getAsString();
			if (resp.getStatusCode() == HttpStatus.ALREADY_REPORTED) {
				LOG.error("Company already registed with entered email Id");
				model.addAttribute("exist", message);
				return "registration";
			} else if (resp.getStatusCode() != HttpStatus.OK) {
				throw new EMPRuntimeException(resp.getBody().toString());

			} else {
				System.out.println("Company registered successfully ");
				try {
					Otp otpval = new Otp();
					otpval.setEmailId(company.getEmail().trim());
					String otpJSON = JsonUtils.createGsonObjectForDateFormat().toJson(otpval);
					HttpHeaders httpHeader1 = new HttpHeaders();
					httpHeader1.setContentType(MediaType.APPLICATION_JSON);
					httpHeader1.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

					HttpEntity<String> entity1 = new HttpEntity<String>(otpJSON, httpHeader1);
					RestTemplate restTemplate1 = new RestTemplate();
					ResponseEntity<String> resp1 = null;
					System.out.println("calling otp method");
					resp1 = restTemplate1.exchange(ApplicationConstant.URL + "generateOtp", HttpMethod.POST, entity1,
							String.class);

					System.out.println(resp1.getStatusCode());
					if (resp1.getStatusCode() != HttpStatus.OK) {
						throw new EMPRuntimeException(resp1.getBody().toString());

					} else {
						String body = resp1.getBody();
						JsonObject content = JsonParser.parseString(body).getAsJsonObject().get("content")
								.getAsJsonObject();
						String emailId = content.get("email").getAsString();
						String otp = content.get("otp").getAsString();
						model.addAttribute("emailId", emailId);
						model.addAttribute("otp", otp);
						session.setAttribute("emailId", emailId);
						/*
						 * verify.setEmailId(emailId); verify.setOtp(otp);
						 * redirectAttributes.addFlashAttribute("verify",
						 * verify);
						 */
						return "registerotp";
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("**************************");
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			return "registration";
		}

		return "registration";
	}

	@RequestMapping("/otp")
	public String otp() {
		return "otp";
	}

	@RequestMapping("/validatepassword")
	public String validatepassword() {

		return "validatepassword";
	}

	@RequestMapping(value = "/productdashboard")
	public String productdashboard(@ModelAttribute RolesForm roleForm, HttpServletRequest request, HttpSession session,
			Model model, RedirectAttributes redirectAttrs) {

		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		try {
			if ("admin".equals(username) && "admin".equals(password)) {
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				return "productdashboard";
			} else if ("admin".equals(username) && !"admin".equals(password)) {
				model.addAttribute("msg", "Invalid password");
				roleForm.setMsg("Invalid password");
				redirectAttrs.addFlashAttribute("roleForm", roleForm);
				return "redirect:login";
			} else if (!"admin".equals(username) && "admin".equals(password)) {
				model.addAttribute("msg", "Invalid Username");
				roleForm.setMsg("Invalid Username");
				redirectAttrs.addFlashAttribute("roleForm", roleForm);
				return "redirect:login";
			} else {
				model.addAttribute("msg", "Invalid Login Credential");
				roleForm.setMsg("Invalid Login Credential");
				redirectAttrs.addFlashAttribute("roleForm", roleForm);
				return "redirect:login";
			}
		} catch (Exception e) {
			LOG.error("Invalid Login Credential");
			model.addAttribute("msg", "Invalid Login Credential");
			roleForm.setMsg("Invalid Login Credential");
			redirectAttrs.addFlashAttribute("roleForm", roleForm);
			session.setAttribute("username", null);
			session.setAttribute("password", null);
			return "redirect:login";
		}

	}

	@RequestMapping("/companydashboard")
	public String dashboard(@ModelAttribute RolesForm roleForm, Model model, HttpServletRequest request,
			HttpSession session, RedirectAttributes redirectAttrs) {

		try {
			if (session.getAttribute("username") == null) {
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> resp = null;
				String username = request.getParameter("email").trim();
				String password = request.getParameter("password").trim();
				String usertype = request.getParameter("usertype");
				HttpHeaders httpHeader = new HttpHeaders();
				HttpEntity<String> entity = new HttpEntity<String>(httpHeader);
				System.out.println("usertype: " + usertype);
				if (usertype.equals("A")) {// admin user
					resp = restTemplate.exchange(
							ApplicationConstant.URL + "login?emailId=" + username + "&password=" + password,
							HttpMethod.GET, entity, String.class);
					System.out.println(resp);
					String body = resp.getBody();
					String message = JsonParser.parseString(body).getAsJsonObject().get("message").getAsString();
					String status = JsonParser.parseString(body).getAsJsonObject().get("status").getAsString();
					JsonObject content = JsonParser.parseString(body).getAsJsonObject().get("content")
							.getAsJsonObject();

					if (resp.getStatusCode() == HttpStatus.OK && status.equalsIgnoreCase("OK")) {
						session.setAttribute("username", username);
						session.setAttribute("password", password);
						session.setAttribute("usertype", "A");
						session.setAttribute("companyId", content.get("id").getAsInt());
						session.setAttribute("emailId", content.get("email").getAsString());
						
						model.addAttribute("msg", message);
						return "companydashboard";
					}

					else {
						LOG.error("Invalid Login Credential");
						model.addAttribute("msg", "Invalid Login Credential");
						roleForm.setMsg(message);
						redirectAttrs.addFlashAttribute("roleForm", roleForm);
						session.setAttribute("username", null);
						session.setAttribute("password", null);
						session.setAttribute("usertype", null);
						return "redirect:login";
					}

				} else if (usertype.equals("E")) {
					try {
						// subuser
						resp = restTemplate.exchange(
								ApplicationConstant.URL_EMPLOYEE + "login?emailId=" + username + "&password=" + password,
								HttpMethod.GET, entity, String.class);
						String body = resp.getBody();
						String message = JsonParser.parseString(body).getAsJsonObject().get("message").getAsString();
						System.out.println("body: "+ body);
						String status = JsonParser.parseString(body).getAsJsonObject().get("status").getAsString();
						JsonObject content = JsonParser.parseString(body).getAsJsonObject().get("content")
								.getAsJsonObject();
						if (resp.getStatusCode() == HttpStatus.OK) {
							session.setAttribute("username", username);
							session.setAttribute("password", password);
							session.setAttribute("usertype", "E");
							session.setAttribute("companyId", content.get("id").getAsInt());
							session.setAttribute("emailId", content.get("organizationEmailId").getAsString());
							model.addAttribute("msg", message);
							roleForm.setMsg(message);
							redirectAttrs.addFlashAttribute("roleForm", roleForm);
							return "companydashboard";
						} else {
							LOG.error("Invalid Login Credential");
							model.addAttribute("msg", "Invalid Login Credential");
							roleForm.setMsg(message);
							redirectAttrs.addFlashAttribute("roleForm", roleForm);
							session.setAttribute("username", null);
							session.setAttribute("password", null);
							session.setAttribute("usertype", null);
							return "redirect:login";
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
					return "companydashboard";
				} else {
					LOG.error("Invalid Login Credential");
					model.addAttribute("msg", "Invalid Login Credential");
					roleForm.setMsg("Something went wrong");
					redirectAttrs.addFlashAttribute("roleForm", roleForm);
					session.setAttribute("username", null);
					session.setAttribute("password", null);
					return "redirect:login";
				}
			} else {
				return "companydashboard";
			}
		} catch (Exception e) {
			LOG.error("Invalid Login Credential");
			model.addAttribute("msg", "Someting went wrong");
			roleForm.setMsg("Someting went wrong");
			redirectAttrs.addFlashAttribute("roleForm", roleForm);
			return "redirect:login";
		}

	}

	@RequestMapping("/leavecalendar")
	public String leavecalendar() {
		return "leavecalendar";
	}

	@RequestMapping("/announecement")
	public String announecement() {
		return "announecement";
	}

	@RequestMapping("/menumanagement")
	public String menumanagement() {
		return "menumanagement";
	}


	@RequestMapping("/announcement")
	public String announcement() {
		return "announcementtab";
	}

	@RequestMapping("/announcementtab")
	public String announcementtab() {
		return "announcementtab";
	}
}
