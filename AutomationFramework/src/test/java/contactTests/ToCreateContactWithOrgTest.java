package contactTests;

import java.io.IOException;


import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.ContactPage;
import objectRepository.ContactsInfoPage;
import objectRepository.CreateContactPage;
import objectRepository.HomePage;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactWithOrgTest extends BaseClass{
	@Test(groups = "smoke")
	public void toCreateContactwithOrgname_002() throws EncryptedDocumentException, IOException {
		
		
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();
		
		//Click on Create Contact
		ContactPage cp = new ContactPage(driver);
		cp.getContactslookupimage().click();
		
		//To Pass Data in Lastname
		CreateContactPage ccp = new CreateContactPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("contacts", 1, 2);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		
		//To Click on org look up image
		ccp.getOrgnameAddbutton().click();
		WebDriverUtility wutil=new WebDriverUtility();
		
		//To Switch driver control to child window
		wutil.toSwitchWindow(driver, "Accounts");
		
		//To click on TCS
		driver.findElement(By.linkText("Tsc")).click();
		
		//To Switch back driver control to parent
		wutil.toSwitchWindow(driver, "Contacts");
		
		//Save and Verify
		ccp.getSaveButton().click();
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String name = cip.getHeadertext().getText();
//		if(name.contains(LASTNAME)) {
//			System.out.println(name+"--passed--");
//		}else {
//			System.out.println(name+"--failed--");
//		}
		
		//In place of if else for validation we user hard assert for validation
		Assert.assertTrue(name.contains(LASTNAME));
				
		
		
	}

}
