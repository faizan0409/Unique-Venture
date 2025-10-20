package com.ozone.productpage.compoent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ozone.application.utils.ApplicationConstant;
import com.ozone.notification.component.MailTemplate;
import com.ozone.response.component.model.ResponseModel;

@Controller
@RequestMapping("/product")
public class ProductPageManagement {

	@Autowired
	private MailTemplate mailTemplate;

	// Register Company
	@PostMapping(value = "/sendEnquiry", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> add(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).add(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());

		String message = "";
		try {
			String name = requestBody.get("name");
			String email = requestBody.get("email");
			String sub = requestBody.get("sub");
			String desc = requestBody.get("desc");
			message = ApplicationConstant.EQUIRY_SUCCESSFULL;
			String mailContent = mailTemplate.getHTMLTextForEquiry(name, email, sub, desc);

			if (mailContent != null || !mailContent.equals("")) {
				List<InternetAddress> emailIdList = new ArrayList<>();
				emailIdList.add(new InternetAddress(ApplicationConstant.SUPPORT_EMAIL));
				mailTemplate.createMailTemplateAndSend(mailContent, emailIdList, message);
			}
			response.setStatus(HttpStatus.OK);
			response.setMessage(message);
			response.setContent("Enquiry Email Sent");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while sending enquiry email");
			response.setContent("Error while sending enquiry email");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

	}
}
