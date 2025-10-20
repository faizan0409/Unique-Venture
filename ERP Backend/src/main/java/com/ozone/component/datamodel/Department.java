package com.ozone.component.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Sumit Aher Date: Mar 19, 2021
 */

@Entity
@Table(name = "ozone_department")
public class Department extends BaseModel {
	private String name;
	private String description;
	private int companyId;
	private boolean departmentStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public boolean isDepartmentStatus() {
		return departmentStatus;
	}

	public void setDepartmentStatus(boolean departmentStatus) {
		this.departmentStatus = departmentStatus;
	}

	@Override
	public String toString() {
		return "Department [name=" + name + ", description=" + description + ", companyId=" + companyId
				+ ", departmentStatus=" + departmentStatus + "]";
	}

}
