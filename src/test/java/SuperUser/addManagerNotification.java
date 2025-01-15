package SuperUser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_M;
import ObjectRepository_POM.Superuser;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

public class addManagerNotification extends baseClass_M{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void notificationflowTestNG() throws IOException, InterruptedException {
		
		String USERNAME_su=pfu.getDataFromPropertyFile("not_usernameSU");
		String PASSWORD_su=pfu.getDataFromPropertyFile("not_passwordSU");				
		String URL_su="http://93.127.199.85/Dashboard/391/SuperUser";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		Thread.sleep(2000);
		Superuser superuser=new Superuser(driver);
		superuser.superuserPage(driver);
		
		Thread.sleep(2000);
		String loginPageUrl_su = driver.getCurrentUrl();
		System.out.println(loginPageUrl_su);
		
		//login
		Thread.sleep(2000);
		loginPage lp_su = new loginPage(driver);
		lp_su.login(USERNAME_su, PASSWORD_su);
		
		Thread.sleep(2000);
		String homePageUrl_su = driver.getCurrentUrl();
		System.out.println(homePageUrl_su);
		
		if (homePageUrl_su.equals(loginPageUrl_su)) {
			
			WebElement error_msg = driver.findElement(By.className("loginpage-error"));
			Assert.assertTrue(error_msg.isDisplayed(), "error msg not displayed");
			System.out.println("Login failed : " + error_msg.getText());
			
		} else if(homePageUrl_su.equals(URL_su)) {
			System.out.println("login successfull");
			
			superuser.getSuperUser().click();
			Thread.sleep(1000);
			superuser.getAddManager().click();
			
			Thread.sleep(1000);
			WebElement name = driver.findElement(By.name("managerName"));
			name.sendKeys("test");
			
			Thread.sleep(1000);
			WebElement desigination = driver.findElement(By.name("designationM"));
			desigination.sendKeys("manager");
			
			Thread.sleep(1000);
			WebElement dateOfJoining = driver.findElement(By.name("dateOfJoiningM"));
//			Date today = new Date();
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String formattedDate = dateFormat.format(today); 
			Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DAY_OF_YEAR, 0);  
	        Date todaydate = calendar.getTime();
	        // Format the new date as yyyy-MM-dd
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate = dateFormat.format(todaydate);
			// Use JavaScript to set the current date
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='" + formattedDate + "';", dateOfJoining);
		
			Thread.sleep(1000);
			WebElement dept = driver.findElement(By.name("departmentM"));
			dept.sendKeys("Sales");
			
			Thread.sleep(1000);
			WebElement jobRole = driver.findElement(By.name("jobRole"));
			wdu.handleDropdown(jobRole, "Manager");
			
			Thread.sleep(1000);
			WebElement personalEmail = driver.findElement(By.name("personalMaliM"));
			personalEmail.sendKeys("manager@gmail.com");
			
			Thread.sleep(1000);
			WebElement personalNumber = driver.findElement(By.name("personalNumberM"));
			personalNumber.sendKeys("1233211233");
			
			Thread.sleep(1000);
			WebElement dob = driver.findElement(By.name("dateOfBirthM"));
			js.executeScript("arguments[0].scrollIntoView();", dob);
			Calendar calendar1 = Calendar.getInstance();
	        calendar1.add(Calendar.DAY_OF_YEAR, 2);  // Add 2 days to today's date
	        Date futureDate = calendar1.getTime();
	        // Format the new date as yyyy-MM-dd
	        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate1 = dateFormat1.format(futureDate);
	        js.executeScript("arguments[0].value='" + formattedDate1 + "';", dob);

	        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	        Thread.sleep(1000);
	        WebElement submit = driver.findElement(By.xpath("//button[text()=\"Submit\"]"));
	       submit.click();
	       
	       WebElement error = driver.findElement(By.xpath("//div[@class=\"Toastify__toast-body\"]"));
	       
	       if (error.isDisplayed()) {
	    	   System.out.println(error.getText());   
	    	   Assert.fail("fill all the required details of manager");
	       	}
	       
	       //logout 
	       wait.until(ExpectedConditions.invisibilityOf(error));
	       superuser.getSuperUser().click();
	       Thread.sleep(1000);
	       logoutPage lo=new logoutPage(driver);
	       lo.logout(driver, "Yes");
		
		}	
			
	}
}
