/*
 * InventorToolkitCreateService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	// AbstractCreateService<Inventor, Toolkit> interface -------------------------

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;

		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(masterId);
		final int inventorId = request.getPrincipal().getAccountId();
		Inventor inventor = this.repository.findOneInventorById(inventorId);
		result = !toolkit.isPublish() && request.isPrincipal(inventor);

		return result;
	}
	
	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "publish", "link");
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
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
