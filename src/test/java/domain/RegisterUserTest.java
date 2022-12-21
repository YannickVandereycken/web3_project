package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterUserTest {
    private WebDriver driver;

    // Test variables
    String firstName = "bob";
    String lastName = "de bouwer";
    String email = "bob@bouwer.be";
    String password = "bob";

    String team = "Delta";

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
        RegisterUserPage registerUserPage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerUserPage.setFirstName(firstName);
        registerUserPage.setLastName(lastName);
        registerUserPage.setEmail("bab@bouwer.be");
        registerUserPage.setPassword(password);
        registerUserPage.setTeam(team);

        // WHEN STEP = action
        registerUserPage.add();

        // THEN STEP = result
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertEquals("Overview", overviewPage.getTitle());
        assertTrue(overviewPage.containsUserWithName(firstName));
    }

    @Test
    public void given_nameNotFilledIn_when_userAdded_then_errorMessageGivenForNameAndOtherFieldsValueAreKept(){
        // GIVEN STEP = context
        RegisterUserPage registerUserPage = PageFactory.initElements(driver, RegisterUserPage.class);
        registerUserPage.setFirstName("");
        registerUserPage.setLastName(lastName);
        registerUserPage.setEmail(email);
        registerUserPage.setPassword(password);
        registerUserPage.setTeam(team);

        // WHEN STEP = action
        registerUserPage.add();

        // THEN STEP = result
        assertEquals("Sign Up", registerUserPage.getTitle());
        assertTrue(registerUserPage.hasEmptyName());
        assertTrue(registerUserPage.hasErrorMessage("No firstname given"));
        assertTrue(registerUserPage.hasStickyLastName(lastName));
        assertTrue(registerUserPage.hasStickyEmail(email));
        assertTrue(registerUserPage.hasStickyPassword(password));
    }
}