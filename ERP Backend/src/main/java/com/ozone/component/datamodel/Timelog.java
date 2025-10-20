package com.ozone.component.datamodel;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ozone_employee_timelog")
public class Timelog extends BaseModel{

	private LocalTime loginTime;
	private LocalTime logoutTime;
	private double workHrs;
	private double approvedHrs;
	private String employeeId;
	private String day;
	private String approversComment;
		
	public Timelog() {
	}

	public LocalTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalTime getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(LocalTime logoutTime) {
		this.logoutTime = logoutTime;
	}

	public double getWorkHrs() {
		return workHrs;
	}

	public void setWorkHrs(double workHrs) {
		this.workHrs = workHrs;
	}

	public double getApprovedHrs() {
		return approvedHrs;
	}

	public void setApprovedHrs(double approvedHrs) {
		this.approvedHrs = approvedHrs;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getApproversComment() {
		return approversComment;
	}

	public void setApproversComment(String approversComment) {
		this.approversComment = approversComment;
	}

	@Override
	public String toString() {
		return "Timelog [loginTime=" + loginTime + ", logoutTime=" + logoutTime + ", workHrs=" + workHrs
				+ ", approvedHrs=" + approvedHrs + ", employeeId=" + employeeId + ", day=" + day + ", approversComment="
				+ approversComment + "]";
	}
}
