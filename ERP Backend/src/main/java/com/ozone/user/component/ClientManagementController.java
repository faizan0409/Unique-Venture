package com.ozone.user.component;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ozone.application.utils.ApplicationConstant;
import com.ozone.application.utils.NumberUtils;
import com.ozone.component.datamodel.Clients;
import com.ozone.component.datamodel.Project;
import com.ozone.response.component.model.ResponseModel;

@Controller
@RequestMapping("/client")
public class ClientManagementController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@PostMapping(value = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> saveClient(@RequestBody Map<String, String> requestBody){
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass())
				.saveClient(requestBody));
		System.out.println("requestBody: "+ requestBody);
		String updatedStatus = "";
		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		
		Clients client = new Clients();
		Clients createdClient = null;
		Clients existingClient = null;
		String message = "";
		boolean isClientUpdated = false;
		
		try {
			
			if(!requestBody.get("clientId").equals("0")) {
				existingClient = clientRepository.findByClientId(requestBody.get("clientId"));
				
			}else {
				Clients alreadyExistingClient = clientRepository.findByEmailId(requestBody.get("emailId"));
				
				if(alreadyExistingClient != null) {
					response.setStatus(HttpStatus.ALREADY_REPORTED);
					response.setMessage("Client already exists with email Id "+requestBody.get("emailId"));
					response.setContent("Client already exists with email Id "+requestBody.get("emailId"));
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.ALREADY_REPORTED);
				}
			}
			
			if (existingClient != null) {
				//throw new AuthenticationException("Email already exists");
				
				client = existingClient;
				//client.setUpdatedDatetime(new java.sql.Date(new Date().getTime()));
				client.setUpdatedBy(requestBody.get("createdBy"));
				
				message = "Client details updated successfully!!!";
				updatedStatus = ApplicationConstant.MAIL_CONTENT_FOR_UPADTE;
				isClientUpdated = true;
			}else {
				
				Clients maxClient = clientRepository.findMaxClientId();
				
				if(maxClient == null) {
					maxClient = new Clients();
					maxClient.setClientId("000");
				}
				
				
				String newClientId = maxClient.getClientId().substring(2);
				
				client.setClientId(("CL"+NumberUtils.padZeroes(Integer.parseInt(newClientId)+1)).toString());
				
				//client.setCreatedDatetime(new java.sql.Date(new Date().getTime()));
				client.setCreatedBy(requestBody.get("createdBy"));
				
				message = "Client details added Successfully !!";
				updatedStatus = ApplicationConstant.MAIL_CONTENT_FOR_REGISTER;
			}

			client.setClientName(requestBody.get("clientName"));
			client.setCompanyName(requestBody.get("companyName"));
			client.setAddress(requestBody.get("address"));
			client.setContactNumber(requestBody.get("contactNumber"));
			client.setEmailId(requestBody.get("emailId"));
			client.setDeleteStatus(false);
			
			if(requestBody.get("clientStatus").equals("Y")) {
				client.setClientStatus(true);
			}else {
				client.setClientStatus(false);
			}
		
			createdClient = clientRepository.save(client);

			/*if (!isClientUpdated) {
			
				String mailContent = mailTemplate.getHTMLTextForRegistration(client, defualtPassword,"Client", updatedStatus);

				if (mailContent != null || !mailContent.equals("")) {
					mailTemplate.createMailTemplateAndSend(mailContent, client.getEmailId(), message);
				}
			}*/
			 
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while saving client");
			response.setContent("Error while saving client");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage(message);
		response.setContent(createdClient);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/getclients",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readClientInformation() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).readClientInformation());
		
		ResponseModel response = new ResponseModel();
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		List<Clients> clientList = new ArrayList<>();
		
		try {
			if (clientRepository.findClients() != null) {
				
				clientList = (List<Clients>)clientRepository.findClients();
			}else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage("Clients not availabel");
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage( ex.getMessage());
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Clients Information");
		response.setStatus(HttpStatus.OK);
		
		if(!clientList.isEmpty()) {
			response.setContent(clientList);
		}else {
			response.setContent(null);
		}

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getclientbyemailid",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readClientUsingEmailId(@RequestParam("searchtxt") String emailId) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).readClientUsingEmailId(emailId));
		
		ResponseModel response = new ResponseModel();
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		Clients client = null;
		
		try {

			if (clientRepository.findByEmailId(emailId) != null) {
				
				client = clientRepository.findByEmailId(emailId);
				
			}else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage("Client not availabel");
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting client");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Client Details");
		response.setStatus(HttpStatus.OK);
		response.setContent(client);

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
		
		Clients client = clientRepository.findByEmailIdCaseSensitive(requestBody.get("emailId"));
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response, selfLinkBuilder.withSelfRel());
		try {
			
			List<Project> projectListForClient = (List<Project>) clientRepository.findProjectOfClient(client.getClientId());
			
			if(!projectListForClient.isEmpty()) {
				response.setStatus(HttpStatus.OK);
				response.setMessage("Project assigned to client, not able to delete client");
				response.setContent("Project assigned to client, not able to delete client");
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
				
			}else {
				if(client != null) {
					client.setDeleteStatus(true);
					clientRepository.save(client);
					
				}else {
					response.setStatus(HttpStatus.OK);
					response.setMessage("Unable to get client details");
					response.setContent("Unable to get client details");
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
					
				}
			}
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while deleting client");
			response.setContent("Error while deleting client");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}

		response.setMessage("Client deleted successfully");
		response.setContent("Client deleted successfully");
		response.setStatus(HttpStatus.OK);

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
}
