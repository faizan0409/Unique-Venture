package com.ozone.project.component;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.Tasks;

@Repository
public interface TaskRepository extends CrudRepository<Tasks, Integer> {

	@Query("SELECT t FROM Tasks t WHERE t.deleteStatus = false") 
	Iterable<Tasks> findTasks();
	
	@Query("SELECT t FROM Tasks t WHERE t.taskName LIKE %:taskName% and t.deleteStatus = false ORDER BY t.taskName")
	Iterable<Tasks> findTasksByName(String taskName);
	
	//@Query(value = "SELECT * FROM ozone_employee e WHERE BINARY e.organization_email_id = ?1 and e.delete_status = false", nativeQuery = true) 

	@Query(value = "SELECT * FROM ozone_tasks t WHERE t.task_name = ?1 and t.delete_status = false", nativeQuery = true)
	Tasks findTaskByNameCaseSensitive(String taskName);
}
