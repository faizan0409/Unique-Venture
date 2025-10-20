package com.ozone.notification.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ozone.application.utils.ApplicationConstant;

@Component
public class ScheduledNotification {

	
	@Scheduled(fixedRate = ApplicationConstant.SCHEDULED_TIME_FOR_NOTIFICATION)
	public void scheduleTaskWithFixedRate() {
		
		try {

		
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
