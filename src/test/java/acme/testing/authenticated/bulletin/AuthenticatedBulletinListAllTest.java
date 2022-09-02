package acme.testing.authenticated.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedBulletinListAllTest extends TestHarness{


    @ParameterizedTest
    @CsvFileSource(resources = "/authenticated/bulletin/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positiveTest(final int recordIndex, final String instantiationMoment, final String heading, final String text, final String flag, final String link) {
        super.signIn("administrator", "administrator");

        super.clickOnMenu("Authenticated", "Bulletins");
        super.checkListingExists();
        super.sortListing(0, "desc");

        super.checkColumnHasValue(recordIndex, 0, heading);
        super.checkColumnHasValue(recordIndex, 1, instantiationMoment);
        super.checkColumnHasValue(recordIndex, 2, text);

        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.checkInputBoxHasValue("heading", heading);
        super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
        super.checkInputBoxHasValue("text", text);
        super.checkInputBoxHasValue("flag", flag);
        super.checkInputBoxHasValue("link", link);

        super.signOut();
    }

}