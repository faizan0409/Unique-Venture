package com.ozone.project.component;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ozone.component.datamodel.TaskMap;
import com.ozone.component.datamodel.Tasks;

@Repository
public interface TaskMapRepository extends CrudRepository<TaskMap, Integer> {

	@Query("SELECT t FROM TaskMap t where t.employeeId = :employeeId and t.deleteStatus = false") 
	Iterable<TaskMap> findTaskMapForEmployee(String employeeId);
	
	@Query("SELECT t FROM Tasks t where t.id in (SELECT tm.taskId FROM TaskMap tm where tm.employeeId = :employeeId and tm.deleteStatus = false)") 
	Iterable<Tasks> findTasksForEmployee(String employeeId);
	
	@Query("SELECT t FROM TaskMap t WHERE t.employeeId = :employeeId and t.projectId = :projectId and t.deleteStatus = false")
	Iterable<TaskMap> findProjectBasedTaskMapForEmployee(String employeeId, String projectId);
	
	@Query("SELECT t FROM Tasks t where t.id in (SELECT t.taskId FROM TaskMap t WHERE t.employeeId = :employeeId and t.projectId = :projectId and t.deleteStatus = false)")
	Iterable<Tasks> findProjectBasedTasksForEmployee(String employeeId, String projectId);
	
	@Query("SELECT t FROM TaskMap t WHERE t.taskId = :taskId and t.deleteStatus = false")
	TaskMap findTaskById(int taskId);
	
	@Query("SELECT t FROM TaskMap t WHERE t.taskId = :taskId and t.employeeId = :employeeId and t.projectId = :projectId and t.deleteStatus = false")
	TaskMap findByTaskProjectEmployeeId(int taskId,String employeeId, String projectId);
	
	@Modifying
	@Transactional
	@Query("UPDATE TaskMap t SET t.deleteStatus = true where t.taskId = :taskId")
	void upadteDeleteStatusByTaskId(int taskId);
	
	@Modifying
	@Transactional
	@Query("UPDATE TaskMap t SET t.deleteStatus = true where t.projectId = :projectId")
	void upadteDeleteStatusByProjectId(String projectId);
	
	@Modifying
	@Transactional
	@Query("UPDATE TaskMap t SET t.deleteStatus = true where t.employeeId = :employeeId")
	void upadteDeleteStatusByEmployeeId(String employeeId);
}
