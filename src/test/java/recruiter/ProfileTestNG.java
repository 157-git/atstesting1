package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerImplementation.class)
public class ProfileTestNG extends baseClass{
	
	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	
	@Test
	public void TeamPerformance() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		String USERNAME = pfu.getDataFromPropertyFile("not_username");
		String PASSWORD = pfu.getDataFromPropertyFile("not_password");
		String URL=pfu.getDataFromPropertyFile("not_url");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);

		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		
		//6-12-24 updated
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			WebElement error = driver.findElement(By.className("loginpage-error"));
			if (error.isDisplayed()) {
				System.out.println(error.getText());
			}
			//Assert.fail("Invalid login details");	
		} else if(RecPageUrl.equals(URL)){
			
			System.out.println("login successfull");
			
			List<WebElement> alerts = driver.findElements(By.className("ant-modal-content"));
			if (!alerts.isEmpty() && alerts.get(0).isDisplayed()) {
			    WebElement ok = driver.findElement(By.xpath("//div[@class=\"ant-modal-footer\"]/button[2]"));
			    ok.click();
			}


			
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-container")));
			
			//click on user image
			driver.findElement(By.className("user-img")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"View More\"]")));
			
			driver.findElement(By.xpath("//button[text()=\"View More\"]")).click();
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class=\"profile-back-button\"]/button[2]")).click();
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class=\"PIE-date-container\"]/div[3]/label/input")).click();
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class=\"PIE-job-filter\"]/button")).click();
			
			Thread.sleep(1000);
			List<WebElement> jobIds = driver.findElements(By.xpath("//div[@class=\"ant-modal-body\"]/div/label"));
			System.out.println("number of JOB id :"+ jobIds.size());
			if (!jobIds.isEmpty()) {
				
				for (WebElement job : jobIds) {
				    job.click();
				    Thread.sleep(500);
				}
				
				driver.findElement(By.xpath("//div[@class=\"ant-modal-footer\"]/button[2]")).click();
				
				JavascriptExecutor js=(JavascriptExecutor) driver;
				Thread.sleep(1000);
				WebElement performancetable = driver.findElement(By.className("PIT-heading"));
				js.executeScript("arguments[0].scrollIntoView();", performancetable);
				
				Thread.sleep(1000);
				WebElement processTable=driver.findElement(By.className("fixPerformanceprocesstable100"));
				js.executeScript("arguments[0].scrollIntoView();", processTable);
				
				Thread.sleep(1000);
				WebElement canvas=driver.findElement(By.xpath("//div[@class=\"chartDownloaddiv\"]/canvas"));
				js.executeScript("arguments[0].scrollIntoView();", canvas);
				
				
				} else {
				System.out.println("No job id are present");
			}
			
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
			
		}
		
	}

}
