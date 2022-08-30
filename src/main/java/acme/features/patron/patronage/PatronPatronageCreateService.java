package acme.features.patron.patronage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.StatusPatronage;
import acme.features.inventor.patronage.InventorPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected InventorPatronageRepository inventorRepository;
	
	@Override	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		
		result = request.getPrincipal().hasRole(Patron.class);
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "initDate", "finishDate",
			"budget", "legalStuff", "link");		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creationMoment", "initDate", "finishDate",
			"budget" , "status", "legalStuff", "link");		
		List<Inventor> inventors = this.inventorRepository.findAllInventors();
		model.setAttribute("inventors", inventors);
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		Date creationMoment;
		Patronage result;
		result = new Patronage();

		

		result.setPatron(this.repository.findPatronByUserId(request.getPrincipal().getAccountId()));
		
		result.setStatus(StatusPatronage.PROPOSED);
		
		List<Inventor> inventors = this.inventorRepository.findAllInventors();
		result.setInventor(this.inventorRepository.findInventorById(inventors.get(0).getId()));
		
		// Manage unique code
		String ticker = "";

		do
			ticker = this.createTicker();
		while (!this.isTickerUnique(ticker));
		result.setCode(ticker);
		result.setPublished(false);
		
		creationMoment = new Date(System.currentTimeMillis() - 1000);
		result.setCreationMoment(creationMoment);
		
		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("initDate")) {
			Calendar calendar;

			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			errors.state(request, entity.getInitDate().after(calendar.getTime()), "initDate", "patron.patonage.form.error.too-close-init-date");
		}
		if (!errors.hasErrors("finishDate")) {
			Calendar calendar;
			Date finish;
			calendar = new GregorianCalendar();
			calendar.setTime(entity.getInitDate());
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			finish = calendar.getTime();
			errors.state(request, entity.getFinishDate().after(finish), "finishDate", "patron.patonage.form.error.one-month");
		}
		if(!errors.hasErrors("budget")) {
			final String upperCaseCurrency = entity.getBudget().getCurrency().toUpperCase();
			boolean accepted = false;
			
			// Manage likely currencies
			for (final String acceptedCurrency : this.repository.findConfiguration().getAcceptedCurrencies().toUpperCase().split("[.]")) {
				if (upperCaseCurrency.equals(acceptedCurrency)) {
					accepted = true;
					break;
				}
			}
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative-budget");
			errors.state(request, accepted, "budget", "patron.patronage.form.error.non-accepted-currency");
		}
		
	}


	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity !=null;
		
		Date creationMoment;
		
		creationMoment = new Date(System.currentTimeMillis() - 1000);
		entity.setCreationMoment(creationMoment);
		
		String nameInventor = (String) request.getModel().getAttribute("inventors");
		
		Inventor inventor = this.inventorRepository.findByName(nameInventor);
		
		entity.setInventor(inventor);
		
		
		this.repository.save(entity);
		
		
	}
	
	// Other business methods -------------------------

		public String numbersSecuency() {

			final char[] elementos = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

			final char[] conjunto = new char[3];

			final String secuency;

			for (int i = 0; i < 3; i++) {
				final int el = (int) (Math.random() * 9);
				conjunto[i] = elementos[el];
			}

			secuency = new String(conjunto);
			return secuency;

		}

		public String lettersSecuency() {

			final char[] elementos = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
					'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

			final char[] conjunto = new char[3];

			final String secuency;

			for (int i = 0; i < 3; i++) {
				final int el = (int) (Math.random() * 25);
				conjunto[i] = elementos[el];
			}

			secuency = new String(conjunto).toUpperCase();
			return secuency;

		}

		public String generateLetter(final String secuency) {

			final int rd = (int) (Math.random() * 2);
			final String letter = String.valueOf(secuency.charAt(rd)).toUpperCase();

			return letter;

		}

		public String createTicker() {

			// The ticker must be as follow: AAA-XXX-A
			String ticker = new String();
			final String lettersSecuency = this.lettersSecuency();

			// Set ticker format
			ticker = this.lettersSecuency() + "-" + this.numbersSecuency() + "-" + this.generateLetter(lettersSecuency);

			return ticker;

		}

		public boolean isTickerUnique(final String ticker) {

			Boolean result = true;

			final ArrayList<Patronage> patronages = new ArrayList<>(this.repository.findAllPatronages());

			final ArrayList<String> tickers = new ArrayList<>();

			for (final Patronage t : patronages) {
				tickers.add(t.getCode());
			}

			if (tickers.contains(ticker)) {
				result = false;
				this.createTicker();
			}

			return result;

		}

}
