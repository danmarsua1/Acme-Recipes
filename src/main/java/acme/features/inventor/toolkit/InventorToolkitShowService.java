/*
 * InventorToolkitShowService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	// AbstractUpdateService<Inventor, Toolkit> interface -----------------

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int toolkitId;
		Toolkit toolkit;
		boolean isContributor;

		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(toolkitId);
		Collection<Integer> iventors = this.repository.findContributorsByToolkitId(toolkitId);
		isContributor = iventors.contains(request.getPrincipal().getActiveRoleId());
		result = toolkit != null && isContributor;

		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		int toolkitId;
		Toolkit result;

		toolkitId = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(toolkitId);

		return result;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes" , "publish", "link");
		
		int toolkitId = request.getModel().getInteger("id");
		Object[] price = this.repository.getToolkitPrice(toolkitId);
		Object totalPrice = price[0];
		model.setAttribute("totalPrice", totalPrice);
	}
	
}
