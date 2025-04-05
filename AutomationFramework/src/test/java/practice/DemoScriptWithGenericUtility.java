package practice;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;

public class DemoScriptWithGenericUtility {

	public static void main(String[] args) throws IOException {
		
		// To Read Data from property file
		PropertyFileUtility putil = new PropertyFileUtility();
		String URL = putil.toReadDataFromPropetyFile("url");
		String BROWSER = putil.toReadDataFromPropetyFile("browser");
		String USERNAME = putil.toReadDataFromPropetyFile("username");
		String PASSWORD = putil.toReadDataFromPropetyFile("password");

		// TO read Data from Excel file
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);

		// Step 1:- To Launch browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Step 2;-Login with valid credentials
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 3:-Click on contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 4:- Click on Contact lookup image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 5:- Save and Verify
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (name.contains(LASTNAME)) {
			System.out.println(LASTNAME + "----passed");
		} else {
			System.out.println(PASSWORD + "---failed");
		}
		// Step 6:- Logout from application
		WebElement logoutele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutele).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 7:- close the browser
		driver.quit();

	}

}
