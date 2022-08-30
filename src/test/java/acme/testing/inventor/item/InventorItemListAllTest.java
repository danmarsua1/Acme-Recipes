package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemListAllTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String type, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String publish) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "Items");
		super.checkListingExists();
		super.sortListing(0, "desc");

		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, technology);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, publish);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("type", type);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		// HINT: there's no negative test case for this listing, since it doesn't
		// HINT+ involve filling in any forms.
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/inventor/item/list");
		super.checkPanicExists();
	}
}