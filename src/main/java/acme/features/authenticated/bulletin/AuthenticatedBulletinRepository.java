/*
 * AnyDutyRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.bulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Bulletin;
import acme.entities.Configuration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBulletinRepository extends AbstractRepository {

	@Query("select a from Bulletin a where a.id = :id")
	Bulletin findOneBulletinById(int id);
	
	@Query("select a from Bulletin a where TO_DAYS(current_date()) - TO_DAYS(a.instantiationMoment) < 730")
	Collection<Bulletin> findAllBulletins();
	
	@Query("select c from Configuration c")
	Configuration findConfiguration();
	
}