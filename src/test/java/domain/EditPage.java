package domain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPage extends Page {

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "update")
    private WebElement submitButton;

    public EditPage(WebDriver driver) {
        super(driver);
//        this.driver.get(Config.BASE_URL + "?command=Update");
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

    public void update() {
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

    public boolean hasEmptyName() {
        return firstNameField.getAttribute("value").isEmpty();
    }
    public boolean hasEmptyEmail() {
        return emailField.getAttribute("value").isEmpty();
    }
}