package recruiter;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.loginPage;

@Listeners(listenerImplementation.class)
public class FC_SelectedCandidateTestNG1 extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void selectedCandidateInquery() throws IOException, InterruptedException {
		
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");
		String URL=pfu.getDataFromPropertyFile("rec_url");
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);

		Thread.sleep(2000);

		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);
		
		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);

		Thread.sleep(2000);
		
		//is user login or not
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			WebElement error = driver.findElement(By.className("loginpage-error"));
			if (error.isDisplayed()) {
				System.out.println(error.getText());
			}
			//Assert.fail("Invalid login details");
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
			
			
			RecruiterhomePage hp= new RecruiterhomePage(driver);
			hp.FinCan(driver);
			System.out.println("TEST");
			
			FindCandidate fc=new FindCandidate(driver);
			fc.selectedCand(driver);
			fc.actionBtn(driver);
			System.out.println(".............2...........");
			
			WebElement mailReceived = driver.findElement(By.cssSelector("select[id=\"mailReceived\"]"));
			mailReceived.click();
			Thread.sleep(500);
			wdu.handleDropdown(mailReceived, "Not Received");
			
//			Thread.sleep(500);
//			WebElement aadharCard = driver.findElement(By.xpath("(//input[@class=\"after-file-input\"])[1]"));
//			aadharCard.click();
//			Thread.sleep(3000);
//			Runtime.getRuntime().exec("C:\\Users\\hp\\Documents\\RecruiterDoc\\aadharCard.png");
		}

		
	}	
}
