package acme.features.inventor.patronageReports;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
public class InventorPatronageReportConfirmService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	// AbstractCreateService<Inventor, PatronageReport> interface
	// -------------------------

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

		result = new PatronageReport();

		// Manage unique code
		String ticker = "";

		do
			ticker = this.createTicker();
		while (!this.isTickerUnique(ticker));
		result.setSequenceNumber(ticker);

		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();
		result.setCreationMoment(moment);

		patronageId = request.getModel().getInteger("patronageId");
		patronage = this.repository.findPatronageById(patronageId);

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

	public String numbersSecuency() {

		final char[] elementos = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		final char[] conjunto = new char[4];

		final String secuency;

		for (int i = 0; i < 4; i++) {
			final int el = (int) (Math.random() * 9);
			conjunto[i] = elementos[el];
		}

		secuency = new String(conjunto);
		return secuency;

	}

	public String createTicker() {

		// The ticker must be as follow:XXXX
		String ticker = new String();

		// Set ticker format
		ticker = this.numbersSecuency();

		return ticker;

	}

	public boolean isTickerUnique(final String ticker) {

		Boolean result = true;

		final ArrayList<PatronageReport> patronageReports = new ArrayList<>(this.repository.findAllPatronageReports());

		final ArrayList<String> tickers = new ArrayList<>();

		for (final PatronageReport t : patronageReports) {
			tickers.add(t.getSequenceNumber());
		}

		if (tickers.contains(ticker)) {
			result = false;
			this.createTicker();
		}

		return result;

	}

}