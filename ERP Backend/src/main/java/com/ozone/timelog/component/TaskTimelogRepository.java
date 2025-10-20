package com.ozone.timelog.component;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.TaskTimelog;

@Repository
public interface TaskTimelogRepository extends CrudRepository<TaskTimelog, Integer> {

	@Query("SELECT tt FROM TaskTimelog tt WHERE tt.taskMapId = :taskMapId and tt.day = :day and tt.deleteStatus = false") 
	TaskTimelog findByTaskMapIdForDay(int taskMapId, String day);
	
	@Query("SELECT tt FROM TaskTimelog tt WHERE tt.taskMapId IN (SELECT tm.id FROM TaskMap tm WHERE tm.taskId = :taskId and tm.employeeId = :employeeId and tm.projectId = :projectId)and tt.day = :day and tt.deleteStatus = false") 
	TaskTimelog findByTaskId(int taskId, String employeeId, String projectId);
	
	@Query("SELECT tt FROM TaskTimelog tt WHERE tt.taskMapId IN (SELECT tm.id FROM TaskMap tm WHERE tm.taskId IN (SELECT t.id FROM Tasks t WHERE t.taskName = :taskName)) and tt.deleteStatus = false") 
	Iterable<TaskTimelog> findTaskTimelogByTaskName(String taskName);
}
