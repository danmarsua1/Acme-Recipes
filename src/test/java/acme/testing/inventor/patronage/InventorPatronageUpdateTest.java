package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageUpdateTest extends TestHarness	{
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code,final String legalStuff,final String budget,final String creationMoment,final String initDate,final String finishDate, final String link) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "Patronages");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.fillInputBoxIn("status", status);
		super.clickOnSubmit("Update");
		
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, initDate);
		super.checkColumnHasValue(recordIndex, 1, finishDate);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, status);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("initDate", initDate);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	

	
	
}
