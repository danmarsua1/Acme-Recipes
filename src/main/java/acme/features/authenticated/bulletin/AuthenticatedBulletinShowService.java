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

package acme.features.authenticated.bulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedBulletinShowService implements AbstractShowService<Authenticated, Bulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedBulletinRepository repository;

	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public Bulletin findOne(final Request<Bulletin> request) {
		assert request!=null;
		
		Bulletin result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneBulletinById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "instantiationMoment","heading","text","flag","link");
		
	}

	

}