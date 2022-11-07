package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DeleteProjectTest {
    public WebDriver driver;

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
    public void deleteProject_clickConfirm_OverviewAndProjectDeleted() {
        // GIVEN STEP = context
        ProjectOverviewPage orderOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);

        //Get latest added order from registerordertest
        int added_id = orderOverviewPage.highestId();

        // WHEN STEP = action
        orderOverviewPage.deleteProjectWithId(added_id);

        // Confirm action
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.confirm();

        // THEN STEP = result
        assertEquals("Projects", orderOverviewPage.getTitle());
        assertFalse(orderOverviewPage.containsProjectWithName("dummy project"));
    }

    @Test
    public void deleteProject_clickCancel_OverviewAndProjectNotDeleted() {
        // GIVEN STEP = context
        ProjectOverviewPage orderOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);

        // WHEN STEP = action
        orderOverviewPage.deleteProjectWithId(1);

        // Confirm action
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.cancel();

        // THEN STEP = result
        assertEquals("Projects", orderOverviewPage.getTitle());
        assertTrue(orderOverviewPage.containsProjectWithName("dummy project"));
    }

    @Test
    public void deleteProjectNonExistingId_ProjectOverview() {
        // GIVEN STEP = context
        ProjectOverviewPage orderOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);

        // WHEN STEP = action
        orderOverviewPage.deleteProjectWithIdUrl(5);

        // THEN STEP = result
        assertEquals("Projects", orderOverviewPage.getTitle());
    }
}
