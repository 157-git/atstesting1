package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v118.database.Database;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.DataBase;
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.ShortListed;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerImplementation.class)
public class databaseUpdateNoListTestNG extends baseClass{    //699

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void UpdatedNoOfCandidate() throws IOException, InterruptedException {
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(20));
		String URL="http://93.127.199.85/Dashboard/12/Recruiters";
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
			
			//click on data base
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			//click on dataBase
			hp.dataBase(driver);
			
			
			DataBase db=new DataBase(driver);
			//click on upload files
			db.dbDropdown2(driver);
			System.out.println(".....database......");
			
//			Thread.sleep(1000);
//			
//			//click on view excel
//			db.ExcelView(driver);
//			
//			System.out.println("..........1.........");
//			
			WebElement table = driver.findElement(By.cssSelector("table[class=\"selfcalling-table attendance-table\"]"));
			w.until(ExpectedConditions.visibilityOf(table));
			
			//Locate the table and count initial rows
			 List<WebElement> initialRows = driver.findElements(By.xpath("//table[@class='selfcalling-table attendance-table']/tbody/tr"));
	         int initialRowCount = initialRows.size();
	         System.out.println("Initial row count: " + initialRowCount);
			
	        Thread.sleep(1000);
	        //click on choose file
	        db.dbDropdown(driver);
	        Thread.sleep(1000);
			db.ExcelUpload(driver);
			System.out.println("......choose file......");

	        //click on view 
			Thread.sleep(1000);
			db.dbDropdown2(driver);
	        
			System.out.println(".......uploaded excel viewed.........");
			
	        // Count rows again after adding data
			WebElement table1 = driver.findElement(By.cssSelector("table[class=\"selfcalling-table attendance-table\"]"));
			w.until(ExpectedConditions.visibilityOf(table1));
			
	        List<WebElement> finalRows = driver.findElements(By.xpath("//table[@class='selfcalling-table attendance-table']/tbody/tr"));
	        int finalRowCount = finalRows.size();
	        System.out.println("Final row count: " + finalRowCount);
			
	        // Calculate the difference
	        int rowsAdded = finalRowCount - initialRowCount;
	        System.out.println("Number of rows added: " + rowsAdded);
			
	        //logout............update:-12-9-24----119-122
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
		}	
	}
}
