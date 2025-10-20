package com.ozone.user.component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.support.Repositories;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import com.ozone.application.utils.ApplicationConstant;
import com.ozone.application.utils.NumberUtils;
import com.ozone.component.datamodel.Employee;
import com.ozone.component.datamodel.ProjectMap;
import com.ozone.component.datamodel.Roles;
import com.ozone.component.datamodel.Timelog;
import com.ozone.notification.component.MailTemplate;
import com.ozone.project.component.ProjectMapRepository;
import com.ozone.response.component.model.ResponseModel;
import com.ozone.roles.compoent.RoleRepository;
import com.ozone.timelog.component.TimelogRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeManagementController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProjectMapRepository projectMapRepository;
	
	@Autowired
	private TimelogRepository timelogRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private MailTemplate mailTemplate;
	
	@GetMapping(value = "/login",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> login(@RequestParam("emailId") String organizationEmailId,@RequestParam("password") String password) {

		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).login(organizationEmailId, password));
		ResponseModel response = new ResponseModel();
		System.out.println("organizationEmailId: "+ organizationEmailId);
		try {
			Employee employee = employeeRepository.findByEmailIdCaseSensitive(organizationEmailId);
			System.out.println("employee: "+employee);
			if (employee != null && !employee.isDeleteStatus()) {
				
				/*
				 * java.sql.Date todayDate = new
				 * java.sql.Date(Calendar.getInstance().getTime().getTime()); SimpleDateFormat
				 * dateFormat = new SimpleDateFormat("dd-MM-yyyy"); String strDate =
				 * dateFormat.format(todayDate);
				 */
				if (passwordEncoder().matches(password, employee.getPassword())) {
	
					/*
					 * Timelog timelog =
					 * timelogRepository.findByEmployeeId(employee.getEmployeeId(),strDate);
					 * 
					 * if(timelog == null){ timelog = new Timelog();
					 * timelog.setLoginTime(java.time.LocalTime.now()); timelog.setDay(strDate);
					 * timelog.setWorkHrs(0.0); timelog.setApprovedHrs(0.0);
					 * timelog.setEmployeeId(employee.getEmployeeId());
					 * timelogRepository.save(timelog); }
					 */
					
					response.setMessage(ApplicationConstant.EMPLOYEE_LOGGED_IN_SUCCESSFULLY);
					response.setStatus(HttpStatus.OK);
					response.setContent(employee);
	
				} else {
					response.setMessage(ApplicationConstant.WRONG_PASSWORD);
					response.setStatus(HttpStatus.UNAUTHORIZED);
					response.setContent(employee);
				}
			} else {
				response.setMessage(ApplicationConstant.EMPLOYEE_NOT_AVAILABLE_WITH_THIS_EMAIL_ID + organizationEmailId);
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setContent(employee);
			}
	
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/register", consumes = { MediaType.APPLICATION_JSON_VALUE   }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> register(@RequestBody Map<String, String> requestBody){
		    final LinkBuilder selfLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).register(requestBody));

		String updatedStatus = "";
		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		
		Employee employee = new Employee();
		Employee createdEmployee = null;
		Employee existingEmployee = null;
		String message = "";
		String defualtPassword  = "";
		boolean isEmployeeUpdated = false;
		System.out.println("requestBody: "+ requestBody);
		try {
			
			if(!requestBody.get("employeeId").equals("0")) {
				existingEmployee = employeeRepository.findByEmployeeId(requestBody.get("employeeId"));
				
			}else {
				Employee alreadyExistingEmployee = employeeRepository.findByEmailId(requestBody.get("organizationEmailId"));
				
				if(alreadyExistingEmployee != null) {
					response.setStatus(HttpStatus.ALREADY_REPORTED);
					response.setMessage(ApplicationConstant.EMPLOYEE_ALREADY_EXISTS_WITH_EMAIL_ID+requestBody.get("organizationEmailId"));
					response.setContent(ApplicationConstant.EMPLOYEE_ALREADY_EXISTS_WITH_EMAIL_ID+requestBody.get("organizationEmailId"));
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.ALREADY_REPORTED);
				}
			}
			
			if (existingEmployee != null) {
				//throw new AuthenticationException("Email already exists");
				
				employee = existingEmployee;
				//employee.setUpdatedDatetime(new java.sql.Date(new Date().getTime()));
				employee.setUpdatedBy(requestBody.get("createdBy"));
				
				message = ApplicationConstant.EMPLOYEE_DETAILS_UPDATED_SUCCESSFULLY;
				updatedStatus = ApplicationConstant.MAIL_CONTENT_FOR_UPADTE;
				isEmployeeUpdated = true;
			}else {
				
				Employee maxEmployee = employeeRepository.findMaxEmployeeId();
				
				if(maxEmployee == null) {
					maxEmployee = new Employee();
					maxEmployee.setEmployeeId(ApplicationConstant._000);
				}
				String newEmployeeId = maxEmployee.getEmployeeId().substring(1);
				employee.setEmployeeId(ApplicationConstant.E+NumberUtils.padZeroes(Integer.parseInt(newEmployeeId)+1));
				
				defualtPassword = ApplicationConstant.OZONE_EMPLOYEE+randomNumberGenerator();
				employee.setPassword(passwordEncoder().encode(defualtPassword));
				employee.setCreatedBy(requestBody.get("createdBy"));
							
				message = ApplicationConstant.EMPLOYEE_REGISTRATION_SUCCESSFULL;
				updatedStatus = ApplicationConstant.MAIL_CONTENT_FOR_REGISTER;
			}

			employee.setFullName(requestBody.get("fullName"));
			employee.setOrganizationEmailId(requestBody.get("organizationEmailId"));
			employee.setPersonalEmailId(requestBody.get("personalEmailId"));
			employee.setContactNumber(requestBody.get("contactNumber"));
			employee.setAddress(requestBody.get("address"));
			employee.setPincode(requestBody.get("pincode"));
			employee.setState(requestBody.get("state"));
			employee.setCity(requestBody.get("city"));
			employee.setCountry(requestBody.get("country"));
			employee.setDateOfBirth(requestBody.get("dateOfBirth"));
			employee.setGender(requestBody.get("gender"));
			employee.setHighestQualification(requestBody.get("highestQualification"));
			employee.setBloodGroup(requestBody.get("bloodGroup"));
			employee.setEmergencyContactNo(requestBody.get("emergencyContactNo"));
			employee.setJoiningDate(requestBody.get("joiningDate"));
			employee.setExperienceInMonth(requestBody.get("experienceInMonth"));
			employee.setSkillSet(requestBody.get("skillSet"));
			employee.setDepartmentId(Integer.parseInt(requestBody.get("departmentId")));
			employee.setRoleId(Integer.parseInt(requestBody.get("roleId")));
			employee.setPfAccount(requestBody.get("pfAccount"));
			employee.setMedicalStatus(requestBody.get("medicalStatus"));
			employee.setContractBased(requestBody.get("contractBased"));
			employee.setState(requestBody.get("state"));
			employee.setDeleteStatus(false);
						
			createdEmployee = employeeRepository.save(employee);
			if(!isEmployeeUpdated) {
				String mailContent = mailTemplate.getHTMLTextForRegistration(employee, defualtPassword,"Employee", updatedStatus);

				if (mailContent != null || !mailContent.equals("")) {
					List<InternetAddress> emailIdList = new ArrayList<>();
					emailIdList.add(new InternetAddress(employee.getOrganizationEmailId()));
					emailIdList.add(new InternetAddress(employee.getPersonalEmailId()));
					mailTemplate.createMailTemplateAndSend(mailContent, emailIdList, message);
				}
			}
			 
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while registering employee");
			response.setContent("Error while registering employee");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage(message);
		response.setContent(createdEmployee);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/getemployees",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readEmployeeInformation() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).readEmployeeInformation());
		
		ResponseModel response = new ResponseModel();
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		List<Employee> employeeList = new ArrayList<>();
		
		try {
			if (employeeRepository.findEmployees() != null) {
				
				employeeList = (List<Employee>)employeeRepository.findEmployees();
			}else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage(ApplicationConstant.EMPLOYEES_NOT_AVAILABEL);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting employee list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage(ApplicationConstant.EMPLOYEE_INFORMATION);
		response.setStatus(HttpStatus.OK);
		
		if(!employeeList.isEmpty()) {
			response.setContent(employeeList);
		}else {
			response.setContent(null);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getmanager",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readManagerInformation() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).readManagerInformation());
		
		ResponseModel response = new ResponseModel();
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		List<Employee> employeeList = new ArrayList<>();
		
		try {
			Roles role = roleRepository.findByRoleName("Manager");
			System.out.println("role list:"+role);
			if (role != null) {
				System.out.println(role);
				Iterable<Employee> employees =  employeeRepository.findEmployeeHavingRole(role.getId());
				response.setStatus(HttpStatus.OK);
				response.setMessage("Success");
				response.setContent(employees);
				}else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage(ApplicationConstant.EMPLOYEES_NOT_AVAILABEL_HAVING_ROLE_AS_MANAGER);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting manager list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}
		

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getemployeebyid",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readEmployeeUsingEmployeeId(@RequestParam("searchtxt") String employeeId) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).readEmployeeUsingEmployeeId(employeeId));
		
		ResponseModel response = new ResponseModel();
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		Collection<Employee> employeeList = new ArrayList<>();
		
		try {

			if (!((Collection<Employee>)employeeRepository.findEmployeesByEmployeeId(employeeId)).isEmpty()) {
				
				employeeList = (Collection<Employee>)employeeRepository.findEmployeesByEmployeeId(employeeId);
				
			}else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage(ApplicationConstant.EMPLOYEE_NOT_AVAILABEL);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting employee list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage(ApplicationConstant.EMPLOYEE_LIST);
		response.setStatus(HttpStatus.OK);
		
		if(!employeeList.isEmpty()) {
			response.setContent(employeeList);
		}else {
			response.setContent(null);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/validateemail",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> validateEmail(@RequestParam("emailId") String organizationEmailId) {

		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).validateEmail(organizationEmailId));
		
		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		
		try {
			Employee employee = employeeRepository.findByEmailIdCaseSensitive(organizationEmailId);
			String updatedStatus = "";
			String message = "";

			if (employee != null && !employee.isDeleteStatus()) {

				updatedStatus = ApplicationConstant.EMAIL_VALIDATION_EMPLOYEE;

				message = ApplicationConstant.EMAIL_VALIDATION_EMPLOYEE;

				String mailContent = mailTemplate.getHTMLTextForRegistration(employee, "", "Employee", updatedStatus);

				if (mailContent != null || !mailContent.equals("")) {
					List<InternetAddress> emailIdList = new ArrayList<>();

					emailIdList.add(new InternetAddress(employee.getOrganizationEmailId()));
					mailTemplate.createMailTemplateAndSend(mailContent, emailIdList, message);
				}

				response.setMessage(ApplicationConstant.EMPLOYEE_AVAILABLE_WITH_THIS_EMAIL_ID + organizationEmailId);
				response.setStatus(HttpStatus.OK);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
			} else {
				response.setMessage(
						ApplicationConstant.EMPLOYEE_NOT_AVAILABLE_WITH_THIS_EMAIL_ID + organizationEmailId);
				response.setStatus(HttpStatus.UNAUTHORIZED);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
						HttpStatus.UNAUTHORIZED);
			}
		} catch (AddressException ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while validating email id");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping(value = "/resetpassword",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> resetpassword(@RequestBody Map<String,String> requestBody){
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass())
				.resetpassword(requestBody));

		String updatedStatus = "";
		String message = "";
		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		Employee employee = employeeRepository.findByEmailIdCaseSensitive(requestBody.get("organizationEmailId"));
		Employee updatedEmployee = null;
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		try {
			
			if(employee!=null && !employee.isDeleteStatus()) {
				employee.setPassword(passwordEncoder().encode(requestBody.get("password")));
				//employee.setUpdatedDatetime(new java.sql.Date(new Date().getTime()));
				employee.setUpdatedBy(requestBody.get("updatedBy"));
				
				updatedEmployee = employeeRepository.save(employee);
				
				updatedStatus = ApplicationConstant.MAIL_CONTENT_FOR_RESETPASSWORD;
				
				message = ApplicationConstant.PASSWORD_RESET_SUCCESSFULLY;
				
				String mailContent = mailTemplate.getHTMLTextForRegistration(employee, requestBody.get("password"),
						"Employee",updatedStatus);
				
				List<InternetAddress> emailIdList = new ArrayList<>();
				emailIdList.add(new InternetAddress(employee.getOrganizationEmailId()));
				mailTemplate.createMailTemplateAndSend(mailContent, emailIdList, message);
			}
		
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while resetting password");
		}
		
		response.setStatus(HttpStatus.OK);
		response.setMessage(updatedEmployee.toString());
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/logout",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> logout(@RequestBody Map<String,String> requestBody){
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass())
				.logout(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		
		Employee employee = employeeRepository.findByEmailIdCaseSensitive(requestBody.get("organizationEmailId"));

		try {
			
			if(employee!=null) {
				
				java.sql.Date todayDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
				String strDate = dateFormat.format(todayDate);  
				
				Timelog timelog = timelogRepository.findByEmployeeId(employee.getEmployeeId(),strDate);
				
				if(timelog != null) {
					timelog.setLogoutTime(java.time.LocalTime.now());
					timelogRepository.save(timelog);
				}else {
					response.setStatus(HttpStatus.NO_CONTENT);
					response.setMessage(ApplicationConstant.UNABLE_TO_GET_EMPLOYEE_LOGIN_ENTRY);
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.NO_CONTENT);
				}
			}else {
				response.setStatus(HttpStatus.NO_CONTENT);
				response.setMessage(ApplicationConstant.UNABLE_TO_GET_EMPLOYEE_DETAILS);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while logging out");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}

		response.setMessage(ApplicationConstant.EMPLOYEE_LOGGED_OUT_SUCCESSFULLY);
		response.setContent(ApplicationConstant.EMPLOYEE_LOGGED_OUT_SUCCESSFULLY);
		response.setStatus(HttpStatus.OK);
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/delete",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> delete(@RequestBody Map<String,String> requestBody){
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass())
				.delete(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		System.out.println("requestBody: "+ requestBody);
		Employee employee = employeeRepository.findByEmployeeId(requestBody.get("employeeId"));
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		try {
			
			Collection<ProjectMap> projectMapCollection = (Collection<ProjectMap>)projectMapRepository.findByEmployeeId(employee.getEmployeeId());
			
			if(projectMapCollection.size()==0) {
				if(employee != null) {
					employee.setDeleteStatus(true);
					employeeRepository.save(employee);
					
				}else {
					response.setStatus(HttpStatus.OK);
					response.setMessage(ApplicationConstant.UNABLE_TO_GET_EMPLOYEE_DETAILS);
					response.setContent(ApplicationConstant.UNABLE_TO_GET_EMPLOYEE_DETAILS);
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
				}
			}else {
				response.setStatus(HttpStatus.OK);
				response.setMessage(ApplicationConstant.PROJECT_ASSIGNED_TO_EMPLOYEE);
				response.setContent(ApplicationConstant.PROJECT_ASSIGNED_TO_EMPLOYEE);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
			}
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while deleting employee");
			response.setContent("Error while deleting employee");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}

		response.setMessage(ApplicationConstant.EMPLOYEE_DELETED_SUCCESSFULLY);
		response.setContent(ApplicationConstant.EMPLOYEE_DELETED_SUCCESSFULLY);
		response.setStatus(HttpStatus.OK);

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getroles",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readRoles(@RequestParam ("roleType") String roleType) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).readRoles(roleType));
		
		ResponseModel response = new ResponseModel();
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		List<Roles> roleListBasedOnCategory = new ArrayList<>();
		
		try {
			if (roleRepository.findByRoleName(roleType) != null) {
				
				roleListBasedOnCategory = (List<Roles>)roleRepository.findByRoleName(roleType);
			}else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage(ApplicationConstant.ROLES_NOT_AVAILABEL);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting requested role");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage(ApplicationConstant.ROLE_INFORMATION_DEPENDING_ON_ROLE_TYPE);
		response.setStatus(HttpStatus.OK);
		
		if(!roleListBasedOnCategory.isEmpty()) {
			response.setContent(roleListBasedOnCategory);
		}else {
			response.setContent(null);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	@PostMapping(value = "/updateEmployeeProfile", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> update(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).update(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		System.out.println("request body: " + requestBody);
		
		Employee employee=new Employee();
		employee = employeeRepository.findByEmailId(requestBody.get("organizationEmailId").toString()); 
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
			employee.setFullName(requestBody.get("fullName").toString());
			employee.setPersonalEmailId(requestBody.get("personalEmailId").toString());
			employee.setContactNumber(requestBody.get("contactNumber").toString());
			employee.setAddress(requestBody.get("address").toString());
			employee.setPincode(requestBody.get("pincode").toString());
			employee.setCity(requestBody.get("city").toString());
			employee.setState(requestBody.get("state").toString());
			employee.setCountry(requestBody.get("country").toString());
			employee.setDateOfBirth(requestBody.get("dateOfBirth").toString());
			employee.setGender(requestBody.get("gender").toString());
			employee.setHighestQualification(requestBody.get("highestQualification").toString());
			employee.setBloodGroup(requestBody.get("bloodGroup").toString());
			employee.setEmergencyContactNo(requestBody.get("emergencyContactNo").toString());
			employee.setJoiningDate(requestBody.get("joiningDate").toString());
			employee.setExperienceInMonth(requestBody.get("experienceInMonth").toString());
			employee.setSkillSet(requestBody.get("skillSet").toString());
			employee.setDepartmentId(parseInt(requestBody.get("departmentId").toString()));
			employee.setRoleId(parseInt(requestBody.get("roleId").toString()));
			employee.setPfAccount(requestBody.get("pfAccount").toString());
			employee.setMedicalStatus(requestBody.get("medicalStatus").toString());
			employee.setContractBased(requestBody.get("contractBased").toString());
			
			employeeRepository.save(employee);
			String message = ApplicationConstant.EMPLOYEE_UPDATE_SUCCESSFULL;
			
			response.setStatus(HttpStatus.OK);
			response.setMessage(message);
			response.setContent(employee);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	
		} catch (Exception e) {
			System.out.println("errror");
			e.printStackTrace();
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while updating Employee");
			response.setContent("Error while updating Employee");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	private int parseInt(String string) {
		// TODO Auto-generated method stub
		return 0;
	}


	@GetMapping(value = "/getemployeeProfile", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> get(@RequestParam("organizationEmailId") String organizationEmailId) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).get(organizationEmailId));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		System.out.println("request body: " + organizationEmailId);
		
		Employee employee=new Employee();
		employee = employeeRepository.findByEmailIdCaseSensitive(organizationEmailId); 
		if (employee != null) {
			response.setMessage("Employee Profile Get Successful");
			response.setStatus(HttpStatus.OK);
			response.setContent(employee);
		} else {
			response.setMessage(ApplicationConstant.EMPLOYEE_NOT_AVAILABLE_WITH_THIS_EMAIL_ID  + organizationEmailId);
			response.setStatus(HttpStatus.UNAUTHORIZED);
		}

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	/**
	 * randomNumberGenerator
	 * @return
	 */
	public String randomNumberGenerator() {
		String str = ApplicationConstant._0123456789;
		String randomNum = new String();
		Random rndm_method = new Random();

		for (int i = 1; i <= 4; i++)
			randomNum += str.charAt(rndm_method.nextInt(str.length()));
		
		return randomNum;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
