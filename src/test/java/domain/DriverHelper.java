package domain;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverHelper {
    private static WebDriver driver;

    public static WebDriver getDriver(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--lang=en-GB");
        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.chromedriver().setup();
        WebDriver driver;
        driver = new FirefoxDriver();
//        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        return driver;
    }
}