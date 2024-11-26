package CommonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;


public class PropertyFileUtil {

	public String getDataFromPropertyFile(String key) throws IOException  {
       FileInputStream file = new FileInputStream("src\\test\\resources\\login.properties");
     Properties p = new Properties();
     p.load(file);
     String value = p.getProperty(key);
		return value; 
	}
	
	public long fileDetails(String filePath) {
		String path=filePath;
		File file=new File(path);
		 if (file.exists()) {
             long fileSizeInBytes = file.length();
            // System.out.println("File size: " + fileSizeInBytes + " bytes");

             // Convert to KB or MB if needed
             double fileSizeInKB = fileSizeInBytes / 1024.0;
            // double fileSizeInMB = fileSizeInKB / 1024.0;
             System.out.println("File size: " + fileSizeInKB + " KB");
            // System.out.println("File size: " + fileSizeInMB + " MB");
             return fileSizeInBytes;
         } else {
             System.out.println("File not found at: " + path);
             return -1;
         }
	}
}
