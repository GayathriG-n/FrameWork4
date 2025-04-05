package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	//Constructor
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastnameTextfield;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveButton;
	
//	@FindBy(xpath="//img[@title='Select']")
//	private WebElement OrganizationAddbutton;
	
	@FindBy(xpath="//input[@name='account_name']/../..//img[@title='Select']")
	private WebElement orgnameAddbutton;
	
	public WebElement getLastnameTextfield() {
		return lastnameTextfield;
	}

	public WebElement getSaveButton() {
		return SaveButton;
	}

	public WebElement getOrgnameAddbutton() {
		return orgnameAddbutton;
	}

//	public WebElement getOrganizationAddbutton() {
//		return OrganizationAddbutton;
//	}
	

	
	

}
