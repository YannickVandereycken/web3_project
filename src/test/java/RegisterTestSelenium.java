import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
/* add dependency to your pom.xml 
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.0.0</version>
        </dependency>
*/

public class RegisterTestSelenium {
	private WebDriver driver;
	private final String path = "http://localhost:8080/groep2_13_war_exploded/Controller";
	
	@Before
	public void setUp() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get(path+"?command=Register");
	}
	
	@After
	public void clean() {
	    driver.quit();
	}

	@Test
	public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered_and_RoleIsEmployee() {
		submitForm("Jan", "Janssens", "jan.janssens@hotmail.com" , "1234");
		
		String title = driver.getTitle();
		assertEquals("Overview",title);
		
		driver.get(path+"?command=Overview");
		
		ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
		boolean found=false;
		for (WebElement listItem:listItems) {
				if (listItem.getText().contains("jan.janssens@hotmail.com") &&  listItem.getText().contains("Jan") &&  listItem.getText().contains("Janssens") && listItem.getText().contains("Employee")) {
				    found=true;
			}
		}
		assertTrue(found);
	}

	
	@Test
	public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
		submitForm("", "Janssens", "jan.janssens@hotmail.com", "1234");
		
		String title = driver.getTitle();
		assertEquals("Sign Up",title);
		
		WebElement errorMsg = driver.findElement(By.cssSelector("p.alert-danger"));
		assertEquals("No firstname given", errorMsg.getText());

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Janssens",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
	}

	@Test
	public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
		submitForm("John", "", "john.janssens@hotmail.com", "1234");
		
		String title = driver.getTitle();
		assertEquals("Sign Up",title);
		
		WebElement errorMsg = driver.findElement(By.cssSelector("p.alert-danger"));
		assertEquals("No last name given", errorMsg.getText());

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("John",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("john.janssens@hotmail.com",fieldEmail.getAttribute("value"));
	}

	@Test
	public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
		submitForm("Jan", "Janssens", "", "1234");
		
		String title = driver.getTitle();
		assertEquals("Sign Up",title);

		WebElement errorMsg = driver.findElement(By.cssSelector("p.alert-danger"));
		assertEquals("No email given", errorMsg.getText());

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Jan",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Janssens",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("",fieldEmail.getAttribute("value"));
	}


	@Test
	public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
		submitForm("Jan", "Janssens", "jan.janssens@hotmail.com", "");
		
		String title = driver.getTitle();
		assertEquals("Sign Up",title);
		
		WebElement errorMsg = driver.findElement(By.cssSelector("p.alert-danger"));
		assertEquals("No password given", errorMsg.getText());

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Jan",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Janssens",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
	}
	
/*	@Test
	public void test_Register_UserAlreadyExists_ErrorMessageGiven(){
		submitForm("Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");
		
		driver.get(path+"?command=Register");

		submitForm( "Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");
		
//		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
//		assertEquals("User already exists", errorMsg.getText());

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Pieter",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Pieters",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("pieter.pieters@hotmail.com",fieldEmail.getAttribute("value"));
	}*/


	private void fillOutField(String name,String value) {
		WebElement field=driver.findElement(By.id(name));
		field.clear();
		field.sendKeys(value);
	}

	private void submitForm(String firstName,String lastName, String email, String password) {
		fillOutField("firstName", firstName);
		fillOutField("lastName",lastName);
		fillOutField("email", email);
		fillOutField("password", password);

		WebElement button=driver.findElement(By.id("signUp"));
		button.click();
	}

}
