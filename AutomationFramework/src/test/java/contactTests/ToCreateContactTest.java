package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import objectRepository.ContactPage;
import objectRepository.ContactsInfoPage;
import objectRepository.CreateContactPage;
import objectRepository.HomePage;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass {
	@Test(groups = "smoke")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getContactslookupimage().click();
		CreateContactPage ccp = new CreateContactPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getSaveButton().click();
 
		//Fail
		Assert.fail();
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String name = cip.getHeadertext().getText();
//		if (name.contains(LASTNAME)) {
//			System.out.println(name + "passed");
//		} else {
//			System.out.println(name + "failed");
//		}
		//instead of if else for validation we user Hard assert
		
	
		Assert.assertTrue(name.contains(LASTNAME));

	}
}
