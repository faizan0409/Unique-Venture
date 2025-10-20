package com.ozone.team.component;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozone.component.datamodel.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer>{
	

	@Query("SELECT t FROM Team t WHERE t.deleteStatus = false ORDER BY t.id DESC")
	Iterable<Team> findteams();
	
	@Query("SELECT t FROM Team t WHERE t.id = :teamId and t.deleteStatus = false")
	Team findByTeamId(String teamId);
	
	@Query("SELECT t FROM Team t WHERE t.id = (SELECT max(p1.id) FROM Project p1)") 
	Team findMaxTeamId();
	
	@Query("SELECT t FROM Team t WHERE t.teamName = :teamName and t.deleteStatus = false")
	Team findByTeamName(String teamName);

}
