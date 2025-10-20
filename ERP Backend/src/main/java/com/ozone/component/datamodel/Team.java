package com.ozone.component.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ozone_team")
public class Team extends BaseModel {

	private String teamName;
	private String description;
	private int companyId;
	private boolean teamStatus;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
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

	public boolean isTeamStatus() {
		return teamStatus;
	}

	public void setTeamStatus(boolean teamStatus) {
		this.teamStatus = teamStatus;
	}

}
