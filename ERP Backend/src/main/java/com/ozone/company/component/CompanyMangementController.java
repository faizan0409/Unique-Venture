package com.ozone.company.component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.hibernate.engine.transaction.spi.IsolationDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.ozone.application.utils.ApplicationConstant;
import com.ozone.application.utils.Utils;
import com.ozone.component.datamodel.Company;
import com.ozone.component.datamodel.Employee;
import com.ozone.notification.component.MailTemplate;
import com.ozone.response.component.model.ResponseModel;

/**
 * Author: Sumit Aher Date: 06-Mar-2021
 */

@Controller
@RequestMapping("/companies")
public class CompanyMangementController {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	private MailTemplate mailTemplate;


	// Register Company
	@PostMapping(value = "/register", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> add(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).add(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		System.out.println("request body: " + requestBody);

		Company company = new Company();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());

		String notificationMsg = "";
		boolean editProjectStatus = false;
		String defaultPassword = "";
		String message = "";
		String updatedStatus = "";
		try {
			System.out.println("id: " + requestBody.get("id"));
			Company existingComany = null;
			if (requestBody.get("id") != null) {
				existingComany = companyRepository.findByCompanyId(Integer.parseInt(requestBody.get("id")));
			}
			if (existingComany != null) {
				company = existingComany;
				company.setUpdatedBy(requestBody.get("updatedBy"));

				notificationMsg = " Company details updated for project " + company.getId();

				editProjectStatus = true;
				response.setStatus(HttpStatus.OK);
				response.setMessage("updated successfully");
				response.setContent(company);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
			} else {
				Company companyExistsWithEmail = companyRepository.getCompanyByEmail(requestBody.get("email"));
				if (companyExistsWithEmail != null) {
					response.setStatus(HttpStatus.OK);
					response.setMessage("Company already registed with entered email Id");
					response.setContent(company);
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
				}
				Company maxCompany = companyRepository.findMaxCompanyId();

				if (maxCompany == null) {
					maxCompany = new Company();
					maxCompany.setId(1);
				}

				int newCompanyId = maxCompany.getId();

				company.setName(requestBody.get("name"));
				company.setOwnerName(requestBody.get("ownerName"));
				company.setRegistrationDate(requestBody.get("registrationDate"));
				company.setGstNumber(requestBody.get("gstNumber"));
				company.setAddress(requestBody.get("address"));
				company.setEmail(requestBody.get("email"));
				company.setContactNumber(requestBody.get("contactNumber"));
				company.setSupportEmailId(requestBody.get("supportEmailId"));
				company.setSupportEmailPassword(requestBody.get("supportEmailPassword"));
				company.setSupportContactNumber(requestBody.get("supportContactNumber"));
				company.setCategory(requestBody.get("category"));
				company.setType(requestBody.get("type"));
				defaultPassword = ApplicationConstant.EMP_COMPANY + randomNumberGenerator();
				company.setPassword(passwordEncoder().encode(defaultPassword));
				company.setDeleteStatus(false);
				company.setCreatedBy(requestBody.get("createdBy"));
				companyRepository.save(company);
				
				message = ApplicationConstant.COMPANY_REGISTRATION_SUCCESSFULL;
				updatedStatus = ApplicationConstant.MAIL_CONTENT_FOR_REGISTER;
				String mailContent = mailTemplate.getHTMLTextForRegistration(company, defaultPassword, "Company",
						updatedStatus);

				if (mailContent != null || !mailContent.equals("")) {
					List<InternetAddress> emailIdList = new ArrayList<>();
					emailIdList.add(new InternetAddress(company.getEmail()));
					if (company.getSupportEmailId() != "") {
						emailIdList.add(new InternetAddress(company.getSupportEmailId()));
					}
					mailTemplate.createMailTemplateAndSend(mailContent, emailIdList, message);
				}

				response.setStatus(HttpStatus.OK);
				response.setMessage(message);
				response.setContent(company);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);

			}

		} catch (Exception e) {
			System.out.println("errror");
			e.printStackTrace();
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while registering employee");
			response.setContent("Error while registering employee");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

	}

