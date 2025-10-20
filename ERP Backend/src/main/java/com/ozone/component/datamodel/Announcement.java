package com.ozone.component.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ozone_announcement")
public class Announcement extends BaseModel {
	private String title;
	private String description;
	private int companyId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	@Override
	public String toString() {
		return "Announcement [title=" + title + ", description=" + description + ", companyId=" + companyId + "]";
	}
}
