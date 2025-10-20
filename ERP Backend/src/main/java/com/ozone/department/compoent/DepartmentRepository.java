package com.ozone.department.compoent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.Department;

/**
 * Author: Sumit Aher
   Date: Mar 19, 2021
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{


	@Query("SELECT d FROM Department d WHERE d.deleteStatus = false ORDER BY d.id DESC")
	Iterable<Department> finddepartments();
	
	@Query("SELECT d FROM Department d WHERE d.id = :departmentId and d.deleteStatus = false")
	Department findByDepartmentId(String departmentId);
	
	@Query("SELECT d FROM Department d WHERE d.id = (SELECT max(p1.id) FROM Project p1)") 
	Department findMaxDepartmentId();
	
	@Query("SELECT d FROM Department d WHERE d.name = :name and d.deleteStatus = false")
	Department findByDepartmentName(String name);
}
