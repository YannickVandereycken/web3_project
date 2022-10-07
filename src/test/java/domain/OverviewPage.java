package domain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class OverviewPage extends Page {

    public OverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath() + "?command=Overview");
    }

    public void deleteUserWithId(int id) {
        WebElement deleteButton = driver.findElement(By.id("delete"+id));
        deleteButton.click();
    }
    public void deleteUserWithIdUrl(int id) {
        this.driver.get(Config.BASE_URL + "?command=Delete&id="+id);
    }

    public void editUserWithId(int id) {
        WebElement deleteButton = driver.findElement(By.id("update"+id));
        deleteButton.click();
    }
    public void editUserWithIdUrl(int id) {
        this.driver.get(Config.BASE_URL + "?command=Update&id="+id);
    }


    public boolean containsUserWithName(String name) {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found = false;
        for (WebElement listItem : listItems) {
            if (listItem.getText().contains(name)) {
                found = true;
            }
        }
        return found;
    }
}