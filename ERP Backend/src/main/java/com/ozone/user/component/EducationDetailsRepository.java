package com.ozone.user.component;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.EducationDetails;

@Repository
public interface EducationDetailsRepository extends CrudRepository<EducationDetails, Integer> {

	@Query("SELECT ed FROM EducationDetails ed WHERE ed.employeeId = :employeeId") 
	Iterable<EducationDetails> findByEmployeeId(String employeeId);
}
