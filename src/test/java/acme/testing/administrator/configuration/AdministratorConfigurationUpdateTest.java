package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorConfigurationUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String currency,final String acceptedCurrencies,final String strongSpam, final Double strongSpamThreshold,final String weakSpam,final Double weakSpamThreshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Configuration");
		super.clickOnButton("Update");
		super.fillInputBoxIn("currency", currency);
		super.fillInputBoxIn("strongSpam", strongSpam);
		super.clickOnSubmit("Update");
		
		
		super.checkFormExists();
		
		super.checkInputBoxHasValue("currency", currency);
		super.checkInputBoxHasValue("strongSpam", strongSpam);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String currency,final String acceptedCurrencies,final String strongSpam, final Double strongSpamThreshold,final String weakSpam,final Double weakSpamThreshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Configuration");
		super.clickOnButton("Update");
		super.fillInputBoxIn("currency", currency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpam", strongSpam);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold.toString());
		super.fillInputBoxIn("weakSpam", weakSpam);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold.toString());
		super.clickOnSubmit("Update");
		
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	

}
