package acme.features.administrator.bulletin;

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

import acme.checker.SpamChecker;
import acme.entities.Bulletin;
import acme.entities.Configuration;
import acme.features.authenticated.bulletin.AuthenticatedBulletinRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBulletinCreateService implements AbstractCreateService<Administrator, Bulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedBulletinRepository repository;
	
	@Autowired
	protected SpamChecker checker;

	// AbstractCreateService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Bulletin instantiate(final Request<Bulletin> request) {
		assert request != null;

		Bulletin result;

		result = new Bulletin();

		return result;
	}

	@Override
	public void bind(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date instantiationMoment;

		instantiationMoment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors, "heading", "text", "flag", "link");
		entity.setInstantiationMoment(instantiationMoment);
	}

	@Override
	public void validate(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("heading")) {
			final Configuration sc = this.repository.findConfiguration();
			final boolean spamFree = this.checker.isSpam(entity.getHeading(), sc.getStrongSpam(),sc.getWeakSpam(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "heading", "form.error.spam");
		}

		if(!errors.hasErrors("text")) {
			final Configuration sc = this.repository.findConfiguration();
			final boolean spamFree = this.checker.isSpam(entity.getText(), sc.getStrongSpam(),sc.getWeakSpam(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "text", "form.error.spam");
		}
		boolean confirmation;
		
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");

	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "heading", "text", "flag", "link");
		model.setAttribute("confirmation", false);
	}

	@Override
	public void create(final Request<Bulletin> request, final Bulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}