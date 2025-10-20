package com.ozone.model;


import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.HttpStatus;
@XmlRootElement()
public class ResponseModel {
	public HttpStatus status;
	public String message;
	public Object content;
	
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
