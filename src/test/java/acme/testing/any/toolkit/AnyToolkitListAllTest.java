package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitListAllTest extends TestHarness{


    @ParameterizedTest
    @CsvFileSource(resources = "/any/toolkit/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positiveTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String publish, final String link) {
        super.signIn("administrator", "administrator");

        super.clickOnMenu("Any", "Toolkits");
        super.checkListingExists();
        super.sortListing(0, "desc");

        super.checkColumnHasValue(recordIndex, 0, title);
        super.checkColumnHasValue(recordIndex, 1, description);

        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.checkInputBoxHasValue("code", code);
        super.checkInputBoxHasValue("title", title);
        super.checkInputBoxHasValue("description", description);
        super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
        super.checkInputBoxHasValue("publish", publish);
        super.checkInputBoxHasValue("link", link);

        super.signOut();
    }
    
    @Test
	@Order(20)
	public void negativeTest() {
		// There's no negative test case for this listing, since it doesn't
		// involve filling in any forms.
	}
    
    @Test
	@Order(30)
	public void hackingTest() {
    	// There is no hacking test case for this listing, since anyone
    	// have access to this listing
	}

}