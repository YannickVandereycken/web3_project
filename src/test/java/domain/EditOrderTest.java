package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EditOrderTest {
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
    public void editOrder_allFieldsFilledInCorrectly_orderIsEdited() {
        // GIVEN STEP = context
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);

        //Get latest added order from registerordertest
        int added_id = orderOverviewPage.highestId();

        // WHEN STEP = action
        orderOverviewPage.editOrderWithId(added_id);

        // GIVEN STEP = context
        EditOrderPage editOrderPage = PageFactory.initElements(driver, EditOrderPage.class);
        editOrderPage.setDate(date);
        editOrderPage.setStartTime(starttime);
        editOrderPage.setEndTime(endtime);
        editOrderPage.setDescription("super hard work");

        // WHEN STEP = action
        editOrderPage.update();

        // THEN STEP = result
        assertEquals("Work Orders", orderOverviewPage.getTitle());
        assertTrue(orderOverviewPage.containsOrderWithDescription(description));
    }

    @Test
    public void editOrder_endTimeBlank_errorAndOldValuesKept() {
        // GIVEN STEP = context
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);

        // WHEN STEP = action
        orderOverviewPage.editOrderWithId(1);

        // GIVEN STEP = context
        EditOrderPage editOrderPage = PageFactory.initElements(driver, EditOrderPage.class);
        editOrderPage.setDate(date);
        editOrderPage.setStartTime(starttime);
        editOrderPage.setEndTime("");
        editOrderPage.setDescription(description);

        // WHEN STEP = action
        editOrderPage.update();

        // THEN STEP = result
        assertEquals("Edit Workorder", editOrderPage.getTitle());
        assertTrue(editOrderPage.hasErrorMessage("Please fill in a valid end time"));
        assertTrue(editOrderPage.hasStickyDate("2006-04-03"));
        assertTrue(editOrderPage.hasStickyStartTime("06:00"));
        assertTrue(editOrderPage.hasStickyDescription("dont delete this is for testing"));
    }

    @Test
    public void editOrderNonExistingId_OrderOverview() {
        // GIVEN STEP = context
        OrderOverviewPage orderOverviewPage = PageFactory.initElements(driver, OrderOverviewPage.class);

        // WHEN STEP = action
        orderOverviewPage.editOrderWithIdUrl(4);

        // THEN STEP = result
        assertEquals("Work Orders", orderOverviewPage.getTitle());
    }
}