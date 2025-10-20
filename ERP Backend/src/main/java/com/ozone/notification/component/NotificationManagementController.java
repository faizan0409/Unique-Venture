package com.ozone.notification.component;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Throwables;
import com.ozone.component.datamodel.Notification;
import com.ozone.response.component.model.ResponseModel;

@Controller
@RequestMapping("/adminnotification")
public class NotificationManagementController {

	@Autowired
	private NotificationRepository notificationRepository;
	
	@GetMapping(value = "/find_unseen_notification", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> findUnseenNotification() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findUnseenNotification());

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		
		List<Notification> unseenNotificationList = new ArrayList<>();
		try {
		
			unseenNotificationList = (List<Notification>) notificationRepository.findNotificationBySeenStatus();

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting unseen notification list");
			Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Unseen notification list : "+unseenNotificationList);
		response.setContent(unseenNotificationList);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	@PostMapping(value = "/update_notification_status", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> updateNotificationStaus(@RequestParam("id") int notificationId) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).updateNotificationStaus(notificationId));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		
		Notification notification = null;
		try {
		
			notification = notificationRepository.findNotificationById(notificationId);
			
			if(notification != null) {
				notification.setSeenStatus(true);
				
				notificationRepository.save(notification);
			}
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while updating notification status");
			Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Notification status updated");
		response.setContent("Notification status updated");
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
}
