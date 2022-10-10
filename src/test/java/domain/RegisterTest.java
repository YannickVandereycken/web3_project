package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest {
    private WebDriver driver;

    // Test variables
    String firstName = "bob";
    String lastName = "de bouwer";
    String email = "bob@bouwer.be";
    String password = "bob";

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
    public void given_allFieldsFilledInCorrectly_when_userAdded_then_userIsAdded() {
        // GIVEN STEP = context
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setFirstName(firstName);
        registerPage.setLastName(lastName);
        registerPage.setEmail("bab@bouwer.be");
        registerPage.setPassword(password);

        // WHEN STEP = action
        registerPage.add();

        // THEN STEP = result
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertEquals("Overview", overviewPage.getTitle());
        assertTrue(overviewPage.containsUserWithName(firstName));
    }

    @Test
    public void given_nameNotFilledIn_when_userAdded_then_errorMessageGivenForNameAndOtherFieldsValueAreKept(){
        // GIVEN STEP = context
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setFirstName("");
        registerPage.setLastName(lastName);
        registerPage.setEmail(email);
        registerPage.setPassword(password);

        // WHEN STEP = action
        registerPage.add();

        // THEN STEP = result
        assertEquals("Sign Up", registerPage.getTitle());
        assertTrue(registerPage.hasEmptyName());
        assertTrue(registerPage.hasErrorMessage("No firstname given"));
        assertTrue(registerPage.hasStickyLastName(lastName));
        assertTrue(registerPage.hasStickyEmail(email));
        assertTrue(registerPage.hasStickyPassword(password));
    }
}