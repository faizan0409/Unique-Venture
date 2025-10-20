package com.ozone.notification.component;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	
	@Query("SELECT n FROM Notification n WHERE n.seenStatus = :false")
	Iterable<Notification> findNotificationBySeenStatus();

	@Query("SELECT n FROM Notification n WHERE n.id = :id and n.deleteStatus = :deleteStatus")
	Notification findNotificationById(int id);

}
