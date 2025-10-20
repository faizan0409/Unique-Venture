package com.ozone.component.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ozone_tasks")
public class Tasks extends BaseModel{

	private String taskName;
	private String taskDescription; 
	private String projectId;  
	private String priority; 
	private String startDate; 
	private String endDate;
	private String taskStatus;
	private String companyId;
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	@Override
	public String toString() {
		return "Tasks [taskName=" + taskName + ", taskDescription=" + taskDescription + ", projectId=" + projectId
				+ ", priority=" + priority + ", startDate=" + startDate + ", endDate=" + endDate + ", taskStatus="
				+ taskStatus + ", companyId=" + companyId + "]";
	}
	
}