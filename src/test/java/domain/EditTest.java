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

    @Before
    public void setUp() {
        driver = DriverHelper.getDriver();

        //Login for tests
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.logIn();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void editUser_allFieldsFilledInCorrectly_userIsEdited() {
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        //Get latest added user from registertest
        int added_id = overviewPage.highestId();

        // WHEN STEP = action
        overviewPage.editUserWithId(added_id);

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
        overviewPage.editUserWithId(3);

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
        assertTrue(editPage.hasStickyFirstName("employee"));
        assertTrue(editPage.hasStickyLastName("ucll"));
        assertTrue(editPage.hasStickyEmail("employee@ucll.be"));
    }

    @Test
    public void editUserNonExistingId_Overview() {
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        // WHEN STEP = action
        overviewPage.editUserWithIdUrl(5);

        // THEN STEP = result
        assertEquals("Overview", overviewPage.getTitle());
    }
}