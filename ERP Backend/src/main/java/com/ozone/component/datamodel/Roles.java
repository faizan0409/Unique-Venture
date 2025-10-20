package com.ozone.component.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "emp_roles")
public class Roles extends BaseModel {

	private String name;
	private String description;
	private int companyId;
	private boolean roleStatus;

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

	public boolean isRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(boolean roleStatus) {
		this.roleStatus = roleStatus;
	}

	@Override
	public String toString() {
		return "Roles [name=" + name + ", description=" + description + ", companyId=" + companyId + ", roleStatus="
				+ roleStatus + "]";
	}

}
