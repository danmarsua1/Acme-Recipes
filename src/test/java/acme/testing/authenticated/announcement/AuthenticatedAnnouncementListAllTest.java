package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementListAllTest extends TestHarness{


    @ParameterizedTest
    @CsvFileSource(resources = "/authenticated/announcement/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positiveTest(final int recordIndex, final String creationMoment, final String title, final String body, final String flag, final String link) {
        super.signIn("administrator", "administrator");

        super.clickOnMenu("Authenticated", "Announcements");
        super.checkListingExists();
        super.sortListing(0, "desc");

        super.checkColumnHasValue(recordIndex, 0, title);
        super.checkColumnHasValue(recordIndex, 1, creationMoment);
        super.checkColumnHasValue(recordIndex, 2, body);

        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.checkInputBoxHasValue("title", title);
        super.checkInputBoxHasValue("creationMoment", creationMoment);
        super.checkInputBoxHasValue("body", body);
        super.checkInputBoxHasValue("flag", flag);
        super.checkInputBoxHasValue("link", link);

        super.signOut();
    }

}