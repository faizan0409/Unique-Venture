package com.ozone.announcement.component;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ozone.component.datamodel.Announcement;
@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Integer> {

	@Query("SELECT a FROM Announcement a WHERE a.deleteStatus = false ORDER BY a.id DESC")
	Iterable<Announcement> findannouncements();
	
	@Query("SELECT a FROM Announcement a WHERE a.title = :title and a.deleteStatus = false")
	Announcement findByAnnouncementName(String title);

}
