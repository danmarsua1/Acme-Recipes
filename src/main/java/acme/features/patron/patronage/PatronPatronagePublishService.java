package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronage>{

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
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "creationMoment", "initDate", "finishDate",
			"budget" , "status", "legalStuff", "link");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creationMoment", "initDate", "finishDate",
			"budget" , "status", "legalStuff", "link");
		
		
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		int itemId;
		Patronage result;

		itemId = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(itemId);

		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;	
		
		if(!errors.hasErrors("budget")) {
			final String upperCaseCurrency = entity.getBudget().getCurrency().toUpperCase();
			boolean accepted = false;
			
			// Manage likely currencies
			for (final String acceptedCurrency : this.repository.findConfiguration().getAcceptedCurrencies().toUpperCase().split("[.]")) {
				if (upperCaseCurrency.equals(acceptedCurrency)) {
					accepted = true;
					break;
				}
			}
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative-budget");
			errors.state(request, accepted, "budget", "patron.patronage.form.error.non-accepted-currency");
		}
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		entity.setPublished(true);
		this.repository.save(entity);
		
	}

}
