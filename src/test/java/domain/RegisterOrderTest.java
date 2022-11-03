package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterOrderTest {
    private WebDriver driver;

    // Test variables
    String date = "2000-10-09";
    String starttime = "10:00";
    String endtime = "14:00";
    String description = "hard work";

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
    public void given_allFieldsFilledInCorrectly_when_orderAdded_then_orderIsAdded() {
        // GIVEN STEP = context
        RegisterOrderPage registerOrderPage = PageFactory.initElements(driver, RegisterOrderPage.class);
        registerOrderPage.setDate(date);
        registerOrderPage.setStartTime(starttime);
        registerOrderPage.setEndTime(endtime);
        registerOrderPage.setDescription(description);

        // WHEN STEP = action
        registerOrderPage.add();

        // THEN STEP = result
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);
        assertEquals("Work Orders", orderOverviewPage.getTitle());
        assertTrue(orderOverviewPage.containsOrderWithDescription(description));
    }

    @Test
    public void OrderAddedWithOverlappingTime_ErrorMessageGiven() {
        // GIVEN STEP = context
        RegisterOrderPage registerOrderPage = PageFactory.initElements(driver, RegisterOrderPage.class);
        registerOrderPage.setDate("2015-11-12");
        registerOrderPage.setStartTime("10:00");
        registerOrderPage.setEndTime("14:00");
        registerOrderPage.setDescription(description);

        // WHEN STEP = action
        registerOrderPage.add();

        // THEN STEP = result
        assertEquals("Register Order", registerOrderPage.getTitle());
        assertTrue(registerOrderPage.hasErrorMessage("Workorder overlaps with other workorder(s)"));
        assertTrue(registerOrderPage.hasStickyDate("2015-11-12"));
        assertTrue(registerOrderPage.hasStickyStartTime("10:00"));
        assertTrue(registerOrderPage.hasStickyEndTime("14:00"));
        assertTrue(registerOrderPage.hasStickyDescription(description));
    }

    @Test
    public void given_dateNotFilledIn_when_orderAdded_then_errorMessageGivenForDateAndOtherFieldsValueAreKept() {
        // GIVEN STEP = context
        RegisterOrderPage registerOrderPage = PageFactory.initElements(driver, RegisterOrderPage.class);
        registerOrderPage.setDate("");
        registerOrderPage.setStartTime(starttime);
        registerOrderPage.setEndTime(endtime);
        registerOrderPage.setDescription(description);

        // WHEN STEP = action
        registerOrderPage.add();

        // THEN STEP = result
        assertEquals("Register Order", registerOrderPage.getTitle());
        assertTrue(registerOrderPage.hasEmptyDate());
        assertTrue(registerOrderPage.hasErrorMessage("Please fill in a valid date"));
        assertTrue(registerOrderPage.hasStickyStartTime(starttime));
        assertTrue(registerOrderPage.hasStickyEndTime(endtime));
        assertTrue(registerOrderPage.hasStickyDescription(description));
    }
}