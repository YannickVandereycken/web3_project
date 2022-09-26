import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
/* add dependency to your pom.xml 
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.0.0</version>
        </dependency>
*/

public class SeleniumWorksWellTest {

		private WebDriver driver;

		@Before
		public void setUp() throws Exception {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://nl.wikipedia.org/wiki/Hoofdpagina");
		}


		@After
		public void clean(){
			driver.quit();
		}
		
		@Test 
		public void browserVindtWikipedia() {
			assertEquals("Wikipedia, de vrije encyclopedie", driver.getTitle());
		}

		@Test
		public void wikipediaVindtSelenium() {
			WebElement field = driver.findElement(By.id("searchInput"));
			field.clear();
			field.sendKeys("selenium");
			WebElement link = driver.findElement(By.id("searchButton"));
			link.click();
			
			assertEquals("Selenium - Wikipedia", driver.getTitle());
			
			assertEquals("Selenium", driver.findElement(By.tagName("h1")).getText());

	}

}
