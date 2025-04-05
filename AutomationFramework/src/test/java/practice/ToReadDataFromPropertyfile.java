package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ToReadDataFromPropertyfile {

	public static void main(String[] args) throws IOException {
		//Create obj of fis
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commanData.properties");
		
		//create an object of Property file
		Properties prop = new Properties();
		
		//Call methods
		prop.load(fis);
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		String BROWSER = prop.getProperty("browser");
		
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		System.out.println(BROWSER);
		
		
		

	}

}
