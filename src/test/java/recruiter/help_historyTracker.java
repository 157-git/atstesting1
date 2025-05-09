package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

public class help_historyTracker extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void historyTracker() throws IOException, InterruptedException {
		
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		
		Thread.sleep(2000);
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		//6-12-24 updated
		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		//.............LOGIN................
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			WebElement error = driver.findElement(By.className("loginpage-error"));
			if (error.isDisplayed()) {
				System.out.println(error.getText());
			}
			//Assert.fail("Invalid login details");
		} else if(RecPageUrl.equals(URL)) {
			
			System.out.println("login successfull");
			
			List<WebElement> alerts = driver.findElements(By.className("ant-modal-content"));
			if (!alerts.isEmpty() && alerts.get(0).isDisplayed()) {
			    WebElement ok = driver.findElement(By.xpath("//div[@class=\"ant-modal-footer\"]/button[2]"));
			    ok.click();
			}
			
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-container")));
			hp.getHelp().click();
			Thread.sleep(1000);
			hp.getHistoryTracker().click();
			
			WebElement page = driver.findElement(By.className("setborderdiv"));
			
			if (page.isDisplayed()) {
				
				//WebElement durationMonth = driver.findElement(By.xpath("//div[@class=\"histry-date-div\"]/label[4]/input"));
				
				WebElement duration = driver.findElement(By.xpath("//div[@class=\"histry-date-div\"]/label[6]/input"));
				duration.click();
				
				if (duration.isSelected()) {
					
					WebElement start = driver.findElement(By.xpath("(//div[@class=\"date-container\"])[1]/input"));
					Thread.sleep(1000);
	        		LocalDate last10DaysDate = LocalDate.now().minusDays(20);  // Get date 10 days ago
	        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Format as needed
	        		String startDate = last10DaysDate.format(formatter);
	        		start.sendKeys(startDate);
	        		System.out.println("start date :"+startDate);
	        		
	        		WebElement end = driver.findElement(By.xpath("(//div[@class=\"date-container\"])[2]/input"));
	        		LocalDate currentDate = LocalDate.now();
                    LocalDate nextDate = currentDate.plusDays(1);
	                // Format the next date to 'YYYY-MM-DD' format
	                String endDate = nextDate.format(formatter);
	                end.sendKeys(endDate);
	                System.out.println("end date :"+endDate);	
				}
				
				//click on filter
				WebElement filter =driver.findElement(By.xpath("//div[@class=\"history-button-container\"]/div[2]"));			
				filter.click();
				
				//is data displayed
				WebElement data = driver.findElement(By.id("issue-containers"));
				
				if (data.isDisplayed()) {
					
					
					List<WebElement> dataRows = driver.findElements(By.xpath("//table[@class=\"can-history-data-table\"]/tbody/tr[1]"));
					if (dataRows.isEmpty()) {
						softAssert.assertTrue(false, "ERROR: NO DATA PRESENT");
						 
					} 	
				}
				
				
			} else {
				 softAssert.assertTrue(false, "ERROR: FAILED TO LOAD PAGE");
			}
			
			//click on filter
			driver.findElement(By.xpath("//button[text()=\"Show Filters\"]")).click();
			
			driver.findElement(By.xpath("//button[text()=\"Clear Filters\"]")).click();
			Thread.sleep(1000);	
			
			List<WebElement> selectFilter = driver.findElements(By.xpath("//div[@class=\"outer-Candi-History-tracker-div\"]/div[3]/label"));
			WebElement list = driver.findElement(By.id("issue-containers"));
			for (WebElement fil : selectFilter) {
				Thread.sleep(500);
				fil.click();
				Thread.sleep(500);
				js.executeScript("arguments[0].scrollIntoView({block: 'end', behavior: 'smooth'});", list);
				Thread.sleep(500);
				js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", fil);
			}
			
			wdu.ScreenShot(driver, "historyTracker");
			
			
			
			//click on logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
			
			softAssert.assertAll("ASSERTION OCCURED");
		}
	}
}
