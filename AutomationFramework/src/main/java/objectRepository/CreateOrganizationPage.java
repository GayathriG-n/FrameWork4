package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {
	// Constructor
	public CreateOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement organizationname;

	@FindBy(name = "industry")
	private WebElement industrydropdown;

	@FindBy(name = "accounttype")
	private WebElement typedropdown;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebutton;

	public WebElement getOrganizationname() {
		return organizationname;
	}

	public WebElement getIndustrydropdown() {
		return industrydropdown;
	}

	public WebElement getTypedropdown() {
		return typedropdown;
	}

	public WebElement getSavebutton() {
		return savebutton;
	}

}
