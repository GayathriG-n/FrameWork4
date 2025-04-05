package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ToSelectEnenrgyDropdown {

	public static void main(String[] args) {
		// Step 1 :- Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Step 2 :- Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();

		// Step 3:- Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4:- Click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 5:-Create Organization with mandatory fields
		Random r = new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys("TCS1"+random);
		
		//Step 6:- Select Energy in the industry dropdown
		WebElement industrydropdown = driver.findElement(By.name("industry"));
		Select industry = new Select(industrydropdown);
		industry.selectByValue("Chemicals");
		
		///Step 7:- Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String organization = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organization.contains("TCS1"+random)) {
			System.out.println(organization+"--passed--");
		}else {
			System.out.println(organization+"---failed--");
		}
		
		//Step 8:-Logout of Application
		
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step 8:- Close Browser
		driver.quit();
	}

	}
