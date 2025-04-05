package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToSelectOrganizationfromOrganization {

	public static void main(String[] args) {
		//Step 1:- Launch browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Step 2:-Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3:-Navigate to Contact links
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4:- Click on Create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 5:-Create  contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Gayathri N");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 6:-Select the organization from Organization lookup image
		
		

	}

}
