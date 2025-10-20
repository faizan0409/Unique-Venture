package com.ozone.roles.compoent;

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
import com.ozone.component.datamodel.Employee;
import com.ozone.component.datamodel.Roles;
import com.ozone.response.component.model.ResponseModel;
import com.ozone.user.component.EmployeeRepository;

/**
 * Author: Sumit Aher Date: Mar 20, 2021
 */

@Controller
@RequestMapping("/role")
public class RolesManagementController {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	// getAllRoles
	@GetMapping(value = "/getAllRoles", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> getAllRoles() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllRoles());

		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		List<Roles> roleList = new ArrayList<>();

		try {
			if (roleRepository.findroles() != null) {

				roleList = (List<Roles>) roleRepository.findroles();
			}

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting Roles list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Roles list");
		response.setStatus(HttpStatus.OK);

		if (!roleList.isEmpty()) {
			response.setContent(roleList);
		} else {
			response.setContent(null);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/saverole", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })

	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> add(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).add(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Roles role = new Roles();
		String notificationMsg = "";
		boolean editDepartmentStatus = false;

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {

			Roles existingRole = roleRepository.findByRoleName(requestBody.get("name"));

			if (existingRole != null) {
				role = existingRole;
				role.setUpdatedBy(requestBody.get("updatedBy"));
				notificationMsg = " Project details updated for project " + role.getId();

				editDepartmentStatus = true;
			} else {

				// set company id
				Company company = companyRepository.findByCompanyId(Integer.parseInt(requestBody.get("companyId")));

				if (company != null) {
					role.setCompanyId(company.getId());
				} else {
					response.setStatus(HttpStatus.EXPECTATION_FAILED);
					response.setMessage("Not able to assign company to role");
					response.setContent("Not able to assign company to role");
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
							HttpStatus.EXPECTATION_FAILED);
				}

				company.setCreatedBy(requestBody.get("createdBy"));
			}
			role.setName(requestBody.get("name"));
			role.setDescription(requestBody.get("description"));
			if (requestBody.get("roleStatus").equals("Y")) {
				role.setRoleStatus(true);
			} else {
				role.setRoleStatus(false);
			}

			roleRepository.save(role);
			response.setStatus(HttpStatus.OK);
			if (editDepartmentStatus) {
				response.setMessage("Role updated successfully");
			} else {
				response.setMessage("Role created successfully");
			}

			response.setContent(role);
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while creating role");
			Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/deleterole", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> deleteRole(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteRole(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Roles role = roleRepository.findByRoleName(requestBody.get("name"));

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {

			Collection<Employee> employeeCollection = (Collection<Employee>) employeeRepository
					.findEmployeeHavingDept(role.getId());

			if (employeeCollection.size() != 0) {

				if (role != null) {
					response.setStatus(HttpStatus.OK);
					response.setMessage("role can not be deleted as it is assigned to employee");
					response.setContent(role);
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
				}

			} else {
				role.setDeleteStatus(true);
				role.setRoleStatus(false);
				roleRepository.save(role);
				response.setStatus(HttpStatus.OK);
				response.setMessage("Role deleted successfully");
				response.setContent(role);
			}

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while deleting role");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
}
