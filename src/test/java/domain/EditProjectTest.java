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
    String startdate = "2026-01-31";
    String enddate = "2026-12-31";

    String startdateShort = "1/31/26";
    String enddateShort = "12/31/26";

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
        assertEquals("Project Overview", projectOverviewPage.getTitle());
            assertTrue(projectOverviewPage.containsProjectWithDate(startdateShort, enddateShort));
    }

    @Test
    public void editProject_endDateBlank_errorAndOldValuesKept() {
        // GIVEN STEP = context
        ProjectOverviewPage projectOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);

        // WHEN STEP = action
        projectOverviewPage.editProjectWithId(1);

        // GIVEN STEP = context
        EditProjectPage editProjectPage = PageFactory.initElements(driver, EditProjectPage.class);
        editProjectPage.setStartDate(startdate);
        editProjectPage.setEndDate("");

        // WHEN STEP = action
        editProjectPage.update();

        // THEN STEP = result
        assertEquals("Edit Project", editProjectPage.getTitle());
        assertTrue(editProjectPage.hasErrorMessage("Please fill in a valid end date"));
        assertTrue(editProjectPage.hasStickyStartDate("2023-03-15"));
        assertTrue(editProjectPage.hasStickyEndDate("2023-06-29"));

    }

    @Test
    public void editProjectNonExistingId_ProjectOverview() {
        // GIVEN STEP = context
        ProjectOverviewPage projectOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);

        // WHEN STEP = action
        projectOverviewPage.editProjectWithIdUrl(5);

        // THEN STEP = result
        assertEquals("Project Overview", projectOverviewPage.getTitle());
    }
}