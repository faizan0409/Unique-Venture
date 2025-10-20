package com.ozone.user.component;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.MedicalDetails;

@Repository
public interface MedicalDetailsRepository extends CrudRepository<MedicalDetails, Integer> {

	@Query("SELECT md FROM MedicalDetails md WHERE md.employeeId = :employeeId") 
	Iterable<MedicalDetails> findByEmployeeId(String employeeId);
}
