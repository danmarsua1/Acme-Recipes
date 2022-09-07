package acme.features.inventor.item;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.Item;
import acme.entities.ItemType;
import acme.entities.Quantity;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository {

	@Query("SELECT userAccount FROM UserAccount userAccount WHERE userAccount.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("SELECT item FROM Item item WHERE item.inventor.id = :id")
	Collection<Item> findAllItemsByInventor(int id);
	
	@Query("SELECT inventor FROM Inventor inventor WHERE inventor.id = :id")
	Inventor findOneInventorById(int id);
	
	@Query("SELECT inventor FROM Inventor inventor WHERE inventor.userAccount.id = :id")
	Inventor findOneInventorByAccountId(int id);
	
	@Query("SELECT item FROM Item item WHERE item.id = :id")
	Item findOneItemById(int id);

	@Query("SELECT item FROM Toolkit toolkit, Quantity quantity, Item item WHERE toolkit.id = quantity.toolkit.id AND quantity.item.id = item.id AND toolkit.id = :toolkitId AND item.inventor.id = :inventorId")
	Collection<Item> findManyItemsByToolkitIdAndInventorId(int toolkitId, int inventorId);
	
	@Query("SELECT item FROM Item item WHERE item.type = :type AND item.inventor.id = :id")
	Collection<Item> findItemsByTypeAndInventorId(ItemType type, int id);
	
	@Query("SELECT item FROM Item item")
	Collection<Item> findAllItems();
	
	@Query("select distinct q from Item i, Quantity q where i.id = q.item.id and i.id = :id")
	Collection<Quantity> findAllQuantitiesByItem(int id);
	
	@Query("select c from Configuration c")
	Configuration findConfiguration();
}