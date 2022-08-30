package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String creationMoment, final String memorandum, final String link) {
	    super.signIn("inventor2", "inventor2");

	    super.clickOnMenu("Inventor", "Patronages");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnButton("Create Patronage Report");
		super.clickOnSubmit("Confirm");
		super.checkFormExists();
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "Patronages");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(recordIndex);
		super.clickOnButton("Show Patronage Report");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron1", "patron1");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();
	}
}