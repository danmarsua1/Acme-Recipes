/*
 * InventorPatronageRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.patronage;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageReport;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {
	
	@Query("select inventor from Inventor inventor where inventor.userAccount.id =:id")
	Inventor findInventorByUserId(int id);
	
	@Query("select inventor from Inventor inventor where inventor.id =:id")
	Inventor findInventorById(int id);
	
	@Query("select inventor from Inventor inventor")
	List<Inventor> findAllInventors();

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("select p from Patronage p")
	Collection<Patronage> findAllPatronages();
	
	@Query("select i from Patronage i where i.id = :id")
	Patronage findOnePatronageById(int id);

	@Query("select inventor from Inventor inventor where inventor.userAccount.username =:username")
	Inventor findByName(String username);
	
	@Query("select patronageReport from PatronageReport patronageReport where patronageReport.patronage.id = :id")
	Collection<PatronageReport> patonageHasAnyPatronageReports(int id);
	
}
