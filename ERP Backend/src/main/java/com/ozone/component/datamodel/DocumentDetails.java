package com.ozone.component.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "emp_document_details")
public class DocumentDetails extends BaseModel{

	private String documentName;
	private String documentPath;
	private String employeeID;
	
	public DocumentDetails() {
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
}
