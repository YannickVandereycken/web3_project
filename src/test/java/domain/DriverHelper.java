package domain;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverHelper {
    private static WebDriver driver;

    public static WebDriver getDriver(){
//        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
        WebDriver driver;
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}