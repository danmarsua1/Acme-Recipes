package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageUpdateService implements AbstractUpdateService<Inventor, Patronage> {
	
		@Autowired
		protected InventorPatronageRepository repository;
		

		@Override
		public boolean authorise(final Request<Patronage> request) {
			assert request != null;
			
			boolean result;
			
			result = request.getPrincipal().hasRole(Inventor.class); 

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
				"budget" , "status", "legalStuff", "link", "published");	
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
		}

		@Override
		public void update(final Request<Patronage> request, final Patronage entity) {
			assert request != null;
			assert entity != null;
			this.repository.save(entity);
		}
	}