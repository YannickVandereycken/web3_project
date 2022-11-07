package domain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class ProjectOverviewPage extends Page {

    @FindBy(id = "date")
    private WebElement dateField;

    @FindBy(id = "find")
    private WebElement findButton;

    public ProjectOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL + "?command=ProjectOverview");
    }

    public void deleteProjectWithId(int id) {
        WebElement deleteButton = driver.findElement(By.id("delete" + id));
        deleteButton.click();
    }

    public void deleteProjectWithIdUrl(int id) {
        this.driver.get(Config.BASE_URL + "?command=DeleteO&id=" + id);
    }

    public void editProjectWithId(int id) {
        WebElement deleteButton = driver.findElement(By.id("update" + id));
        deleteButton.click();
    }

    public void editProjectWithIdUrl(int id) {
        this.driver.get(Config.BASE_URL + "?command=UpdateO&id=" + id);
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
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) this.driver.findElement(By.cssSelector("td"));
        boolean found = false;
        for(WebElement listItem : listItems){
            if(listItem.getText().contains(startDate) && listItem.getText().contains(endDate))
                found=true;
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
}
