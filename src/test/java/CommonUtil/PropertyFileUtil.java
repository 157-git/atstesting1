package CommonUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyFileUtil {

	public String getDataFromPropertyFile(String key) throws IOException  {
       FileInputStream file = new FileInputStream("src\\test\\resources\\login.properties");
     Properties p = new Properties();
     p.load(file);
     String value = p.getProperty(key);
		return value; 
	}
}
