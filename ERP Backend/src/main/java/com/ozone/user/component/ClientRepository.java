package com.ozone.user.component;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.Clients;
import com.ozone.component.datamodel.Project;

@Repository
public interface ClientRepository extends CrudRepository<Clients, Integer> {

	@Query("SELECT c FROM Clients c WHERE c.clientId = :clientId and c.deleteStatus = false") 
	Clients findByClientId(String clientId);
	
	@Query("SELECT c FROM Clients c WHERE c.clientName = :clientName and c.deleteStatus = false") 
	Clients findByClientName(String clientName);
	
	@Query("SELECT c FROM Clients c WHERE c.id = (SELECT max(c1.id) FROM Clients c1)") 
	Clients findMaxClientId();
	
	@Query("SELECT c FROM Clients c WHERE c.emailId = :emailId and c.deleteStatus = false") 
	Clients findByEmailId(String emailId);
	
	@Query(value = "SELECT * FROM ozone_clients c WHERE BINARY c.email_id = ?1 and c.delete_status = false", nativeQuery = true) 
	Clients findByEmailIdCaseSensitive(String emailId);
	
	@Query("SELECT c FROM Clients c WHERE c.deleteStatus = false ORDER BY c.id DESC") 
	public Iterable<Clients> findClients();
	
	@Query("SELECT p FROM Project p WHERE  p.clientId = :clientId and p.deleteStatus = false")
	Iterable<Project> findProjectOfClient(String clientId);
}
