package com.ozone.company.component;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ozone.component.datamodel.Company;


/**
Author: Sumit Aher
Date: 06-Mar-2021
*/

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer>{

	@Query("SELECT c FROM Company c WHERE c.id = :companyId and c.deleteStatus = false")
	Company findByCompanyId(int companyId);
	
	@Query("SELECT c FROM Company c WHERE c.id = (SELECT max(p1.id) FROM Company p1)") 
	Company findMaxCompanyId();
	
	@Query(value = "SELECT * FROM ozone_company e WHERE BINARY e.email = ?1 and e.delete_status = false", nativeQuery = true) 
	Company findByEmailIdCaseSensitive(String organizationEmailId);
	
	@Query("SELECT c FROM Company c WHERE c.email = :email and c.deleteStatus = false")
	Company getCompanyByEmail(String email);
	
	@Query("SELECT c FROM Company c WHERE (c.email = :emailId and c.otp = :otp) and c.deleteStatus = false") 
	Company findByEmailAndOTP(String emailId, String otp);
	
	@Query("SELECT c FROM Company c WHERE c.email = :email")
	Company getCompanyByEmailId(String email);
	
	@Query("SELECT c FROM Company c ORDER BY c.id DESC")
	Iterable<Company> findAllCompanies();
	
	
}
