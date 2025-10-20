package com.ozone.user.component;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.Department;
import com.ozone.component.datamodel.Employee;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e WHERE e.employeeId = :employeeId and e.deleteStatus = false")
	Employee findByEmployeeId(String employeeId);

	@Query("SELECT e FROM Employee e WHERE e.id = (SELECT max(e1.id) FROM Employee e1)")
	Employee findMaxEmployeeId();

	@Query("SELECT e FROM Employee e WHERE e.organizationEmailId = :oganizationEmailId and e.deleteStatus = false")
	Employee findByEmailId(String oganizationEmailId);

	@Query(value = "SELECT * FROM ozone_employee e WHERE BINARY e.organization_email_id = ?1 and e.delete_status = false", nativeQuery = true)
	Employee findByEmailIdCaseSensitive(String organizationEmailId);

	@Query("SELECT e FROM Employee e WHERE e.deleteStatus = false ORDER BY e.id DESC")
	public Iterable<Employee> findEmployees();

	@Query("SELECT e FROM Employee e WHERE e.id IN (:employeeIdList) and e.deleteStatus = false ORDER BY e.id DESC")
	public Iterable<Employee> findEmployeesByIds(List<Integer> employeeIdList);

	@Query("SELECT e FROM Employee e WHERE e.deleteStatus = false")
	public Iterable<Employee> findEmployeesByRole(String roleName);

	@Query("SELECT e FROM Employee e WHERE e.employeeId LIKE %:employeeId% and e.deleteStatus = false ORDER BY e.createdDatetime DESC")
	Iterable<Employee> findEmployeesByEmployeeId(String employeeId);

	@Query("SELECT e FROM Employee e WHERE e.departmentId = :departmentId and e.deleteStatus = false ORDER BY e.createdDatetime DESC")
	Iterable<Employee> findEmployeeHavingDept(int departmentId);
	
	/*@Query("SELECT e FROM Employee e WHERE e.teamId = :teamId and e.deleteStatus = false ORDER BY e.createdDatetime DESC")
	Iterable<Employee> findEmployeeHavingTeam(int teamId);*/

	@Query("SELECT e FROM Employee e WHERE e.roleId = :roleId and e.deleteStatus = false ORDER BY e.createdDatetime DESC")
	Iterable<Employee> findEmployeeHavingRole(int roleId);

	@Query("SELECT e FROM Employee e WHERE e.departmentId = :departmentId and e.deleteStatus = false")
	Department findDepartmentById(int departmentId);

	@Query("SELECT e FROM Employee e WHERE e.roleId = :roleId and e.deleteStatus = false")
	Department findRoleById(int roleId);

}
