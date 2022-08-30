package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitUpdateTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String publish, final String link) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List toolkits");
		super.checkListingExists();
		super.sortListing(2, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");

		super.checkListingExists();
		super.sortListing(2, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String publish, final String link) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List toolkits");
		super.checkListingExists();
		super.sortListing(2, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
//		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");

		super.checkErrorsExist();
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/toolkit/update");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/toolkit/update");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron1", "patron1");
		super.navigate("/inventor/toolkit/update");
		super.checkPanicExists();
		super.signOut();
	}
}