package com.ozone.salary.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.google.common.base.Throwables;
import com.ozone.company.component.CompanyRepository;
import com.ozone.component.datamodel.Salary;
import com.ozone.response.component.model.ResponseModel;
import com.ozone.user.component.EmployeeRepository;

@Controller
@RequestMapping("/salary")
public class SalaryManagementController {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SalaryRepository salaryRepository;

	@PostMapping(value = "/savesalary", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> add(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).add(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Salary salary = new Salary();
		boolean editSalaryStatus = false;
		Salary existingEmployee = salaryRepository.findByEmployeeId(requestBody.get("employeeId"));
		System.out.println("existingEmployee: " + existingEmployee);
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
			if (existingEmployee != null) {
				salary = existingEmployee;
				salary.setUpdatedBy(requestBody.get("updatedBy"));
				editSalaryStatus = true;
				salary.setEmployeeId(existingEmployee.getEmployeeId());
			} else {
				salary.setEmployeeId(requestBody.get("employeeId"));
			}
			salary.setCompanyId(Integer.parseInt(requestBody.get("companyId")));
			salary.setCtc(requestBody.get("ctc"));
			salary.setNetSalary(requestBody.get("netSalary"));
			salary.setBasicSalary(requestBody.get("basicSalary"));
			salary.setHouseRentAllowance(requestBody.get("houseRentAllowance"));
			salary.setSpecialBenefits(requestBody.get("specialBenefits"));
			salary.setMonthlyBonus(requestBody.get("monthlyBonus"));
			salary.setOtherCompensation(requestBody.get("otherCompensation"));
			salary.setPf(requestBody.get("pf"));
			salary.setGrossSalary(requestBody.get("grossSalary"));
			salary.setDeleteStatus(false);
			salaryRepository.save(salary);
			System.out.println("Salary resp::" + salary);
			response.setStatus(HttpStatus.OK);
			if (editSalaryStatus) {
				response.setMessage("Salary updated successfully");
			} else {
				response.setMessage("Salary created successfully");
			}

			response.setContent(salary);
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while creating salary");
			Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/deletesalary", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> delete(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).delete(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
			System.out.println(requestBody.get("employeeId"));
			Salary salary = salaryRepository.findByEmployeeId(requestBody.get("employeeId"));
			System.out.println("salary: "+ salary);
			if (salary != null) {
				salary.setDeleteStatus(true);
				salaryRepository.save(salary);
				response.setStatus(HttpStatus.OK);
				response.setMessage("Salary deleted successfully");
				response.setContent(salary);
			}
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while deleting department");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllEmployeeSalary", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> getAllEmployeeSalary() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllEmployeeSalary());

		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		List<Salary> employeeSalaryList = new ArrayList<>();

		try {
			if (salaryRepository.findemployeesalary() != null) {

				employeeSalaryList = (List<Salary>) salaryRepository.findemployeesalary();
			}

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting Employee Salary list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Employee Salary list");
		response.setStatus(HttpStatus.OK);

		if (!employeeSalaryList.isEmpty()) {
			response.setContent(employeeSalaryList);
		} else {
			response.setContent(null);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

}
