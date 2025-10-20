package com.ozone.component.datamodel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "emp_notification")
public class Notification extends BaseModel{
	
	private String notificationMessage;
	private Date scheduledTime;
	private boolean seenStatus;
		
	public Notification() {
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public boolean isSeenStatus() {
		return seenStatus;
	}

	public void setSeenStatus(boolean seenStatus) {
		this.seenStatus = seenStatus;
	}
}
