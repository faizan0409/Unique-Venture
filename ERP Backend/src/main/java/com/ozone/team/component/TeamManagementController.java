package com.ozone.team.component;

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
import com.ozone.component.datamodel.Team;
import com.ozone.response.component.model.ResponseModel;
import com.ozone.team.component.TeamRepository;
import com.ozone.user.component.EmployeeRepository;

@Controller
@RequestMapping("/team")
public class TeamManagementController {

	@Autowired
	TeamRepository TeamRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;


	// getAllTeams
	@GetMapping(value = "/getAllTeams", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> getAllTeams() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllTeams());

		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		List<Team> teamList = new ArrayList<>();

		try {
			if (TeamRepository.findteams() != null) {

				teamList = (List<Team>) TeamRepository.findteams();
			}

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting Team list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Team list");
		response.setStatus(HttpStatus.OK);

		if (!teamList.isEmpty()) {
			response.setContent(teamList);
		} else {
			response.setContent(null);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/saveteam", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> add(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).add(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Team team=new Team();
		String notificationMsg = "";
		boolean editTeamStatus = false;

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {

			Team existingTeam = TeamRepository.findByTeamName(requestBody.get("teamName"));

			if (existingTeam != null) {
				team = existingTeam;
				team.setUpdatedBy(requestBody.get("updatedBy"));
				notificationMsg = " Team details updated for team " + team.getId();
				team.setTeamName(existingTeam.getTeamName());
				editTeamStatus = true;
			} else {

				// set company id
				Company company = companyRepository.findByCompanyId(Integer.parseInt(requestBody.get("companyId")));

				if (company != null) {
					team.setCompanyId(company.getId());
				} else {
					response.setStatus(HttpStatus.EXPECTATION_FAILED);
					response.setMessage("Not able to assign company to team");
					response.setContent("Not able to assign company to team");
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
							HttpStatus.EXPECTATION_FAILED);
				}

				company.setCreatedBy(requestBody.get("createdBy"));
				team.setTeamName(requestBody.get("teamName"));
			}
			
			team.setDescription(requestBody.get("description"));
			if (requestBody.get("teamStatus").equals("Y")) {
				team.setTeamStatus(true);
			} else {
				team.setTeamStatus(false);
			}

			TeamRepository.save(team);
			response.setStatus(HttpStatus.OK);
			if (editTeamStatus) {
				response.setMessage("Team updated successfully");
			} else {
				response.setMessage("Team created successfully");
			}

			response.setContent(team);
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while creating team");
			Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/deleteteam", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> deleteteam(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteteam(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Team team = TeamRepository.findByTeamName(requestBody.get("teamName"));

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
/*
			Collection<Employee> employeeCollection = (Collection<Employee>) employeeRepository
					.findEmployeeHavingTeam(team.getId());

			if (employeeCollection.size() != 0) {

				if (team != null) {
					response.setStatus(HttpStatus.OK);
					response.setMessage("team can not be deleted as it is assigned to employee");
					response.setContent(team);
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
				}*/

			/*} else {*/
				team.setDeleteStatus(true);
				team.setTeamStatus(false);
				TeamRepository.save(team);
				response.setStatus(HttpStatus.OK);
				response.setMessage("Team deleted successfully");
				response.setContent(team);
			

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while deleting team");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

	
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
}
