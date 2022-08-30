package acme.features.any.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{

	
	@Autowired
	protected AnyToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int toolkitId = request.getModel().getInteger("id");
		final Object[] price = this.repository.getToolkitPrice(toolkitId);
		final Object totalPrice = price[0];
		model.setAttribute("totalPrice", totalPrice);
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "publish", "link");
		
	}

}
