package domain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditProjectPage extends Page{
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "startdate")
    private WebElement startDateField;

    @FindBy(id = "enddate")
    private WebElement endDateField;

    @FindBy(id = "update")
    private WebElement submitButton;

    public EditProjectPage(WebDriver driver) {
        super(driver);
    }

    public void setName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void setStartDate(String startdate) {
        startDateField.clear();
        startDateField.sendKeys(startdate);
    }

    public void setEndDate(String enddate) {
        endDateField.clear();
        endDateField.sendKeys(enddate);
    }

    public void update() {
        submitButton.click();
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("p.alert-danger"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyName(String name) {
        return name.equals(nameField.getAttribute("value"));
    }

    public boolean hasStickyStartDate(String startdate) {
        return startdate.equals(startDateField.getAttribute("value"));
    }

    public boolean hasStickyEndDate(String enddate) {
        return enddate.equals(endDateField.getAttribute("value"));
    }

    public boolean hasEmptyStartDate() {
        return startDateField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEndDate() {
        return endDateField.getAttribute("value").isEmpty();
    }
}
