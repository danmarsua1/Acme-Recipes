/*
 * InventorToolkitRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.Item;
import acme.entities.Quantity;
import acme.entities.Toolkit;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);
	
	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findOneInventorById(int id);

	@Query("select distinct t from Toolkit t, Quantity q, Item i where t.id = q.toolkit.id and q.item.id = i.id and i.inventor.id = :id")
	Collection<Toolkit> findManyToolkitsByInventor(int id);
	
	@Query("select t from Toolkit t")
	Collection<Toolkit> findAllToolkits();
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select i.inventor.id from Toolkit t, Quantity q, Item i where t.id = q.toolkit.id and q.item.id = i.id and t.id = :id")
	Collection<Integer> findContributorsByToolkitId(int id);
	
	@Query("select i.retailPrice.currency, sum(i.retailPrice.amount) from Toolkit t, Quantity q, Item i where t.id = q.toolkit.id and q.item.id = i.id and t.id = :id")
	Object[] getToolkitPrice(int id);
	
	@Query("select distinct q from Toolkit t, Quantity q where t.id = q.toolkit.id and t.id = :id")
	Collection<Quantity> findAllQuantitiesByToolkit(int id);
	
	@Query("select distinct i from Toolkit t, Quantity q, Item i where t.id = q.toolkit.id and q.item.id = i.id and t.id = :id")
	Collection<Item> findAllItemsByToolkit(int id);
	
	@Query("select c from Configuration c")
	Configuration findConfiguration();
}
