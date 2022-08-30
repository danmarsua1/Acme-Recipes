package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends AbstractEntity {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Pattern(regexp = "^(EUR|GBP|USD)$")
	protected String				currency;
	
	@NotBlank
	@Pattern(regexp = "^$|(^[^\\.]+([\\.][^\\.]+)*$)")
	protected String				acceptedCurrencies;

	@NotBlank
	@Pattern(regexp = "^$|(^[^\\.]+([\\.][^\\.]+)*$)")
	private String					strongSpam;
	
	@Range(min = 0, max = 100)
	private Double					strongSpamThreshold;
	
	@NotBlank
	@Pattern(regexp = "^$|(^[^\\.]+([\\.][^\\.]+)*$)")
	private String					weakSpam;
	
	@Range(min = 0, max = 100)
	private Double					weakSpamThreshold;
	
	// Derived attributes -----------------------------------------------------
	
	public boolean isAcceptedCurrency(final String currency) {
		final String upperCaseCurrency = currency.toUpperCase();
		boolean accepted = false;
		
		// Manage likely currencies
		for (final String acceptedCurrency : this.acceptedCurrencies.toUpperCase().split(".")) {
			if (upperCaseCurrency.equals(acceptedCurrency)) {
				accepted = true;
				break;
			}
		}
		
		return accepted;
	}
	
	public boolean isSpam(final String text) {
		final String lowerCaseText = text.toLowerCase();
		
		int strongSpamCount = 0;
		int weakSpamCount = 0;
		
		// Manage likely strong spam words
		for (final String spamWord : this.strongSpam.toLowerCase().split(".")) {
			strongSpamCount += StringUtils.countMatches(lowerCaseText, spamWord) * spamWord.length();
		}
		final double strongPercentageResult = strongSpamCount / text.length() * 100;
		
		// Manage likely weak spam words
		for (final String spamWord : this.strongSpam.toLowerCase().split(".")) {
			weakSpamCount += StringUtils.countMatches(lowerCaseText, spamWord) * spamWord.length();
		}
		final double weakPercentageResult = weakSpamCount / text.length() * 100;
		
		return (float) strongPercentageResult > this.strongSpamThreshold || weakPercentageResult > this.weakSpamThreshold;
	}

	// Relationships ----------------------------------------------------------


}
