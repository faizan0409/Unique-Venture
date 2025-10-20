package com.ozone.document.component;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.DocumentDetails;

@Repository
public interface DocumentRepository extends CrudRepository<DocumentDetails, Integer> {
	
	@Query("SELECT dd FROM DocumentDetails dd WHERE dd.employeeID = :employeeID and dd.deleteStatus = false")
	Iterable<DocumentDetails> findDocumentOfEmployee(String employeeID);
	
	@Query("SELECT dd FROM DocumentDetails dd WHERE dd.documentName = :documentName and dd.deleteStatus = false")
	DocumentDetails findByDocumentName(String documentName);
	
	@Query("SELECT dd FROM DocumentDetails dd WHERE dd.documentName = :documentName and dd.employeeID = :employeeID and dd.deleteStatus = false")
	DocumentDetails findEmpDocumentByDocumentName(String documentName, String employeeID);
}
