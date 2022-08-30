package acme.features.administrator;

/*
 * AnonymousAnnouncementCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Announcement;
import acme.features.authenticated.announcement.AuthenticatedAnnouncementRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedAnnouncementRepository repository;

	// AbstractCreateService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		return true;
	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;

		Announcement result;

		result = new Announcement();

		return result;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date creationMoment;

		creationMoment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors, "title", "body", "flag", "link");
		entity.setCreationMoment(creationMoment);
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;
		
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");

	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "flag", "link");
		model.setAttribute("confirmation", false);
	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}