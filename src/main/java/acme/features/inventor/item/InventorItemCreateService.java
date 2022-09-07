package acme.features.inventor.item;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.checker.SpamChecker;
import acme.entities.Configuration;
import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorItemCreateService implements AbstractCreateService<Inventor, Item> {
	
	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected SpamChecker checker;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
		
		result = request.getPrincipal().hasRole(Inventor.class);
		
		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		 
		request.bind(entity, errors, "type", "name", "code", "technology", "description", "retailPrice", "link");
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "name", "code", "technology", "description", "retailPrice", "link", "publish");		
	}
	
	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;
		
		Item result;
		Inventor inventor;
		
		inventor = this.repository.findOneInventorByAccountId(request.getPrincipal().getAccountId());		
		result = new Item();
		
		// Manage unique code
				String code = "";

				do
					code = this.createCode();
				while (!this.isCodeUnique(code));
				result.setCode(code);
		
		result.setPublish(false);
		result.setInventor(inventor);
		return result;
	}
	
	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;	
		
		if(!errors.hasErrors("name")) {
			final Configuration sc = this.repository.findConfiguration();
			final boolean spamFree = this.checker.isSpam(entity.getName(), sc.getStrongSpam(),sc.getWeakSpam(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "name", "form.error.spam");
		}

		if(!errors.hasErrors("description")) {
			final Configuration sc = this.repository.findConfiguration();
			final boolean spamFree = this.checker.isSpam(entity.getDescription(), sc.getStrongSpam(),sc.getWeakSpam(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "description", "form.error.spam");
		}
	}
			
	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		entity.setPublish(false);
		
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

		public String createCode() {

			// The ticker must be as follow: AAA-XXX-A
			String code = new String();
			final String lettersSecuency = this.lettersSecuency();

			// Set ticker format
			code = this.lettersSecuency() + "-" + this.numbersSecuency() + "-" + this.generateLetter(lettersSecuency);

			return code;

		}

		public boolean isCodeUnique(final String code) {

			Boolean result = true;

			final ArrayList<Item> items = new ArrayList<>(this.repository.findAllItems());

			final ArrayList<String> codes = new ArrayList<>();

			for (final Item t : items) {
				codes.add(t.getCode());
			}

			if (codes.contains(code)) {
				result = false;
				this.createCode();
			}

			return result;

		}
}