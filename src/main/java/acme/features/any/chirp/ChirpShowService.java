/*
 * AnyDutyShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.chirp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class ChirpShowService implements AbstractShowService<Any, Chirp> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChirpRepository repository;

	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public Chirp findOne(final Request<Chirp> request) {
		assert request!=null;
		
		Chirp result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneChirpById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "creationMoment","title","author","body","email");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
		model.setAttribute("showmoment", true);
		
	}


}
