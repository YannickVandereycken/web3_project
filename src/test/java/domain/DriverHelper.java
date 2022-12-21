package domain;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverHelper {
    private static WebDriver driver;

    public static WebDriver getDriver() {
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--lang=en-GB");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver(chromeOptions);

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}