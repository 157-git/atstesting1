package teamLeader;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_TL;
import CommonUtil.listenerimplementation_TL;
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;

@Listeners(listenerimplementation_TL.class)
public class notificationTestNG extends baseClass_TL{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public WebDriver sdriver;
	
	@Test(enabled = false)
	public void findCandidate() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		String USERNAME=pfu.getDataFromPropertyFile("username1");
		String PASSWORD=pfu.getDataFromPropertyFile("password1");
		String URL="http://93.127.199.85/Dashboard/432/TeamLeader";
		
		//updated;3-1-25
		Thread.sleep(2000);
		TeamLeader tl=new TeamLeader(driver);
		tl.teamLeaderPage(driver);//
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		//6-12-24 updated
		Thread.sleep(2000);
		String teamleadPageUrl=driver.getCurrentUrl();
		System.out.println(teamleadPageUrl);
		
				
		if (teamleadPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			//Assert.fail("Invalid login details");
		} else if(teamleadPageUrl.equals(URL)) {
			System.out.println("login successfull");
						
			TeamLeaderHomePage hp=new TeamLeaderHomePage(driver);
			hp.notification(driver);
			System.out.println("TEST");
			
			List<WebElement> msg = driver.findElements(By.className("motificationSubCont1"));
			int MsgCount = msg.size();
			System.out.println("number of Notification : "+MsgCount);
			
			
		}
	}
	
	
	@Test
	public void notificationFlow() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
	String USERNAME=pfu.getDataFromPropertyFile("not_username");
	String PASSWORD=pfu.getDataFromPropertyFile("not_password");
	String URL="http://93.127.199.85/Dashboard/21/Recruiters";
	
	RecruiterGear r = new RecruiterGear(driver);
	r.RecruiterPage(driver);
	
	Thread.sleep(2000);
	String LoginPageUrl=driver.getCurrentUrl();
	System.out.println(LoginPageUrl);
	

	loginPage lp = new loginPage(driver);
	lp.login(USERNAME, PASSWORD);
	
	Thread.sleep(2000);
	String RecPageUrl=driver.getCurrentUrl();
	System.out.println(RecPageUrl);
	
	if (RecPageUrl.equals(LoginPageUrl)) {
		System.out.println("login failed");
	} else if(RecPageUrl.equals(URL)){
		System.out.println("login successfull");
		
		RecruiterhomePage hp = new RecruiterhomePage(driver);
		hp.FinCan(driver);
		System.out.println("TEST");
		
		FindCandidate fc=new FindCandidate(driver);
		fc.callTrac(driver);
		
		
		//count the number of candidate
		List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
		Thread.sleep(1000);
		int inititalRowsCount=initalrows.size();
		System.out.println("recruiter inital row count : "+inititalRowsCount);
		
		if(!(initalrows.isEmpty())) {
			
			//click on edit action
			
			}
		}
	}
		
		
	
}
