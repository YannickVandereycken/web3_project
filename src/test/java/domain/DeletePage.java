package domain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePage extends Page {

    @FindBy(id = "confirm")
    private WebElement confirmButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    public DeletePage(WebDriver driver) {
        super(driver);
    }

    public void confirm() {
        confirmButton.click();
    }

    public void cancel() {
        cancelButton.click();
    }
}