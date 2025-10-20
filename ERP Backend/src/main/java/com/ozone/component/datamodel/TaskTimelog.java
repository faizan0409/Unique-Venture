package com.ozone.component.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "emp_task_timelog")
public class TaskTimelog extends BaseModel{

	private double workHrs;
	private int taskMapId;
	private String day;
	private String comment;
		
	public TaskTimelog() {
	}

	public double getWorkHrs() {
		return workHrs;
	}

	public void setWorkHrs(double workHrs) {
		this.workHrs = workHrs;
	}

	public int getTaskMapId() {
		return taskMapId;
	}

	public void setTaskMapId(int taskMapId) {
		this.taskMapId = taskMapId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
