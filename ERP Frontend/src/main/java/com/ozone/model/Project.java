package com.ozone.model;

public class Project {

	private String projectId;
	private String projectTitle;
	private String projectDescription;
	private String managerId;
	private String projectStartDate;
	private String projectEndDate;
	private String clientId;
	
	private String projectType;		//cost/revenue
	private String projectStatus;
	
	private String imageData;
	private String imageContentType;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public String getProjectEndDate() {
		return projectEndDate;
	}
	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getImageData() {
		return imageData;
	}
	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectTitle=" + projectTitle + ", projectDescription="
				+ projectDescription + ", managerId=" + managerId + ", projectStartDate=" + projectStartDate
				+ ", projectEndDate=" + projectEndDate + ", clientId=" + clientId + ", projectType=" + projectType
				+ ", projectStatus=" + projectStatus + ", imageData=" + imageData + ", imageContentType="
				+ imageContentType + "]";
	}
	
	
}
