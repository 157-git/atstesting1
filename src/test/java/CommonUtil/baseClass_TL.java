package CommonUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

public class baseClass_TL {

	public WebDriver driver;
	public static WebDriver sdriver;
	
	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	private static final String URL = "jdbc:mysql://localhost:3306/Recruiters";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	public Connection connection;
	
	@BeforeSuite
	public Connection BS() {
		System.out.println("connecting to the DataBase");
		
		
		 try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	            if (connection != null) {
	                System.out.println("Database connected successfully!");
	            } else {
	                System.out.println("Failed to make connection to the database.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return connection;
	}
	
	@BeforeClass
	// @Parameters("browser")
	public void BC() throws IOException {   //public void BC(@Optional("true") String browser) throws
		String URL = pfu.getDataFromPropertyFile("updatedUrl");
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
	public void AM() throws InterruptedException {
		System.out.println("dis-connected from database");
		dataBaseUtil.disconnect();

//		WebElement img = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
//		wdu.mouseHover(driver, img);
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}
	
	@AfterClass			//close the browser
	public void AC() {
		System.out.println("after class");
	   // driver.quit();
		
	} 
	
	
	@AfterSuite			//dis-connect from data base
	public void AS() {
		System.out.println("dis-connect from data base");
		 if (connection != null) {
	            try {
	                connection.close();
	                System.out.println("Database connection closed.");
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
		 }
	}
	

}
