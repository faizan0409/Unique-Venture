package com.ozone.salary.component;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ozone.component.datamodel.Salary;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, Integer>{

	@Query("SELECT s FROM Salary s WHERE s.employeeId = :employeeId and s.deleteStatus = false")
	Salary findByEmployeeId(String employeeId);
	
	@Query("SELECT s FROM Salary s WHERE s.deleteStatus = false ORDER BY s.id DESC")
	Iterable<Salary> findemployeesalary();

}
