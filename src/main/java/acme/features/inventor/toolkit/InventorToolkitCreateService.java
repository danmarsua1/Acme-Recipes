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

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.checker.SpamChecker;
import acme.entities.Configuration;
import acme.entities.Item;
import acme.entities.ItemType;
import acme.entities.Quantity;
import acme.entities.Toolkit;
import acme.features.inventor.item.InventorItemRepository;
import acme.features.inventor.quantity.InventorQuantityRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected InventorItemRepository itemRepository;
	@Autowired
	protected InventorQuantityRepository quantityRepository;
	
	@Autowired
	protected SpamChecker checker;

	// AbstractCreateService<Inventor, Toolkit> interface -------------------------

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Inventor.class);

		return result;
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;

		result = new Toolkit();

		// Manage unique code
		String ticker = "";

		do
			ticker = this.createTicker();
		while (!this.isTickerUnique(ticker));
		result.setCode(ticker);

		result.setPublish(false);

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("title")) {
			final Configuration sc = this.repository.findConfiguration();
			final boolean spamFree = this.checker.isSpam(entity.getTitle(), sc.getStrongSpam(),sc.getWeakSpam(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "title", "form.error.spam");
		}

		if(!errors.hasErrors("description")) {
			final Configuration sc = this.repository.findConfiguration();
			final boolean spamFree = this.checker.isSpam(entity.getDescription(), sc.getStrongSpam(),sc.getWeakSpam(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "description", "form.error.spam");
		}
		
		if(!errors.hasErrors("assemblyNotes")) {
			final Configuration sc = this.repository.findConfiguration();
			final boolean spamFree = this.checker.isSpam(entity.getAssemblyNotes(), sc.getStrongSpam(),sc.getWeakSpam(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "assemblyNotes", "form.error.spam");
		}
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "publish", "link");
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
		// Default item
		final Item item = new Item();
		String itemTicker = "";

		do
			itemTicker = this.createTicker();
		while (!this.isTickerUnique(itemTicker));
		item.setCode(itemTicker);
		item.setDescription(
				"This is a default item due creating new toolkit. Please delete it if you want to use this toolkit.");
		item.setName("Test Item");
		item.setType(ItemType.TOOL);
		item.setTechnology("test");

		final Money money = new Money();
		money.setAmount(0.0);
		money.setCurrency("EUR");
		item.setRetailPrice(money);

		final int inventorId = request.getPrincipal().getAccountId();
		final Inventor inventor = this.repository.findOneInventorById(inventorId);
		item.setInventor(inventor);
		
		this.itemRepository.save(item);

		// Quantity
		final Quantity q = new Quantity();
		q.setItem(item);
		q.setToolkit(entity);
		q.setQuantity(1);
		
		this.quantityRepository.save(q);
		
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

		final ArrayList<Toolkit> toolkits = new ArrayList<>(this.repository.findAllToolkits());

		final ArrayList<String> tickers = new ArrayList<>();

		for (final Toolkit t : toolkits) {
			tickers.add(t.getCode());
		}

		if (tickers.contains(ticker)) {
			result = false;
			this.createTicker();
		}

		return result;

	}

}
