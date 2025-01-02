package CommonUtil;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtil {

	public void maximize(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public String ScreenShot(WebDriver driver,String ScreenShotName) throws IOException {
		TakesScreenshot sc=(TakesScreenshot) driver;
		 File tempFile=sc.getScreenshotAs(OutputType.FILE);
		 File destination = new File("./ScreenShot/"+ScreenShotName+".png");
		FileUtils.copyFile(tempFile, destination);
		return destination.getAbsolutePath();
	}
	
	public void mouseHover(WebDriver driver,WebElement element) {
		Actions a=new Actions(driver);
		a.moveToElement(element);
		a.perform();

	}
	
	public void handleDropdown(WebElement element,String targetElement){
		Select s=new Select(element);
		//s.selectByVisibleText(targetElement);
		try {
		    s.selectByVisibleText(targetElement);
		} catch (NoSuchElementException e) {
		    System.out.println("Element not found: " + e.getMessage());
		}
	}
	
	public void switchWindow(WebDriver driver,String expextedUrl) {
		Set<String> ids = driver.getWindowHandles();
		
		for (String string : ids) {
			String actualUrl = driver.switchTo().window(string).getCurrentUrl();
			System.out.println(actualUrl);
			
			if (actualUrl.contains(expextedUrl)) {
				break;
			}
		}	
	}
	
	
}
