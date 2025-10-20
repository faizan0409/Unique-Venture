//Created By: Siddhi Suryawanshi APR  01, 2021
package com.ozone.model;

public class Role {

	private String name;
	private String description;
	private int companyId;
	private String roleStatus;
	private int id;

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

	public String getroleStatus() {
		return roleStatus;
	}

	public void setroleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", description=" + description + ", companyId=" + companyId + ", roleStatus="
				+ roleStatus + ", id=" + id + "]";
	}

}
