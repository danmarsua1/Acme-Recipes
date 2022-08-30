package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository{

	
	@Query("select t from Toolkit t where t.publish=true")
	Collection<Toolkit> findToolkitPublicado();

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);

	/*@Query("select q.toolkit from Quantity q where q.item.id = :item.id")
	Collection<Toolkit> findToolkitByItem(int id);*/

	
	@Query("select i.retailPrice.currency, sum(i.retailPrice.amount) from Toolkit t, Quantity q, Item i where t.id = q.toolkit.id and q.item.id = i.id and t.id = :id")
	Object[] getToolkitPrice(int id);



}
