package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consist of methods related to PropertyFile
 */
public class PropertyFileUtility {
	/**
	 * This method is used to read Data from property file provided Key
	 * @param key
	 * @return
	 * @throws IOException
	 */
	
	public String toReadDataFromPropetyFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commanData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
		
	}

}
