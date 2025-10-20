package com.ozone.timelog.component;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.Timelog;

@Repository
public interface TimelogRepository extends CrudRepository<Timelog, Integer> {

	@Query("SELECT t FROM Timelog t WHERE t.employeeId = :employeeId and t.day = :day") 
	Timelog findByEmployeeId(String employeeId, String day);
	
	@Query(value = "SELECT * FROM ozone_employee_timelog WHERE employee_id = ?1 and day BETWEEN ?2 AND ?3", nativeQuery = true) 
	Iterable<Timelog> findTimelogofEmployeeIdBetweenDates(String employeeId, String startDate, String endDate);
	
	@Query("SELECT t FROM Timelog t WHERE t.day = :day") 
	Iterable<Timelog> findTodaysTimelogEntry(String day);
}
