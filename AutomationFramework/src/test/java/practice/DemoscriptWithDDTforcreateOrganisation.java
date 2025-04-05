package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoscriptWithDDTforcreateOrganisation {

	public static void main(String[] args) throws IOException {
		// To Read data from PropertyFile
		FileInputStream pfis = new FileInputStream(".\\src\\test\\resources\\commanData.properties");
		Properties prop = new Properties();
		prop.load(pfis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");

		// To Read data from Excel
		FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\TestdataforDDT.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String ORGNAME = wb.getSheet("Organization").getRow(1).getCell(2).toString();

		// Step 1:- Launch Browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		// Step 2:-Login with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 3:- click on organization link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4:- Click on organization lookup image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 5:-Create a contact with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);

		// Step 6:-Save and verify
		// Step 5:- Verify the Data
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (name.contains(ORGNAME)) {
			System.out.println(ORGNAME + "--passed--");
		} else {
			System.out.println(ORGNAME + "--failed--");
		}

		// Step 6:-Logout from application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// step 7:-close browser
		driver.quit();

	}

}
