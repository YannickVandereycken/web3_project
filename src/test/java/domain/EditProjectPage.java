package domain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditProjectPage extends Page{
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "starttime")
    private WebElement startTimeField;

    @FindBy(id = "endtime")
    private WebElement endTimeField;

    @FindBy(id = "description")
    private WebElement descriptionField;

    @FindBy(id = "update")
    private WebElement submitButton;

    public EditProjectPage(WebDriver driver) {
        super(driver);
    }

    public void setName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void setStartTime(String starttime) {
        startTimeField.clear();
        startTimeField.sendKeys(starttime);
    }

    public void setEndTime(String endtime) {
        endTimeField.clear();
        endTimeField.sendKeys(endtime);
    }

    public void setDescription(String description) {
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    public void update() {
        submitButton.click();
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("p.alert-danger"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyDate(String name) {
        return name.equals(nameField.getAttribute("value"));
    }

    public boolean hasStickyStartTime(String starttime) {
        return starttime.equals(startTimeField.getAttribute("value"));
    }

    public boolean hasStickyEndTime(String endtime) {
        return endtime.equals(endTimeField.getAttribute("value"));
    }

    public boolean hasStickyDescription(String description) {
        return description.equals(descriptionField.getAttribute("value"));
    }

    public boolean hasEmptyDate() {
        return nameField.getAttribute("value").isEmpty();
    }
}
