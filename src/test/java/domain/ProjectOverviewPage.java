package domain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class ProjectOverviewPage extends Page {

    @FindBy(id = "date")
    private WebElement dateField;

    @FindBy(id = "find")
    private WebElement findButton;

    @FindBy(id = "order")
    private WebElement orderField;

    @FindBy(id = "sort")
    private WebElement sortButton;

    public ProjectOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL + "?command=ProjectOverview");
    }

    public void deleteProjectWithId(int id) {
        WebElement deleteButton = driver.findElement(By.id("delete" + id));
        deleteButton.click();
    }

    public void deleteProjectWithIdUrl(int id) {
        this.driver.get(Config.BASE_URL + "?command=DeleteP&id=" + id);
    }

    public void editProjectWithId(int id) {
        WebElement deleteButton = driver.findElement(By.id("update" + id));
        deleteButton.click();
    }

    public void editProjectWithIdUrl(int id) {
        this.driver.get(Config.BASE_URL + "?command=UpdateP&id=" + id);
    }


    public boolean containsProjectWithName(String name) {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found = false;
        for (WebElement listItem : listItems) {
            if (listItem.getText().contains(name)) {
                found = true;
            }
        }
        return found;
    }

    public boolean containsProjectWithDate(String startDate, String endDate){
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found1 = false;
        boolean found2 = false;
        for(WebElement listItem : listItems){
            if(listItem.getText().contains(startDate))
                found1=true;
            if(listItem.getText().contains(endDate))
                found2=true;
        }
        return found1&&found2;
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

    public void setDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
    }

    public void findProject() {
        findButton.click();
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("p.alert-danger"));
        return (message.equals(errorMsg.getText()));
    }

    public void setOrder(String order) {
        Select orderF = new Select(orderField);
        orderF.selectByVisibleText(order);
    }

    public void sortProject() {
        sortButton.click();
    }
}
