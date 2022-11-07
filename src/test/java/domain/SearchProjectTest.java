package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchProjectTest {

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
    public void dateCorrect_foundOneProject() {
        // GIVEN STEP = context
        ProjectOverviewPage projectOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);
        projectOverviewPage.setDate("2025-06-05");
        projectOverviewPage.findProject();

        // THEN STEP = result
        assertEquals("Overview", projectOverviewPage.getTitle());
        assertTrue(projectOverviewPage.containsProjectWithName("Dummy 1"));
    }

    @Test
    public void dateCorrect_foundNoProject() {
        // GIVEN STEP = context
        ProjectOverviewPage projectOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);
        projectOverviewPage.setDate("2020-05-06");
        projectOverviewPage.findProject();

        // THEN STEP = result
        assertEquals("Overview", projectOverviewPage.getTitle());
        assertTrue(projectOverviewPage.hasErrorMessage("Op de ingevulde datum zijn er geen projecten bezig"));
    }
}
