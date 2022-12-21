package domain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterUserPage extends Page {

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "team")
    private WebElement teamField;

    @FindBy(id = "signUp")
    private WebElement submitButton;

    public RegisterUserPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL + "?command=Register");
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void setTeam(String team){
        Select dropdown = new Select(teamField);
        dropdown.selectByVisibleText(team);
    }

    public void add() {
        submitButton.click();
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("p.alert-danger"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyFirstName(String firstName) {
        return firstName.equals(firstNameField.getAttribute("value"));
    }

    public boolean hasStickyLastName(String lastName) {
        return lastName.equals(lastNameField.getAttribute("value"));
    }

    public boolean hasStickyEmail(String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean hasStickyPassword(String password) {
        return password.equals(passwordField.getAttribute("value"));
    }

    public boolean hasEmptyName() {
        return firstNameField.getAttribute("value").isEmpty();
    }
}