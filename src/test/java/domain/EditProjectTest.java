package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EditProjectTest {

    private WebDriver driver;

    // Test Variables
    String name = "dummy project";
    String startdate = "31-01-2026";
    String enddate = "31-12-2026";

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
    public void editProject_allFieldsFilledInCorrectly_projectIsEdited() {
        // GIVEN STEP = context
        ProjectOverviewPage projectOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);

        //Get latest added project from registerprojecttest
        int added_id = projectOverviewPage.highestId();

        // WHEN STEP = action
        projectOverviewPage.editProjectWithId(added_id);

        // GIVEN STEP = context
        EditProjectPage editProjectPage = PageFactory.initElements(driver, EditProjectPage.class);
        editProjectPage.setStartDate(startdate);
        editProjectPage.setEndDate(enddate);

        // WHEN STEP = action
        editProjectPage.update();

        // THEN STEP = result
        assertEquals("Projects", projectOverviewPage.getTitle());
        assertTrue(projectOverviewPage.containsProjectWithDate(startdate, enddate));
    }

    @Test
    public void editProject_endDateBlank_errorAndOldValuesKept() {
        // GIVEN STEP = context
        ProjectOverviewPage orderOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);

        // WHEN STEP = action
        orderOverviewPage.editProjectWithId(1);

        // GIVEN STEP = context
        EditProjectPage editProjectPage = PageFactory.initElements(driver, EditProjectPage.class);
        editProjectPage.setName(name);
        editProjectPage.setStartDate(startdate);
        editProjectPage.setEndDate("");

        // WHEN STEP = action
        editProjectPage.update();

        // THEN STEP = result
        assertEquals("Edit Workorder", editProjectPage.getTitle());
        assertTrue(editProjectPage.hasErrorMessage("Please fill in a valid end time"));
        assertTrue(editProjectPage.hasStickyStartDate("31-01-2026"));
        assertTrue(editProjectPage.hasStickyEndDate("31-12-2026"));

    }

    @Test
    public void editProjectNonExistingId_ProjectOverview() {
        // GIVEN STEP = context
        ProjectOverviewPage orderOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);

        // WHEN STEP = action
        orderOverviewPage.editProjectWithIdUrl(4);

        // THEN STEP = result
        assertEquals("Work Projects", orderOverviewPage.getTitle());
    }
}
