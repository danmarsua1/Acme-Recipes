package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.checker.SpamChecker;
import acme.entities.Configuration;
import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemPublishService implements AbstractUpdateService<Inventor, Item> {

	@Autowired
	protected  InventorItemRepository repository;
	
	@Autowired
	protected SpamChecker checker;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
		int id;
		Item item;

		id = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(id);
		result = !item.isPublish() && item.getInventor().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}
	
	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "type", "name", "code", "technology", "description", "retailPrice", "link");
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "type", "name", "code", "technology", "description", "retailPrice", "link", "publish");
	}
	
	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);

		return result;
	}
	
	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("name")) {
			final Configuration sc = this.repository.findConfiguration();
			final boolean spamFree = this.checker.isSpam(entity.getName(), sc.getStrongSpam(),sc.getWeakSpam(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "name", "form.error.spam");
		}

		if(!errors.hasErrors("description")) {
			final Configuration sc = this.repository.findConfiguration();
			final boolean spamFree = this.checker.isSpam(entity.getDescription(), sc.getStrongSpam(),sc.getWeakSpam(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "description", "form.error.spam");
		}
	}
	
	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublish(true);
		this.repository.save(entity);
	}
}