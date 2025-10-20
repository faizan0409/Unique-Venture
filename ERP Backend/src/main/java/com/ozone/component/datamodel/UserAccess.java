package com.ozone.component.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "emp_user_access")
public class UserAccess extends BaseModel{

	private int userId;
	private int permissionId;
	
	public UserAccess() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public String toString() {
		return "UserAccess [userId=" + userId + ", permissionId=" + permissionId + "]";
	}
}
