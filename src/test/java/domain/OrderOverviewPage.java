package domain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class OrderOverviewPage extends Page {

    @FindBy(id = "order")
    private WebElement orderField;

    @FindBy(id = "sort")
    private WebElement sortButton;

    public OrderOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath() + "?command=OrderOverview");
    }

    public void deleteOrderWithId(int id) {
        WebElement deleteButton = driver.findElement(By.id("delete" + id));
        deleteButton.click();
    }

    public void deleteOrderWithIdUrl(int id) {
        this.driver.get(Config.BASE_URL + "?command=DeleteO&id=" + id);
    }

    public void editOrderWithId(int id) {
        WebElement deleteButton = driver.findElement(By.id("update" + id));
        deleteButton.click();
    }

    public void editOrderWithIdUrl(int id) {
        this.driver.get(Config.BASE_URL + "?command=UpdateO&id=" + id);
    }


    public boolean containsOrderWithDescription(String description) {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found = false;
        for (WebElement listItem : listItems) {
            if (listItem.getText().contains(description)) {
                found = true;
            }
        }
        return found;
    }

    public int highestId() {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        int max = 5;
        for (WebElement listItem : listItems) {
            try {
                int nr = Integer.parseInt(listItem.getText());
                if (nr > max) {
                    max = nr;
                }
            } catch (IllegalArgumentException e) {
            }
        }
        return max;
    }

    public void setOrder(String order) {
        Select orderF = new Select(orderField);
        orderF.selectByVisibleText(order);
    }

    public void sortOrder() {
        sortButton.click();
    }
}