package com.ozone.component.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ozone_project_map")
public class ProjectMap extends BaseModel{
	
	private String employeeId;
	private String projectId;
	private int jobRoleId;
	
	public ProjectMap() {
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public int getJobRoleId() {
		return jobRoleId;
	}

	public void setJobRoleId(int jobRoleId) {
		this.jobRoleId = jobRoleId;
	}
}
