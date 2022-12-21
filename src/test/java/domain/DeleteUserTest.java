package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DeleteUserTest {
    private WebDriver driver;

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
    public void deleteUser_clickConfirm_OverviewAndUserDeleted() {
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        //Get latest added user from registertest
        int added_id = overviewPage.highestId();

        // WHEN STEP = action
        overviewPage.deleteUserWithId(added_id);

        // Confirm action
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.confirm();

        // THEN STEP = result
        assertEquals("Overview", overviewPage.getTitle());
        assertFalse(overviewPage.containsUserWithName("Keanu"));
    }

    @Test
    public void deleteUser_clickCancel_OverviewAndUserNotDeleted() {
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        // WHEN STEP = action
        overviewPage.deleteUserWithId(4);

        // Confirm action
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.cancel();

        // THEN STEP = result
        assertEquals("Overview", overviewPage.getTitle());
        assertTrue(overviewPage.containsUserWithName("Bob"));
    }

    @Test
    public void deleteUserNonExistingId_Overview() {
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        // WHEN STEP = action
        overviewPage.deleteUserWithIdUrl(5);

        // THEN STEP = result
        assertEquals("Overview", overviewPage.getTitle());
    }
}