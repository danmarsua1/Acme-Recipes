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

package acme.features.any.chirp;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Chirp;
import acme.entities.Configuration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChirpRepository extends AbstractRepository {

	@Query("select c from Chirp c where c.id = :id")
	Chirp findOneChirpById(int id);
	
	@Query("select c from Chirp c where TO_DAYS(current_date()) - TO_DAYS(c.creationMoment) < 30")
	Collection<Chirp> findAllChirps();
	
	@Query("select c from Configuration c")
	Configuration findConfiguration();
	
	
}
