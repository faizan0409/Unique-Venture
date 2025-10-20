package com.ozone.project.component;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ozone.component.datamodel.Employee;
import com.ozone.component.datamodel.Project;
import com.ozone.component.datamodel.ProjectMap;

@Repository
public interface ProjectMapRepository extends CrudRepository<ProjectMap, Integer> {
	
	@Query("SELECT p FROM ProjectMap p WHERE p.projectId = :projectId AND p.deleteStatus = false ORDER BY p.id DESC")
	Iterable<ProjectMap> findByProjectId(String projectId);

	@Query("SELECT p FROM ProjectMap p WHERE p.employeeId = :employeeId AND p.deleteStatus = false")
	Iterable<ProjectMap> findByEmployeeId(String employeeId);
	
	@Query("SELECT p FROM ProjectMap p WHERE p.id = :Id AND p.deleteStatus = false")
	ProjectMap findById(int Id);
	
	@Query("SELECT p FROM ProjectMap p WHERE p.projectId = :projectId AND p.employeeId = :employeeId AND p.deleteStatus = false")
	public ProjectMap findByProjectIdAndEmployeeId(@Param("projectId") String projectId, @Param("employeeId") String employeeId);
	
	@Modifying
	@Transactional
	@Query("UPDATE ProjectMap pm SET pm.deleteStatus = true where pm.employeeId = :employeeId")
	void upadteDeleteStatusByEmployeeId(String employeeId);

	@Query("SELECT e FROM Employee e WHERE e.employeeId IN (SELECT pm.employeeId FROM ProjectMap pm WHERE pm.projectId = :projectId) and e.deleteStatus = false")
	Iterable<Employee> findEmployeesForProject(String projectId);
	
	@Modifying
	@Transactional
	@Query("UPDATE ProjectMap pm SET pm.deleteStatus = true where pm.projectId = :projectId")
	void upadteDeleteStatusByProjectId(int projectId);
}
