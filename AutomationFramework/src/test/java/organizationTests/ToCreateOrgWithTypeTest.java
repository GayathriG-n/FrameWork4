package organizationTests;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrgnizationPage;

public class ToCreateOrgWithTypeTest extends BaseClass{
	@Test(groups = "Regression")
	public void toCreateOrgWithType_005() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationlink().click();
		OrgnizationPage op = new OrgnizationPage(driver);
		op.getOrganizationlookupimage().click();
		ExcelFileUtility eutil = new ExcelFileUtility();
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		Random r = new Random();
		int random = r.nextInt(1000);
		cop.getOrganizationname().sendKeys(ORGNAME+random);
		cop.getTypedropdown().click();
		driver.findElement(By.xpath("//option[@value='Competitor']")).click();
		cop.getSavebutton().click();
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgname = oip.getOrganizationheader().getText();
//		if(orgname.contains(ORGNAME)) {
//			System.out.println(orgname+"Passed");
//		}else {
//			System.out.println(orgname+"failed");
//		}
		
		Assert.assertTrue(orgname.contains(ORGNAME));
		
	}

}
