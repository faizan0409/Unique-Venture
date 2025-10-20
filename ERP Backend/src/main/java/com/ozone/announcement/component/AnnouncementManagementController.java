package com.ozone.announcement.component;

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
import com.ozone.component.datamodel.Announcement;
import com.ozone.component.datamodel.Company;
import com.ozone.response.component.model.ResponseModel;
import com.ozone.user.component.EmployeeRepository;

@Controller
@RequestMapping("/announcement")
public class AnnouncementManagementController {

	@Autowired
	AnnouncementRepository announcementRepository;
	
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;


	// getAllAnnoucement
	@GetMapping(value = "/getAllAnnouncement", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> getAllAnnouncement() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllAnnouncement());

		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());

		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		List<Announcement> announcements = new ArrayList<>();

		try {
			if (announcementRepository.findannouncements() != null) {

				announcements = (List<Announcement>) announcementRepository.findannouncements();
			}

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting Announcement list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Announcement list");
		response.setStatus(HttpStatus.OK);

		if (!announcements.isEmpty()) {
			response.setContent(announcements);
		} else {
			response.setContent(null);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/saveAnnouncement", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> add(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).add(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Announcement announcement=new Announcement();
		String notificationMsg = "";
		boolean editAnnouncementStatus = false;

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {

			Announcement existingAnnouncement = announcementRepository.findByAnnouncementName(requestBody.get("title"));

			if (existingAnnouncement != null) {
				announcement = existingAnnouncement;
				announcement.setUpdatedBy(requestBody.get("updatedBy"));
				notificationMsg = " Announcement details updated for announcement " + announcement.getId();
				announcement.setTitle(existingAnnouncement.getTitle());
				editAnnouncementStatus = true;
			} else {
				announcement.setTitle(requestBody.get("title"));
			}
			announcement.setCompanyId(Integer.parseInt(requestBody.get("companyId")));
			announcement.setDescription(requestBody.get("description"));
			announcementRepository.save(announcement);
			response.setStatus(HttpStatus.OK);
			if (editAnnouncementStatus) {
				response.setMessage("Announcement updated successfully");
			} else {
				response.setMessage("Announcement created successfully");
			}

			response.setContent(announcement);
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while creating announcement");
			Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/deleteannouncement", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> deleteannouncement(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteannouncement(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Announcement announcement = announcementRepository.findByAnnouncementName(requestBody.get("title"));

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
/*
			Collection<Employee> employeeCollection = (Collection<Employee>) employeeRepository
					.findEmployeeHavingTeam(announcement.getId());

			if (employeeCollection.size() != 0) {

				if (announcement != null) {
					response.setStatus(HttpStatus.OK);
					response.setMessage("announcement can not be deleted as it is assigned to employee");
					response.setContent(announcement);
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
				}*/

			/*} else {*/
				announcement.setDeleteStatus(true);
				announcementRepository.save(announcement);
				response.setStatus(HttpStatus.OK);
				response.setMessage("Announcement deleted successfully");
				response.setContent(announcement);
			

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while deleting announcement");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

	
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
}

