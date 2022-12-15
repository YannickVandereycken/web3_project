package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchOrderTest {

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
    public void dateCorrect_foundOneOrder() {
        // GIVEN STEP = context
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);
        orderOverviewPage.setDate("2006-04-03");
        orderOverviewPage.findOrder();

        // THEN STEP = result
        assertEquals("Result", orderOverviewPage.getTitle());
        assertTrue(orderOverviewPage.containsOrderWithDescription("dont delete this is for testing"));
    }

    @Test
    public void dateCorrect_foundNoOrder() {
        // GIVEN STEP = context
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);
        orderOverviewPage.setDate("2020-05-06");
        orderOverviewPage.findOrder();

        // THEN STEP = result
        assertEquals("Work Orders", orderOverviewPage.getTitle());
        assertTrue(orderOverviewPage.hasErrorMessage("Op de ingevulde datum zijn er geen workorders bezig"));
    }
}
