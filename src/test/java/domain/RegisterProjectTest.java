package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterProjectTest {
    private WebDriver driver;

    // Test variables
    String name = "dummy project";
    String startdate = "31-01-2025";
    String enddate = "31-12-2025";

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
    public void given_allFieldsFilledInCorrectly_when_projectAdded_then_projectIsAdded() {
        // GIVEN STEP = context
        RegisterProjectPage registerProjectPage = PageFactory.initElements(driver, RegisterProjectPage.class);
        registerProjectPage.setName(name);
        registerProjectPage.setStartDate(startdate);
        registerProjectPage.setEndDate(enddate);


        // WHEN STEP = action
        registerProjectPage.add();

        // THEN STEP = result
        ProjectOverviewPage projectOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);
        assertEquals("Work Projects", projectOverviewPage.getTitle());
        assertTrue(projectOverviewPage.containsProjectWithName(name));
    }

    @Test
    public void ProjectAddedNotUniqueNameAndTeam_ErrorMessageGiven() {
        // GIVEN STEP = context
        RegisterProjectPage registerProjectPage = PageFactory.initElements(driver, RegisterProjectPage.class);
        registerProjectPage.setName("dummy project");
        registerProjectPage.setStartDate("31-01-2025");
        registerProjectPage.setEndDate("31-12-2025");

        // WHEN STEP = action
        registerProjectPage.add();

        // THEN STEP = result
        assertEquals("Register Project", registerProjectPage.getTitle());
        assertTrue(registerProjectPage.hasErrorMessage("geen unieke combinatie van naam en team"));
        assertTrue(registerProjectPage.hasStickyName("dummy project"));
        assertTrue(registerProjectPage.hasStickyStartDate("31-01-2025"));
        assertTrue(registerProjectPage.hasStickyEndDate("31-12-2025"));
    }

    @Test
    public void given_nameNotFilledIn_when_projectAdded_then_errorMessageGivenForNameAndOtherFieldsValueAreKept() {
        // GIVEN STEP = context
        RegisterProjectPage registerProjectPage = PageFactory.initElements(driver, RegisterProjectPage.class);
        registerProjectPage.setName("");
        registerProjectPage.setStartDate(startdate);
        registerProjectPage.setEndDate(enddate);

        // WHEN STEP = action
        registerProjectPage.add();

        // THEN STEP = result
        assertEquals("Register Project", registerProjectPage.getTitle());
        assertTrue(registerProjectPage.hasErrorMessage("please fill in a name"));
        assertTrue(registerProjectPage.hasStickyStartDate("31-01-2025"));
        assertTrue(registerProjectPage.hasStickyEndDate("31-12-2025"));
    }
}
