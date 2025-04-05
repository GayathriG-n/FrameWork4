package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadTheDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestdataforDDT.xlsx");
	
	Workbook wb = WorkbookFactory.create(fis);
	
	//call methods
	String lastname = wb.getSheet("Contacts").getRow(1).getCell(2).toString();
	System.out.println(lastname);
	String organization = wb.getSheet("Organization").getRow(1).getCell(2).toString();
	System.out.println(organization);

	}

}
