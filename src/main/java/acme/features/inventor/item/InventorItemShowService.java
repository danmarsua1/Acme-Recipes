package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int id;
		Item item;
		
		id = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(id);
		result = item != null && item.getInventor().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		int id;
		Item result;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "name", "code", "technology", "description", "retailPrice", "link", "publish");
	}
}