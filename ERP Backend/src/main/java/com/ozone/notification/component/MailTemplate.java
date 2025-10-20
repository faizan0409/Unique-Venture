package com.ozone.notification.component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ozone.application.utils.ApplicationConstant;
import com.ozone.component.datamodel.Clients;
import com.ozone.component.datamodel.Company;
import com.ozone.component.datamodel.Employee;

@Component
public class MailTemplate {

	@Value("${spring.mail.username}")
	private String senderAddress;

	@Value("${spring.mail.password}")
	private String senderPassword;

	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.protocol}")
	private String protocol;

	@Value("${spring.mail.port}")
	private String port;
	
	@Value("${spring.mail.properties.socketfactory.class}")
	private String socketFactoryClass;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String authEnable;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String starttlsEnable;

	public void createMailTemplateAndSend(String mailContent, List<InternetAddress> receiverAddress, String subject) {

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", protocol);
		props.put("mail.host", host);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		props.put("mail.smtp.auth", authEnable);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", starttlsEnable);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderAddress, senderPassword);
			}
		});

		try {
			
			Transport transport = session.getTransport();
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderAddress,"Ozone Support"));
			//message.setRecipients(javax.mail.Message.RecipientType.TO, recipientAddress);
			
			if(receiverAddress.size() > 0) {
				message.setRecipients(javax.mail.Message.RecipientType.BCC, receiverAddress.stream().toArray(InternetAddress[] ::new));
			}else {
				message.setRecipients(javax.mail.Message.RecipientType.TO, receiverAddress.stream().toArray(InternetAddress[] ::new));	
			}
			
			message.setSubject(subject);

			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html text)
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mailContent, "text/html");
			// add it
			multipart.addBodyPart(messageBodyPart);

			/*
			 * // second part (the image) messageBodyPart = new MimeBodyPart();
			 * 
			 * DataSource fds = new
			 * FileDataSource(this.getClass().getResource("/techflowLogo.png").getPath());
			 * 
			 * //DataSource fds = new FileDataSource("techflowLogo.png");
			 * messageBodyPart.setDataHandler(new DataHandler(fds));
			 * messageBodyPart.setHeader("Content-ID", "<image>");
			 * messageBodyPart.addHeader("Content-Type", "image/png");
			 * 
			 * // add image to the multipart multipart.addBodyPart(messageBodyPart);
			 */

			// put everything together
			message.setContent(multipart);

			// Send the actual HTML message, as big as you like
			transport.connect();
			Transport.send(message);
			transport.close();
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Generate html text as per mail content required for registration
	 * @param appUser
	 * @param password
	 * @return
	 */
	public String getHTMLTextForRegistration(Object user, String password, String userType, String mailContentFor) {

		Employee employee;
		Clients client;
		Company company ;
		String firstName = "";
		String lastName = "";
		String emailId = "";
		String updatedBy = "";
		String htmlText = "";
		
		if(userType.equals(ApplicationConstant.EMPLOYEE)) {
			employee = (Employee)user;
			firstName = employee.getFullName();
			updatedBy = employee.getUpdatedBy();
			emailId = employee.getOrganizationEmailId();
		}else if(userType.equals(ApplicationConstant.CLIENT)) {
			client = (Clients)user;
			updatedBy = client.getCreatedBy();
			emailId = client.getEmailId();
		}else if(userType.equals(ApplicationConstant.COMPANY)) {
			company = (Company)user;
			updatedBy = company.getCreatedBy();
			emailId = company.getEmail();
		}
		
		if((userType.equals(ApplicationConstant.EMPLOYEE)) && (mailContentFor.equals(ApplicationConstant.MAIL_CONTENT_FOR_REGISTER))){
			htmlText = "<p>" + ApplicationConstant.WELCOME_MSG + "<br>" 
					+ "<p>" + ApplicationConstant.EMPLOYEE_REGISTRATION_1 + "<br>"
					+ "<p>" + ApplicationConstant.WEB_PORTAL_LINK + "<br>"
					+ "<p>" + ApplicationConstant.EMPLOYEE_REGISTRATION_2 + "<br>"
					+ "<p>" + ApplicationConstant.USERNAME + emailId + "<br>" 
					+ "<p>" + ApplicationConstant.PASSWORD + password + "<br>"
					+ "<p>" + ApplicationConstant.REGISTRATION_3 ;
					//+ "<br><br><br>" + "<img src=\"cid:image\">";
		}else if((userType.equals(ApplicationConstant.COMPANY)) && (mailContentFor.equals(ApplicationConstant.MAIL_CONTENT_FOR_REGISTER))){
			htmlText = "<p>" + ApplicationConstant.WELCOME_MSG + "<br>" 
					+ "<p>" + ApplicationConstant.COMPANY_REGISTRATION_1 + "<br>"
					+ "<p>" + ApplicationConstant.WEB_PORTAL_LINK + "<br>"
					+ "<p>" + ApplicationConstant.EMPLOYEE_REGISTRATION_2 + "<br>"
					+ "<p>" + ApplicationConstant.USERNAME + emailId + "<br>" 
					+ "<p>" + ApplicationConstant.PASSWORD + password + "<br>"
					+ "<p>" + ApplicationConstant.REGISTRATION_3 ;
		}else if((userType.equals(ApplicationConstant.COMPANY)) && (mailContentFor.contains(ApplicationConstant.MAIL_CONTENT_FOR_OTP))){
			htmlText = "<p>" + ApplicationConstant.WELCOME_MSG + "<br>" 
					+ "<p>" + ApplicationConstant.COMPANY_OTP_1 + 
					"<br>" + "<p>" + ApplicationConstant.COMPANY_OTP_2
					+ "<br>" + "<p>" + "OTP : " + mailContentFor.substring(mailContentFor.indexOf("_")+1) 
					+ "<br>" + "<p>" + ApplicationConstant.COMPANY_OTP_3 
					+ "<p>" + ApplicationConstant.REGISTRATION_3;
		}
		else if(mailContentFor.equals(ApplicationConstant.MAIL_CONTENT_FOR_RESETPASSWORD)){
			htmlText = "<p>" + ApplicationConstant.RESET_PASSWORD_MSG + firstName + " " + lastName
					+ " by " + updatedBy + "<br>" + "<p>" + ApplicationConstant.CREDENTIAL_MSG
					+ "<br>" + "<p>" + ApplicationConstant.USERNAME + emailId + "<br>" 
					+ "<p>" + ApplicationConstant.PASSWORD + password;
					//+ "<br><br><br>" + "<img src=\"cid:image\">";
		}else if(mailContentFor.equals(ApplicationConstant.EMAIL_VALIDATION)){
			htmlText = "<p>" + ApplicationConstant.MAIL_CONTENT_FOR_VALIDATE_EMAIL;
					//+ "<br><br><br>" + "<img src=\"cid:image\">";
		}else if(mailContentFor.equals(ApplicationConstant.MAIL_CONTENT_FOR_DOCUMENT)) {
			htmlText = "<p>" + ApplicationConstant.DOCUMENT_ADDED_FOR_EMPLOYEE;
		}
	
		return htmlText;
	}
	
	public String getHTMLTextForEquiry(String name, String email, String sub, String desc) {

		String htmlText = "";
	
			htmlText = "<p>" + ApplicationConstant.WELCOME_MSG + "<br>" 
					+ "<p> You have received equiry from " +name+". Please check below details<br>"
					+ "<p> Name: "+name+"<br>"
					+ "<p> Email: "+email+"<br>"
					+ "<p> Subject: "+sub+"<br>"
					+ "<p> Description: "+desc+"<br>";
				
	
		return htmlText;
	}
	
}
