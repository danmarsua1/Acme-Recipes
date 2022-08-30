package acme.testing.authenticated.patron;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedPatronCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/patron/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String username, final String password,final String company,final String statement,final String link) {	
		super.signIn(username, password);
		super.clickOnMenu("Account", "Become a patron");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Account", "Patron data");
		
		super.checkFormExists();
		
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("link", link);
		
	
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/patron/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String username, final String password,final String company,final String statement,final String link) {
		super.signIn(username, password);
		super.clickOnMenu("Account", "Become a patron");
	
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("link", link);

		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}
		
}
