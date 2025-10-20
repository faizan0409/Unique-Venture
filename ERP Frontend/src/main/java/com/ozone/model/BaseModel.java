package com.ozone.model;

import java.sql.Date;


/**
 * Class used to store common attributes of all model classes
 * @author minalc
 *
 */

public class BaseModel{

	
	private int id;
	
	private Date createdDatetime;
	
	
	private Date updatedDatetime;
	
	private String createdBy;
	private String updatedBy;
	private boolean deleteStatus;
	
	public BaseModel() {
	}

	
	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date ceatedDatetime) {
		this.createdDatetime = ceatedDatetime;
	}

	public Date getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	@Override
	public boolean equals(Object obj) {

		BaseModel baseModel = (BaseModel) obj;

		if (this.id == baseModel.id) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "BaseModel [id=" + id + ", createdDatetime=" + createdDatetime + ", updatedDatetime=" + updatedDatetime
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", deleteStatus=" + deleteStatus + "]";
	}
}
