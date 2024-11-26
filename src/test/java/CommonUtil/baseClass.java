package CommonUtil;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class baseClass extends listenerImplementation{

	public WebDriver driver;
	public static WebDriver sdriver;
	
	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	
	
	@BeforeSuite
	public void BS() {
		System.out.println("connected to the DataBase");
		dataBaseUtil.connect();
	}
	
	@BeforeClass
	// @Parameters("browser")
	public void BC() throws IOException {   //public void BC(@Optional("true") String browser) throws
		String URL = pfu.getDataFromPropertyFile("url");
		String BROWSER = pfu.getDataFromPropertyFile("browser");
		
		if (BROWSER.equals("chrome")) {
			 driver=new ChromeDriver();
		} else if(BROWSER.equals("Edge")){
		     driver=new EdgeDriver();
		}else {
			 driver=new FirefoxDriver();
		}
	
		wdu.maximize(driver);
		wdu.implicitWait(driver);
		driver.get(URL);
		sdriver=driver;
		System.out.println("before class");
	}
	
	@BeforeMethod			//login to the application
	public void BM() throws IOException, InterruptedException {
		
		System.out.println("before method");
	}
	
	@AfterMethod			//logout from the application
	public void AM() {
		System.out.println("dis-connected from database");
		dataBaseUtil.disconnect();
		
		

//		WebElement img = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
//		wdu.mouseHover(driver, img);
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}
	
	@AfterClass			//close the browser
	public void AC() {
		System.out.println("after class");
	
		
	} 
	
	
	@AfterSuite			//dis-connect from data base
	public void AS() {
		System.out.println("dis-connect from data base");
	}
	

	
			
	
}
