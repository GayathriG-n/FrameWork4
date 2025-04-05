package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgnizationPage {
	// Constructor
	public OrgnizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText  = "Organizations")
	private WebElement organizationlink;

	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement organizationlookupimage;

	public WebElement getOrganizationlink() {
		return organizationlink;
	}

	public WebElement getOrganizationlookupimage() {
		return organizationlookupimage;
	}

}
