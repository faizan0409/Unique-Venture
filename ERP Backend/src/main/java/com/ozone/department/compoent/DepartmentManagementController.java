package com.ozone.department.compoent;

import java.util.ArrayList;
import java.util.Collection;
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
import com.ozone.component.datamodel.Company;
import com.ozone.component.datamodel.Department;
import com.ozone.component.datamodel.Employee;
import com.ozone.response.component.model.ResponseModel;
import com.ozone.user.component.EmployeeRepository;

/**
 * Author: Sumit Aher Date: Mar 19, 2021
 */

@Controller
@RequestMapping("/department")
public class DepartmentManagementController {
	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	// getAllDepartments
	@GetMapping(value = "/getAllDepartments", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> getAllDepartments() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllDepartments());

		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		List<Department> departmentList = new ArrayList<>();

		try {
			if (departmentRepository.finddepartments() != null) {

				departmentList = (List<Department>) departmentRepository.finddepartments();
			}

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting project list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Department list");
		response.setStatus(HttpStatus.OK);

		if (!departmentList.isEmpty()) {
			response.setContent(departmentList);
		} else {
			response.setContent(null);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/savedepartment", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> add(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).add(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Department department = new Department();
		String notificationMsg = "";
		boolean editDepartmentStatus = false;

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {

			Department existingDepartment = departmentRepository.findByDepartmentName(requestBody.get("name"));

			if (existingDepartment != null) {
				department = existingDepartment;
				department.setUpdatedBy(requestBody.get("updatedBy"));
				notificationMsg = " Project details updated for project " + department.getId();

				editDepartmentStatus = true;
			} else {

				// set company id
				Company company = companyRepository.findByCompanyId(Integer.parseInt(requestBody.get("companyId")));

				if (company != null) {
					department.setCompanyId(company.getId());
				} else {
					response.setStatus(HttpStatus.EXPECTATION_FAILED);
					response.setMessage("Not able to assign company to department");
					response.setContent("Not able to assign company to department");
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
							HttpStatus.EXPECTATION_FAILED);
				}

				company.setCreatedBy(requestBody.get("createdBy"));
			}
			department.setName(requestBody.get("name"));
			department.setDescription(requestBody.get("description"));
			if (requestBody.get("departmentStatus").equals("Y")) {
				department.setDepartmentStatus(true);
			} else {
				department.setDepartmentStatus(false);
			}

			departmentRepository.save(department);
			response.setStatus(HttpStatus.OK);
			if (editDepartmentStatus) {
				response.setMessage("Department updated successfully");
			} else {
				response.setMessage("Department created successfully");
			}

			response.setContent(department);
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while creating department");
			Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/deletedepartment", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> deleteTask(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteTask(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Department department = departmentRepository.findByDepartmentName(requestBody.get("name"));

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {

			Collection<Employee> employeeCollection = (Collection<Employee>) employeeRepository
					.findEmployeeHavingDept(department.getId());

			if (employeeCollection.size() != 0) {

				if (department != null) {
					response.setStatus(HttpStatus.OK);
					response.setMessage("department can not be deleted as it is assigned to employee");
					response.setContent(department);
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
				}

			} else {
				department.setDeleteStatus(true);
				department.setDepartmentStatus(false);
				departmentRepository.save(department);
				response.setStatus(HttpStatus.OK);
				response.setMessage("Department deleted successfully");
				response.setContent(department);
			}

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while deleting department");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

	
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
}
