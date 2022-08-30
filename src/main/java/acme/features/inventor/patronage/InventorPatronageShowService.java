/*
 * InventorPatronageShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.patronage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository repository;

	// AbstractUpdateService<Inventor, Patronage> interface -----------------

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int itemId;
		Patronage item;

		itemId = request.getModel().getInteger("id");
		item = this.repository.findOnePatronageById(itemId);
		result = item != null && item.getInventor().getId() == request.getPrincipal().getActiveRoleId();

		return result;
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
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		int patronId;

		request.unbind(entity, model, "code", "creationMoment", "initDate", "finishDate", "budget" , "status", "legalStuff", "link");
		
		patronId = entity.getPatron().getUserAccount().getId();
		model.setAttribute("patronId", patronId);
		
		// Detect if is any patronageReports
		List<PatronageReport> patronageReports = (List<PatronageReport>) this.repository.patonageHasAnyPatronageReports(entity.getId());
		if(!patronageReports.isEmpty()) {
			model.setAttribute("hasPatronageReport", true);
			model.setAttribute("patronageReportId", patronageReports.get(0).getId());
		}
		
	}
	
}
