package com.ozone.component.datamodel;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Class used to store common attributes of all model classes
 * @author minalc
 *
 */
@MappedSuperclass
public class BaseModel{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@CreationTimestamp
	private Date createdDatetime;
	
	@UpdateTimestamp
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

	public boolean isDeleteStatus() {
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
