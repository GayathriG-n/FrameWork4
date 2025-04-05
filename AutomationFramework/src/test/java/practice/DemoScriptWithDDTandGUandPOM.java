package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.ContactPage;
import objectRepository.ContactsInfoPage;
import objectRepository.CreateContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class DemoScriptWithDDTandGUandPOM {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// To Read data from property file
		String URL = putil.toReadDataFromPropetyFile("url");
		String BROWSER = putil.toReadDataFromPropetyFile("browser");
		String USERNAME = putil.toReadDataFromPropetyFile("username");
		String PASSWORD = putil.toReadDataFromPropetyFile("password");

		// to read data from excel file

		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);

		// Step 1:- to launch Browser

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

		// Step 2:-Login to application with valid credentials
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();

		// Step 3:-Click on contact link
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		// Step 4:- Click on create contact look up image
		ContactPage cp = new ContactPage(driver);
		cp.getContactslookupimage().click();

		// Step 5:-Fill the mandatory details
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);

		// Step 6:- Save and verify
		ccp.getSaveButton().click();
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String name = cip.getHeadertext().getText();
		if (name.contains(LASTNAME)) {
			System.out.println(name + "---passed--");
		} else {
			System.out.println(name + "--failed");
		}

		// Step 7:- Logout
		wutil.toMouseHover(driver, hp.getLogoutlink());
		hp.getSignoutlink().click();

		//Step 8:-close the browser
		driver.quit();
	}

}
