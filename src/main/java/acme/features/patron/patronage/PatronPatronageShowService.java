package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		int patronageId;
		Patronage patronage;
		
		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		result = request.getPrincipal().getActiveRoleId() == patronage.getPatron().getId();
		
		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		int id;
		Patronage result;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		UserAccount inventor;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMoment", "initDate", "finishDate", "link", "patron", "inventor", "published");
    
		inventor = entity.getInventor().getUserAccount();
		model.setAttribute("inventorId", inventor.getId());
	}
}