package com.ozone.roles.compoent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ozone.component.datamodel.Roles;

/**
 * Author: Sumit Aher
   Date: Mar 20, 2021
 */

@Repository
public interface RoleRepository extends CrudRepository<Roles, Integer>{

	@Query("SELECT r FROM Roles r WHERE r.deleteStatus = false ORDER BY r.id DESC")
	Iterable<Roles> findroles();
	
	@Query("SELECT r FROM Roles r WHERE r.name = :name and r.deleteStatus = false")
	Roles findByRoleName(String name);
	
	@Query("SELECT r FROM Roles r WHERE r.id = :id and r.deleteStatus = false ORDER BY r.createdDatetime DESC")
	Iterable<Roles> findEmployeeHavingRole(int id);
}
