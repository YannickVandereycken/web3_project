package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class EditTest {

    private WebDriver driver;

    // Test variables
    String firstName = "Keanu";
    String lastName = "Reeves";
    String email = "keanu@reeves.com";
    String password = "keanu";

    @Before
    public void setUp() {
        driver = DriverHelper.getDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void editUser_allFieldsFilledInCorrectly_userIsEdited() {
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        // WHEN STEP = action
        overviewPage.editUserWithId(3);

        // GIVEN STEP = context
        EditPage editPage = PageFactory.initElements(driver, EditPage.class);
        editPage.setFirstName(firstName);
        editPage.setLastName(lastName);
        editPage.setEmail(email);

        // WHEN STEP = action
        editPage.update();

        // THEN STEP = result
        assertEquals("Overview", overviewPage.getTitle());
        assertTrue(overviewPage.containsUserWithName(firstName));
    }

    @Test
    public void editUser_emailBlank_errorAndOldValuesKept() {
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        // WHEN STEP = action
        overviewPage.editUserWithId(4);

        // GIVEN STEP = context
        EditPage editPage = PageFactory.initElements(driver, EditPage.class);
        editPage.setFirstName(firstName);
        editPage.setLastName(lastName);
        editPage.setEmail("");

        // WHEN STEP = action
        editPage.update();

        // THEN STEP = result
        assertEquals("Edit User", editPage.getTitle());
        assertTrue(editPage.hasErrorMessage("No email given"));
        assertTrue(editPage.hasStickyFirstName("Good"));
        assertTrue(editPage.hasStickyLastName("Employee"));
        assertTrue(editPage.hasStickyEmail("c@c.be"));
    }

    @Test
    public void editUserNonExistingId_Overview() {
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        // WHEN STEP = action
        overviewPage.editUserWithIdUrl(10);

        // THEN STEP = result
        assertEquals("Overview", overviewPage.getTitle());
    }
}
