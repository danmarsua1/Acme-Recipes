/*
 * InventorPatronageReportCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.patronageReports;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	// AbstractCreateService<Inventor, PatronageReport> interface -------------------------

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Inventor.class);

		return result;
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		Date moment;
		Calendar calendar;
		Patronage patronage;
		int patronageId;
		int patronageReportLastId = 0;

		result = new PatronageReport();
<<<<<<< HEAD
		patronageId = request.getModel().getInteger("patronageId");
		patronage = this.repository.findPatronageById(patronageId);
		
		final String patronageCode = patronage.getCode();
		result.setSequenceNumber(patronageCode + " : " + this.createTicker());
=======
		
		patronageReportLastId = this.repository.findLastPatronageReport().get(0) != null 
				? this.repository.findLastPatronageReport().get(0).getId() 
				: patronageReportLastId;

		// Manage unique code
		String ticker = "";

		do
			ticker = this.createTicker(patronageReportLastId+1);
		while (!this.isTickerUnique(ticker,patronageReportLastId+1));
		result.setSequenceNumber(ticker);
>>>>>>> refs/remotes/origin/master
		
		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();
		result.setCreationMoment(moment);
		
		
		result.setPatronage(patronage);

		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "sequenceNumber", "memorandum", "link");
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationMoment", "memorandum", "link");
		model.setAttribute("patronageId", entity.getPatronage().getId());
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		Calendar calendar;

		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();

		entity.setCreationMoment(moment);

		this.repository.save(entity);
	}

	// Other business methods -------------------------

	public String createTicker(int patronageReportId) {

<<<<<<< HEAD
		final int num = this.repository.findAllPatronageReports().size()+1;

		String secuency = new String();
		if(num>999) {
			secuency = Integer.toString(num);
		}else if(num>99) {
			secuency="0"+Integer.toString(num);
		}else if(num>9){
			secuency="00"+Integer.toString(num);
		}else {
			secuency="000"+Integer.toString(num);
=======
		// The ticker must be as follow: X...:XXXX
		String ticker = "";
		String sequence = "";
		List<PatronageReport> listPatronageReport = this.repository.findAllPatronageReports();
		String auxSequence = Integer.toString(listPatronageReport.size()+1);
		
		switch(auxSequence.length()) {
			case 1:
				sequence = "000"+auxSequence;
				break;
			case 2:
				sequence = "00"+auxSequence;
				break;
			case 3:
				sequence = "0"+auxSequence;
				break;
			default:
				sequence = auxSequence;
				break;
>>>>>>> refs/remotes/origin/master
		}
		
<<<<<<< HEAD
		return secuency;

	}

	public String createTicker() {

		// The ticker must be as follow:XXXX
		String ticker = new String();

=======
>>>>>>> refs/remotes/origin/master
		// Set ticker format
		ticker = Integer.toString(patronageReportId)+ ":" + sequence;

		return ticker;

	}

<<<<<<< HEAD
//	public boolean isTickerUnique(final String ticker) {
//
//		Boolean result = true;
//
//		final ArrayList<PatronageReport> patronageReports = new ArrayList<>(this.repository.findAllPatronageReports());
//
//		final ArrayList<String> tickers = new ArrayList<>();
//
//		for (final PatronageReport t : patronageReports) {
//			tickers.add(t.getSequenceNumber());
//		}
//
//		if (tickers.contains(ticker)) {
//			result = false;
//			this.createTicker();
//		}
//
//		return result;
//	}
=======
	public boolean isTickerUnique(final String ticker,final int patronageReportId) {

		Boolean result = true;

		final ArrayList<PatronageReport> patronageReports = new ArrayList<>(this.repository.findAllPatronageReports());

		final ArrayList<String> tickers = new ArrayList<>();

		for (final PatronageReport t : patronageReports) {
			tickers.add(t.getSequenceNumber());
		}

		if (tickers.contains(ticker)) {
			result = false;
			this.createTicker(patronageReportId);
		}

		return result;

	}
>>>>>>> refs/remotes/origin/master

}
