package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;   
import org.testng.annotations.Parameters;

import objectRepository.HomePage;
import objectRepository.LoginPage;

public class BaseClass {
	PropertyFileUtility putil = new PropertyFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sDriver;//listeners

	@BeforeSuite(groups = { "smoke", "Regression" })
	public void beforeSuiteConfiguration() {
		Reporter.log("---DataBse Connection established---", true);
	}

	// @Parameters("browser")//this is used for cross browser testing
	// @BeforeTest //this is used for cross browser testing
	@BeforeClass(groups = { "smoke", "Regression" })
	public void beforeClassConfiguration(/* String BROWSER */) throws IOException {
		String BROWSER = putil.toReadDataFromPropetyFile("browser");
		String URL = putil.toReadDataFromPropetyFile("url");
		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		sDriver = driver;//this is for listeners
		wutil.toMaximize(driver);
		wutil.toWaitElement(driver);
		driver.get(URL);
	}

	@BeforeMethod(groups = { "smoke", "Regression" })
	public void beforeMethodConfiguration() throws IOException {
		String USERNAME = putil.toReadDataFromPropetyFile("username");
		String PASSWORD = putil.toReadDataFromPropetyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		Reporter.log("Logged in successfully", true);

	}

	@AfterMethod(groups = { "smoke", "Regression" })
	public void afterMethodConfiguration() {
		HomePage hp = new HomePage(driver);
		wutil.toMouseHover(driver, hp.getLogoutlink());
		hp.getSignoutlink().click();
		Reporter.log("Logged out successfully", true);

	}

	@AfterClass(groups = { "smoke", "Regression" })
	public void afterClassConfiguration() {
		Reporter.log("Browser got closed Successfully", true);
		driver.quit();
	}

	@AfterSuite(groups = { "smoke", "Regression" })
	public void afterSuiteConfiguration() {
		Reporter.log("---DataBse Connection disconnected---", true);
	}

}
