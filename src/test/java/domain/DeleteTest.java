package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class DeleteTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverHelper.getDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void deleteUser_clickConfirm_OverviewAndUserDeleted() {
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        // WHEN STEP = action
        overviewPage.deleteUserWithId(2);

        // Confirm action
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.confirm();

        // THEN STEP = result
        assertEquals("Overview", overviewPage.getTitle());
        assertFalse(overviewPage.containsUserWithName("Boss"));
    }

    @Test
    public void deleteUser_clickCancel_OverviewAndUserNotDeleted(){
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        // WHEN STEP = action
        overviewPage.deleteUserWithId(4);

        // Confirm action
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.cancel();

        // THEN STEP = result
        assertEquals("Overview", overviewPage.getTitle());
        assertTrue(overviewPage.containsUserWithName("Good"));
    }

    @Test
    public void deleteUserNonExistingId_Overview(){
        // GIVEN STEP = context
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);

        // WHEN STEP = action
        overviewPage.deleteUserWithIdUrl(10);

        // THEN STEP = result
        assertEquals("Overview", overviewPage.getTitle());
    }
}
