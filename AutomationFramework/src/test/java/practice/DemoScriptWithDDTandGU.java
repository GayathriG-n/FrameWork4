package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithDDTandGU {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// Read data from property file
		String URL = putil.toReadDataFromPropetyFile("url");
		String BROWSER = putil.toReadDataFromPropetyFile("browser");
		String USERNAME = putil.toReadDataFromPropetyFile("username");
		String PASSWORD = putil.toReadDataFromPropetyFile("password");

		// Read data from Excel file
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);

		// Step 1:- To launch browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		wutil.toMaximize(driver);
		wutil.toWaitElement(driver);

		// Step 2:- Login with application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 3:- Navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();

		
		// Step 4:- Click on create contact lookup image		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		
		// Step 5:- Create a contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		// Step 6:- Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (name.contains(LASTNAME)) {
			System.out.println(name + "--Passed--");
		} else {
			System.out.println(name + "--Failed--");
		}

		// Step 7:-logout from application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, logout);
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 8:-close Browser
		driver.quit();

	}

}
