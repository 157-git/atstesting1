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
	
	
	@Test(enabled = true)
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
			driver.findElement(By.xpath("//div[@class=\"PIE-date-containerteamperformance\"]/div[3]/label/input")).click();
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class=\"PIE-job-filterteamperformance\"]/button")).click();
			
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
	
	
	
	@Test(enabled =false)
	public void yourAttendance() throws InterruptedException, IOException {
		
		// TODO Auto-generated constructor stub
				String USERNAME = pfu.getDataFromPropertyFile("username");
				String PASSWORD = pfu.getDataFromPropertyFile("password");
				String URL=pfu.getDataFromPropertyFile("rec_url");
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
					
					//click on view more button
					driver.findElement(By.xpath("//button[text()=\"View More\"]")).click();
					
					//click on team attendance button
					Thread.sleep(1000);
					driver.findElement(By.xpath("//div[@class=\"profile-back-button\"]/button[3]")).click();
					
					//select date or duration
					Thread.sleep(1000);
					//driver.findElement(By.xpath("//div[@class=\"PI-radio-buttons\"]/div[4]/label/input")).click();
					WebElement customDate = driver.findElement(By.xpath("//div[@class=\"PI-radio-buttonsattendanceform\"]/div[6]/label/input"));	
					customDate.click();
					
					Thread.sleep(1000);
					if (customDate.isSelected()) {
						
					WebElement start = driver.findElement(By.xpath("(//input[@class=\"date-pickerattendanceform\"])[1]"));
	        		
	        		Thread.sleep(1000);
	        		LocalDate last10DaysDate = LocalDate.now().minusDays(10);  // Get date 10 days ago
	        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Format as needed
	        		String startDate = last10DaysDate.format(formatter);
	        		start.sendKeys(startDate);
	        		System.out.println("start date :"+startDate);
					
					
					WebElement end=driver.findElement(By.xpath("(//input[@class=\"date-pickerattendanceform\"])[2]"));
					LocalDate currentDate = LocalDate.now();
                    LocalDate nextDate = currentDate.plusDays(1);
	                // Format the next date to 'YYYY-MM-DD' format
	                String endDate = nextDate.format(formatter);
	                end.sendKeys(endDate);
	                System.out.println("end date :"+endDate);
					
					}
					
					//click on get attendance
					Thread.sleep(1000);
					driver.findElement(By.xpath("//button[text()=\"Get Attendance\"]")).click();
					
					Thread.sleep(1000);
					wdu.ScreenShot(driver, "your_attendence");
					
					//click on logout
					Thread.sleep(1000);
					logoutPage lo=new logoutPage(driver);
					lo.logout(driver, "Yes");
				}
	}

}
