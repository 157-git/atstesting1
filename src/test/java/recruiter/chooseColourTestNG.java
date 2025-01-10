package recruiter;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;

public class chooseColourTestNG extends baseClass{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public WebDriver sdriver;
	
	@Test
	public void chooseColour() throws IOException, InterruptedException {
		
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		String URL="http://93.127.199.85/Dashboard/12/Recruiters";
		
		Thread.sleep(2000);
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		//6-12-24 updated
		Thread.sleep(1000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		//.............LOGIN................
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			Assert.fail("Invalid login details");//.................
		} else if(RecPageUrl.equals(URL)) {
			
			System.out.println("login successfull");
			
			//click on add candidate
			RecruiterhomePage hp=new RecruiterhomePage(driver);
			hp.chooseColour(driver);
			
			WebElement colourBox = driver.findElement(By.className("modal-body"));
			if (colourBox.isDisplayed()) {
				WebElement grid = driver.findElement(By.className("color-picker-main-div"));
	
			} else {
				System.out.println("choose colour box is not displayed");
				Assert.fail("NO OPTION TO SELECT COLOUR");
			}
		}
		
	}

}
