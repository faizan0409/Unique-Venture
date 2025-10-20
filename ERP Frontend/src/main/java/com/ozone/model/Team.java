package com.ozone.model;

public class Team {

	private String teamName;
	private String description;
	private int companyId;
	private String teamStatus;

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

	public String getTeamStatus() {
		return teamStatus;
	}

	public void setTeamStatus(String teamStatus) {
		this.teamStatus = teamStatus;
	}

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", description=" + description + ", companyId=" + companyId
				+ ", teamStatus=" + teamStatus + "]";
	}

	public void setId(int asInt) {
		// TODO Auto-generated method stub

	}

}
