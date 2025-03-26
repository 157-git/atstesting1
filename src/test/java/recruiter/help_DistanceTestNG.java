package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

public class help_DistanceTestNG extends baseClass{
	
	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;

	@Test
	public void help_Distance() throws IOException, InterruptedException {
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
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
			
			//click on add candidate
			RecruiterhomePage hp=new RecruiterhomePage(driver);
			wait.until(ExpectedConditions.visibilityOf(hp.getAddCandidate()));
			hp.addCan(driver);
			
			String JOB_ID= eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 1, 6);
			String LOCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 7);
			
			//select job id
			 WebElement job_id = driver.findElement(By.id("requirementId"));
			 job_id.click();
			 Thread.sleep(1000);
			 wdu.handleDropdown(job_id, JOB_ID);
			 
			 //enter full address
			 Thread.sleep(1000);
			 WebElement location = driver.findElement(By.name("fullAddress"));
			 location.sendKeys(LOCATION);
			 
			 //click on help button
			 Thread.sleep(1000);
			 driver.findElement(By.xpath("//button[text()=\"Help\"]")).click(); 
			 
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mapDiv")));
			
			 
			 
			WebElement map = driver.findElement(By.xpath("//div[@class=\"distance-calculation-bottom-div\"]/iframe"));
			wait.until(ExpectedConditions.visibilityOf(map));
			driver.switchTo().frame(map);
			
			//frame change
			WebElement locations = driver.findElement(By.cssSelector(".directions-card.directions-card-medium-large"));
			wait.until(ExpectedConditions.visibilityOf(locations));
			
			if (locations.isDisplayed()) {
				System.out.println("DISTANCE CALCULATED");
				
				System.out.println(locations.getText());
				
				wdu.ScreenShot(driver, "distanceCalc");
				
				Thread.sleep(1000);
				//switch to default frame
				driver.switchTo().defaultContent();
				
				driver.findElement(By.className("callingTracker-popup-close-btn")).click();
				} else {
				System.out.println("DISTANCE NOT CALCULATED");
			}
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
			
			driver.close();
		}
		
	}
	

}
