package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DeleteOrderTest {
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
    public void deleteOrder_clickConfirm_OverviewAndOrderDeleted() {
        // GIVEN STEP = context
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);

        //Get latest added order from registerordertest
        int added_id = orderOverviewPage.highestId();

        // WHEN STEP = action
        orderOverviewPage.deleteOrderWithId(added_id);

        // Confirm action
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.confirm();

        // THEN STEP = result
        assertEquals("Work Orders", orderOverviewPage.getTitle());
        assertFalse(orderOverviewPage.containsOrderWithDescription("super hard work"));
    }

    @Test
    public void deleteOrder_clickCancel_OverviewAndOrderNotDeleted() {
        // GIVEN STEP = context
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);

        // WHEN STEP = action
        orderOverviewPage.deleteOrderWithId(1);

        // Confirm action
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.cancel();

        // THEN STEP = result
        assertEquals("Work Orders", orderOverviewPage.getTitle());
        assertTrue(orderOverviewPage.containsOrderWithDescription("testing"));
    }

    @Test
    public void deleteOrderNonExistingId_OrderOverview() {
        // GIVEN STEP = context
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);

        // WHEN STEP = action
        orderOverviewPage.deleteOrderWithIdUrl(5);

        // THEN STEP = result
        assertEquals("Work Orders", orderOverviewPage.getTitle());
    }
}