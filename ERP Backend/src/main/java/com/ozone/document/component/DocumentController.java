package com.ozone.document.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.ozone.application.utils.ApplicationConstant;
import com.ozone.application.utils.MediaTypeUtils;
import com.ozone.component.datamodel.DocumentDetails;
import com.ozone.component.datamodel.Employee;
import com.ozone.notification.component.MailTemplate;
import com.ozone.response.component.model.ResponseModel;
import com.ozone.user.component.EmployeeRepository;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private MailTemplate mailTemplate;
	
	@Autowired
	private ServletContext servletContext;

	private static String UPLOADED_FOLDER = "EmployeeDocuments";
	
	@PostMapping(value = "/send_document_to_employee")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> sendDocuemntToEmployee(@RequestPart("files") MultipartFile[] file) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(this.getClass()).sendDocuemntToEmployee(file));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());

		List<DocumentDetails> documentList = new ArrayList<>();

		try {
			    
			if (file.length>0) {
				
				for(int i=0; i< file.length; i++) {
					
					if(!file[i].isEmpty()) {
						
						DocumentDetails documentDetails = new DocumentDetails();
						String employeeID = file[i].getOriginalFilename().substring(0, file[i].getOriginalFilename().indexOf("_"));

						DocumentDetails existingDoc = documentRepository.findEmpDocumentByDocumentName(file[i].getOriginalFilename(), employeeID);
						
						if(existingDoc != null)
						{
							documentDetails = existingDoc;
							
						}else {
							documentDetails.setEmployeeID(employeeID);
						}
						
						documentDetails.setDocumentName(file[i].getOriginalFilename());
						documentDetails.setDocumentPath(UPLOADED_FOLDER + "/" + file[i].getOriginalFilename());
						documentDetails.setDeleteStatus(false);
						documentList.add(documentDetails);
					}
				}
				
				if(documentList.size() > 0) {
					documentRepository.saveAll(documentList);
					sendEmailNotificationToEmployee(documentList);
				}
				
			} else {
				response.setStatus(HttpStatus.NO_CONTENT);
				response.setMessage("No document is available in request");
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
			}

		} catch (Exception ex) {
			
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting documents for employee");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Document added for employees");
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);

	}

/*	
	@PostMapping(value = "/delete", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> delete(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).delete(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Category3Dpdf pdfCategory = new Category3Dpdf();

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {

			pdfCategory = pdfCategoryRepository.findById(Integer.parseInt(requestBody.get("id")));
			
			pdfCategory.setDeleteStatus(true);
			pdfCategoryRepository.save(pdfCategory);
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage(ex.getMessage());
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("3Dpdf category deleted successfully");
		response.setContent(pdfCategory);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}*/
	
	/**
	 * Download file 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile(
            @RequestParam("fileName") String fileName) throws IOException {
 
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        
        Path path = Paths.get(UPLOADED_FOLDER + "/" + fileName);
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);
 
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                // Content-Type
                .contentType(mediaType) //
                // Content-Lengh
                .contentLength(data.length) //
                .body(resource);
    }
	
	/**
	 * Method used to save notifications for document added for employee
	 * @param id
	 */
	private void sendEmailNotificationToEmployee(List<DocumentDetails> documentList) {
		
		Set<InternetAddress> emailIdSet = new HashSet<>();
		
		try {
			for (DocumentDetails documentDetails : documentList) {
				
				Employee employee = employeeRepository.findByEmployeeId(documentDetails.getEmployeeID());
				
				if(employee != null) {
						emailIdSet.add(new InternetAddress(employee.getOrganizationEmailId()));
				}
			}	
			
			String mailContent = mailTemplate.getHTMLTextForRegistration(new Object(), "", "Document", ApplicationConstant.MAIL_CONTENT_FOR_DOCUMENT);
			mailTemplate.createMailTemplateAndSend(mailContent, new ArrayList<>(emailIdSet), ApplicationConstant.MAIL_CONTENT_FOR_DOCUMENT);
			
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
	}
}
