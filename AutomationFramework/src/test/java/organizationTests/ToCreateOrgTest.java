package organizationTests;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import objectRepository.ContactsInfoPage;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;

import objectRepository.OrgnizationPage;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateOrgTest extends BaseClass {

	@Test(groups = "Regression")
	public void tocreateOrgnameTest_003() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationlink().click();
		OrgnizationPage op = new OrgnizationPage(driver);
		op.getOrganizationlookupimage().click();
		ExcelFileUtility eutil = new ExcelFileUtility();
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		Random r = new Random();
		int random = r.nextInt(1000);
		cop.getOrganizationname().sendKeys(ORGNAME + random);
		cop.getSavebutton().click();
		//fail
		//Assert.fail();
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String orgname = cip.getHeadertext().getText();
//		if (orgname.contains(ORGNAME)) {
//			System.out.println(orgname + "--Passed");
//		} else {
//			System.out.println(orgname + "Failed");
//		}
		
		Assert.assertTrue(orgname.contains(ORGNAME));

	}

}
