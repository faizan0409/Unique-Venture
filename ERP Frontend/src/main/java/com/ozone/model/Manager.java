package com.ozone.model;

public class Manager {
private String employeeId;
private String fullName;
public String getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
@Override
public String toString() {
	return "Manager [employeeId=" + employeeId + ", fullName=" + fullName + "]";
}

}
