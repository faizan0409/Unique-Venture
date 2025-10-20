package com.ozone.project.component;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ozone.component.datamodel.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

	@Query("SELECT p FROM Project p WHERE p.projectTitle = :projectTitle and p.deleteStatus = false")
	Project findByProjectTitle(String projectTitle);
	
	@Query("SELECT p FROM Project p WHERE p.projectId = :projectId and p.deleteStatus = false")
	Project findByProjectId(String projectId);
	
	@Query("SELECT p FROM Project p WHERE p.id = :projectId and p.deleteStatus = false" )
	Project findByProjectId(int projectId);

	@Query("SELECT p FROM Project p WHERE p.deleteStatus = false ORDER BY p.id DESC")
	Iterable<Project> findProjects();
	
	@Query("SELECT p.projectId FROM Project p WHERE p.deleteStatus = false ORDER BY p.id DESC")
	Iterable<String> findProjectIds();
	
	@Query("SELECT p FROM Project p WHERE p.id = (SELECT max(p1.id) FROM Project p1)") 
	Project findMaxProjectId();
	
	@Modifying
	@Transactional
	void deleteByProjectId(String projectId);

	@Query("SELECT p FROM Project p WHERE p.projectId in (SELECT pm.projectId FROM ProjectMap pm WHERE pm.employeeId = :employeeId and pm.deleteStatus = false) and p.deleteStatus = false ORDER BY p.id DESC")
	Iterable<Project> findProjectByEmployeeId(@Param("employeeId") String employeeId);
	
	@Query("SELECT COUNT(p) FROM Project p WHERE p.deleteStatus = false")
	int getProjectCount();

}
