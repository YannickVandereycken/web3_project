package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SortOrderTest {
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
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);
        orderOverviewPage.setOrder("Descending");
        orderOverviewPage.sortOrder();

        // THEN STEP = result
        assertEquals("Work Orders", orderOverviewPage.getTitle());
        assertTrue(orderOverviewPage.containsOrderWithDescription("yesn't"));
    }

    @Test
    public void dateCorrect_foundNoProject() {
        // GIVEN STEP = context
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);
        orderOverviewPage.setOrder("Ascending");
        orderOverviewPage.sortOrder();

        // THEN STEP = result
        assertEquals("Work Orders", orderOverviewPage.getTitle());
        assertTrue(orderOverviewPage.containsOrderWithDescription("testing"));
    }
}
