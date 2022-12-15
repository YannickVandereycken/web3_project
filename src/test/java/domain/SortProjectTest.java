package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SortProjectTest {
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
    public void descending_orderCorrect() {
        // GIVEN STEP = context
        ProjectOverviewPage projectOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);
        projectOverviewPage.setOrder("Descending");
        projectOverviewPage.sortProject();

        // THEN STEP = result
        assertEquals("Work Orders", projectOverviewPage.getTitle());
        assertTrue(projectOverviewPage.containsProjectWithName("yes"));
    }

    @Test
    public void ascending_orderCorrect() {
        // GIVEN STEP = context
        ProjectOverviewPage projectOverviewPage = PageFactory.initElements(driver, ProjectOverviewPage.class);
        projectOverviewPage.setOrder("Ascending");
        projectOverviewPage.sortProject();

        // THEN STEP = result
        assertEquals("Work Orders", projectOverviewPage.getTitle());
        assertTrue(projectOverviewPage.containsProjectWithName("yes"));
    }
}
