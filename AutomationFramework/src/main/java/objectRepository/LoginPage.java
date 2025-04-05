package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	// Constructor
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "user_name")
	private WebElement usernameTextfield;

	@FindAll({ @FindBy(name = "user_password"), @FindBy(xpath = "//input[@type='password']") })
	private WebElement PasswordTextfield;

	@FindBy(id = "submitButton")
	private WebElement LoginButton;

	public WebElement getUsernameTextfield() {
		return usernameTextfield;
	}

	public WebElement getPasswordTextfield() {
		return PasswordTextfield;
	}

	public WebElement getLoginButton() {
		return LoginButton;
	}

}
