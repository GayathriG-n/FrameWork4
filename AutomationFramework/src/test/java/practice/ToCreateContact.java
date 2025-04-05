package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateContact {
	public static void main(String[] args) {
	
	//Step 1 :- Launch Browser
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	//Step 2 :- Login to application with valid credentials
	driver.get("http://localhost:8888/");
	driver.findElement(By.name("user_name")).sendKeys("admin");
	driver.findElement(By.name("user_password")).sendKeys("password");
	driver.findElement(By.id("submitButton")).click();
	
	//Step 3 :- Navigate to contact links
	driver.findElement(By.linkText("Contacts")).click();
	
	//Step 4 :- Click on Create contact lookup image
	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	
	//Step 5 :- Create contacts with mandatory fields
	driver.findElement(By.name("lastname")).sendKeys("Gayathri N");
	
	//Step 6 :- save and verify
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(lastname.contains("Gayathri N")) {
		System.out.println(lastname+"---passed");
	}else {
		System.out.println(lastname+"--failed");
	}
	
	//Step 7 :- Logout from application
	
	WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	Actions action = new Actions(driver);
	action.moveToElement(logoutEle).perform();
	driver.findElement(By.linkText("Sign Out")).click();
	
	//Step 8 :- Close browser
	driver.quit();
	}

}
