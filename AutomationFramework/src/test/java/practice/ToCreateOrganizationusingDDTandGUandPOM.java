package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrgnizationPage;

public class ToCreateOrganizationusingDDTandGUandPOM {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// To Read data from property file
		String URL = putil.toReadDataFromPropetyFile("url");
		String BROWSER = putil.toReadDataFromPropetyFile("browser");
		String USERNAME = putil.toReadDataFromPropetyFile("username");
		String PASSWORD = putil.toReadDataFromPropetyFile("password");

		// To read data from Excel file
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);

		// Step 1:-to launch browser
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

		// Step 2:- Login with valid credentials
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();

		// Step 3:-Navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.getOrganizationlink().click();

		// Step 4:-Click on create Organization look up image
		OrgnizationPage op = new OrgnizationPage(driver);
		op.getOrganizationlookupimage().click();

		// Step 5:-Create Organization with mandatory fields
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		Random r = new Random();
		int random = r.nextInt(1000);
		cop.getOrganizationname().sendKeys(ORGNAME + random);

		// Step 6:- Save and verify
		cop.getSavebutton().click();
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgname = oip.getOrganizationheader().getText();
		if (orgname.contains(ORGNAME)) {
			System.out.println(orgname + "--passed--");
		} else {
			System.out.println(orgname + "--failed---");
		}

		// Step 7:- Logout
		wutil.toMouseHover(driver, hp.getLogoutlink());
		hp.getSignoutlink().click();

		// Step 8:- close
		driver.quit();

	}

}