	// Login For Company Admin
	@GetMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> login(@RequestParam("emailId") String organizationEmailId,
			@RequestParam("password") String password) {

		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).login(organizationEmailId, password));
		ResponseModel response = new ResponseModel();

		Company company = companyRepository.findByEmailIdCaseSensitive(organizationEmailId);

		if (company != null ) {
			if(!company.setOtpValidationStatus()) {
				response.setMessage(ApplicationConstant.OPT_NOT_VERIFIED);
				response.setStatus(HttpStatus.UNAUTHORIZED);
			} else if(company.isDeleteStatus()) {
				response.setMessage("Company is not active, Please contact Administrator");
				response.setStatus(HttpStatus.UNAUTHORIZED);
			}else if (passwordEncoder().matches(password, company.getPassword())) {

				response.setMessage(ApplicationConstant.COMPANY_LOGGED_IN_SUCCESSFULLY);
				response.setStatus(HttpStatus.OK);
				response.setContent(company);

			} else {
				response.setMessage(ApplicationConstant.WRONG_PASSWORD);
				response.setStatus(HttpStatus.UNAUTHORIZED);
			}
		}else {
			response.setMessage(ApplicationConstant.COMPANY_NOT_AVAILABLE_WITH_THIS_EMAIL_ID + organizationEmailId);
			response.setStatus(HttpStatus.UNAUTHORIZED);
		}

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/generateOtp",consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> regenerateRegistrationOtp(@RequestBody Map<String,String> requestBody){
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).regenerateRegistrationOtp(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		System.out.println("requestBody otp: "+ requestBody);
		Company company = new Company();
		try {
			if (companyRepository.getCompanyByEmail(requestBody.get("emailId")) == null) {
				throw new RuntimeException(
						MessageFormat.format("Company with emailId :{0} does not exist", requestBody.get("emailId")));
			}
			
			Company companyByEmail = companyRepository.getCompanyByEmail(requestBody.get("emailId"));
			generateRegistrationOtp(companyByEmail);
			company = companyRepository.save(companyByEmail);
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while sending an OTP");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("OTP sent successfully");
		response.setContent(company);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/validateotp",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> validateOtp(@RequestParam String emailId, @RequestParam String otp) {

		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).validateOtp(emailId, otp));
		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		
		Company company = new Company();		
		
		company = companyRepository.findByEmailAndOTP(emailId, otp);
		
		if(company!=null && !company.isDeleteStatus()) {
			
			company.setOtpValidationStatus(true);
			companyRepository.save(company);
			
			response.setMessage("OTP validated successfully");
			response.setStatus(HttpStatus.OK);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
		}else {
			
			company = companyRepository.getCompanyByEmail(emailId);
			
			if(company != null) {
				company.setOtpValidationStatus(false);
				companyRepository.save(company);
				
				response.setMessage("OTP validation failed, please enter valid OTP");
				response.setStatus(HttpStatus.UNAUTHORIZED);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.UNAUTHORIZED);
				
			}else {
				response.setMessage("Unable to find company account");
				response.setStatus(HttpStatus.NOT_FOUND);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.NOT_FOUND);
			}
			
		}	
	}

	private void generateRegistrationOtp(Company company){
		String registrationOtp = Utils.generateOTP(4);
		String messageText = Utils.templateRegistrationOtp(company.getEmail(),registrationOtp);
		
		String mailContent = mailTemplate.getHTMLTextForRegistration(company, messageText,
				"Company", "OTP" + "_" + registrationOtp);

		if (mailContent != null || !mailContent.equals("")) {
			List<InternetAddress> emailIdList = new ArrayList<>();
			try {
				emailIdList.add(new InternetAddress(company.getEmail()));
			} catch (AddressException e) {
				e.printStackTrace();
			}
			mailTemplate.createMailTemplateAndSend(mailContent, emailIdList, "EMP Registration OTP");
		}
		
		company.setOtp(registrationOtp);
		company.setOtpTimestamp(new java.sql.Time(new Date().getTime()));
		company.setOtpValidationStatus(false);
	}
	public String randomNumberGenerator() {
		String str = ApplicationConstant._0123456789;
		String randomNum = new String();
		Random rndm_method = new Random();

		for (int i = 1; i <= 4; i++)
			randomNum += str.charAt(rndm_method.nextInt(str.length()));

		return randomNum;
	}
	@GetMapping(value = "/getAllCompanies", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> getAllCompanies() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllCompanies());

		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		List<Company> companyList = new ArrayList<>();

		try {
			if (companyRepository.findAllCompanies() != null) {

				companyList = (List<Company>) companyRepository.findAllCompanies();
			}

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting company list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Company list");
		response.setStatus(HttpStatus.OK);

		if (!companyList.isEmpty()) {
			response.setContent(companyList);
		} else {
			response.setContent(null);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	@PostMapping(value = "/updateprofile", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> update(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).update(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		System.out.println("request body: " + requestBody);
		
		Company company = new Company();
	    company = companyRepository.getCompanyByEmail(requestBody.get("email")); 
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
				company.setName(requestBody.get("name"));
				company.setOwnerName(requestBody.get("ownerName"));
				company.setRegistrationDate(requestBody.get("registrationDate"));
				company.setGstNumber(requestBody.get("gstNumber"));
				company.setAddress(requestBody.get("address"));
				//company.setEmail(requestBody.get("email"));
				company.setContactNumber(requestBody.get("contactNumber"));
				company.setSupportEmailId(requestBody.get("supportEmailId"));
				company.setSupportEmailPassword(requestBody.get("supportEmailPassword"));
				company.setSupportContactNumber(requestBody.get("supportContactNumber"));
				company.setCategory(requestBody.get("category"));
				company.setType(requestBody.get("type"));
				
				companyRepository.save(company);
				String message = ApplicationConstant.COMPANY_UPDATE_SUCCESSFULL;
				
				response.setStatus(HttpStatus.OK);
				response.setMessage(message);
				response.setContent(company);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("errror");
			e.printStackTrace();
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while updating company");
			response.setContent("Error while updating company");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping(value = "/getProfile", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> get(@RequestParam("email") String email) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).get(email));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		System.out.println("request body: " + email);
		
		Company company=new Company();
		company = companyRepository.findByEmailIdCaseSensitive(email); 
		if (company != null) {
			response.setMessage("Company Profile Get Successful");
			response.setStatus(HttpStatus.OK);
			response.setContent(company);
		} else {
			response.setMessage(ApplicationConstant.COMPANY_NOT_AVAILABLE_WITH_THIS_EMAIL_ID  + email);
			response.setStatus(HttpStatus.UNAUTHORIZED);
		}

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	@PostMapping(value = "/changeStatus", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> change(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).change(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		System.out.println("request body: " + requestBody);
		
		Company company=new Company();
		company = companyRepository.getCompanyByEmailId(requestBody.get("email")); 
		System.out.println(company);
		if (company != null) {
			if(requestBody.get("deleteStatus").equalsIgnoreCase("true")) {
				company.setDeleteStatus(true);
			}else {
				company.setDeleteStatus(false);
			}
			companyRepository.save(company);
			response.setMessage("Company Status Change Successful");
			response.setStatus(HttpStatus.OK);
			response.setContent(company);
			
		} else {
			response.setMessage(ApplicationConstant.COMPANY_NOT_AVAILABLE_WITH_THIS_EMAIL_ID  + requestBody.get("emailId"));
			response.setStatus(HttpStatus.UNAUTHORIZED);
		}

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
