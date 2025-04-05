package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	// Constructor
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Contacts")
	private WebElement Contactlink;

	@FindBy(linkText = "Organizations")
	private WebElement Organizationlink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement Logoutlink;

	@FindBy(linkText = "Sign Out")
	private WebElement Signoutlink;

	public WebElement getContactlink() {
		return Contactlink;
	}

	public WebElement getOrganizationlink() {
		return Organizationlink;
	}

	public WebElement getLogoutlink() {
		return Logoutlink;
	}

	public WebElement getSignoutlink() {
		return Signoutlink;
	}

}
