package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.TeamLeadSection;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerImplementation.class)
public class rejectedTest extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void rejectedTest() throws IOException, InterruptedException {
		
		JavascriptExecutor j=(JavascriptExecutor) driver;
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");	
		String URL=pfu.getDataFromPropertyFile("rec_url");

		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		Thread.sleep(2000);
		
		//login page URL
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		//is user login or not
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");

			//click on find candidate
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			hp.FinCan(driver);
			System.out.println("TEST");
			
			//click on rejected candidate
			FindCandidate fc=new FindCandidate(driver);
			fc.rejectedCandidate(driver);
			
			//count the number of candidate
			List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
			//wait for the visibility of candidate
			//w.until(ExpectedConditions.visibilityOfAllElements(initalrows));
			int inititalRowsCount=initalrows.size();
		    System.out.println("rejected candidate inital row count : "+inititalRowsCount);
					
			Thread.sleep(1000);
			
			if(inititalRowsCount!=0) {
				System.out.println("rejected candidate found");
				
				//logout
				Thread.sleep(1000);
				logoutPage tl_lo=new logoutPage(driver);
				tl_lo.logout(driver, "Yes");
				
			}else {
				
				//click on find candidate
				hp.FinCan(driver);
				
				//logout
				Thread.sleep(1000);
				logoutPage tl_lo=new logoutPage(driver);
				tl_lo.logout(driver, "Yes");
				
				//click on window back button
				Thread.sleep(1000);
				driver.navigate().back();

				//login as team leader
				String TL_USERNAME=pfu.getDataFromPropertyFile("username1");
				String TL_PASSWORD=pfu.getDataFromPropertyFile("password1");
				String URL_tl=pfu.getDataFromPropertyFile("tl_url");
				
				//click on team leader
				Thread.sleep(1000);
				TeamLeader tl=new TeamLeader(driver);
				tl.teamLeaderlogin(driver);
				
				//login page URL
				String tl_LoginPageUrl=driver.getCurrentUrl();
				System.out.println(tl_LoginPageUrl);
				
				lp.login(TL_USERNAME, TL_PASSWORD);
				
				Thread.sleep(2000);
				String tl_RecPageUrl=driver.getCurrentUrl();
				System.out.println(tl_RecPageUrl);
				
				//is Team Leader login or not
				if (tl_RecPageUrl.equals(tl_LoginPageUrl)) {
					System.out.println("login failed");
				} else if(tl_RecPageUrl.equals(URL_tl)){
					System.out.println("login successfull");
					
					//click on Team leader section
					TeamLeaderHomePage tl_hp=new TeamLeaderHomePage(driver);
					tl_hp.TeamLeaderSection(driver);
					
					//click on update response
					TeamLeadSection tl_section=new TeamLeadSection(driver);
					tl_section.updateResponse(driver);
					
					//click on search field
					WebElement search = driver.findElement(By.className("search-input"));
					System.out.println("search for rejected candidate");
					search.sendKeys("rejected");
					
					//count the number of row count
					List<WebElement> tl_initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
					int tl_initialRowCount=tl_initalrows.size();
					System.out.println("rejected candidate found :"+tl_initialRowCount);
					
					if (tl_initialRowCount!=0) {
						System.out.println("rejected candidate found");
						
						//select rejected candidate and update data
						WebElement update = driver.findElement(By.xpath("(//button[@class=\"TeamLead-main-table-button\"])[1]"));
						w.until(ExpectedConditions.visibilityOf(update));
						update.click();
						
						//select interview round
						Thread.sleep(1000);
						WebElement interviewRound = driver.findElement(By.xpath("//table[@class=\"min-w-full border-collapse table-auto\"]/tbody/tr/td[2]/select"));
						wdu.handleDropdown(interviewRound, "Hold");
						
						//comments for team leads
						WebElement commentsforTL = driver.findElement(By.name("commentForTl"));
						commentsforTL.sendKeys("not intrested anymore");
					
						//click on update response
						Thread.sleep(2000);
						WebElement updateRes = driver.findElement(By.xpath("//button[text()=\"Update\"]"));
						updateRes.click();
						System.out.println("data updated");
						
						//take screenshot
						Thread.sleep(1000);
						wdu.ScreenShot(driver, "CandidateUpdateToHold");
						
						//click on Team leader section
						Thread.sleep(7000);
						tl_hp.TeamLeaderSection(driver);

						//logout
						Thread.sleep(1000);
						//logoutPage tl_lo=new logoutPage(driver);
						tl_lo.logout(driver, "Yes");
						
						//click on window back button
						Thread.sleep(1000);
						driver.navigate().back();
						
						Thread.sleep(1000);
						r.RecruiterLogin(driver);
						
						//login page URL
						System.out.println(LoginPageUrl);
				
						//enter login details
						Thread.sleep(1000);
						lp.login(USERNAME, PASSWORD);

						Thread.sleep(2000);
						System.out.println(RecPageUrl);

						
					}else {
						System.out.println("no rejected candidate data found");
						
						//clear the search field
						search.sendKeys(Keys.CONTROL+"a"+Keys.BACK_SPACE);
						
						//select first candidate and update data
						Thread.sleep(1000);
						List<WebElement> update = driver.findElements(By.xpath("(//button[@class=\"TeamLead-main-table-button\"])[1]"));
						int before=update.size();
						System.out.println("candidate present before update :"+update.size());
						w.until(ExpectedConditions.visibilityOfAllElements(update));
						
						if (update.isEmpty()) {
							System.out.println("no candidate present to be updated");
						} else {
							System.out.println("candidate found for updating response");
							
							driver.findElement(By.xpath("(//button[@class=\"TeamLead-main-table-button\"])[1]")).click();
						
							//select interview round
							Thread.sleep(1000);
							WebElement interviewRound = driver.findElement(By.xpath("//table[@class=\"min-w-full border-collapse table-auto\"]/tbody/tr/td[2]/select"));
							wdu.handleDropdown(interviewRound, "Rejected");
							
							//comments for team leads
							WebElement commentsforTL = driver.findElement(By.name("commentForTl"));
							commentsforTL.sendKeys("not intrested anymore");
						
							//click on update response
							Thread.sleep(2000);
							WebElement updateRes = driver.findElement(By.xpath("//button[text()=\"Update\"]"));
							updateRes.click();
							
							//take screenshot
							Thread.sleep(1000);
							wdu.ScreenShot(driver, "RejectCandidateUpdate");
							
							Thread.sleep(1000);
							List<WebElement> Afterupdate = driver.findElements(By.xpath("(//button[@class=\"TeamLead-main-table-button\"])[1]"));
							int after = Afterupdate.size();
							System.out.println("candidate present after update :"+Afterupdate.size());
								
							
						}	
						
						//click on Team leader section
						Thread.sleep(7000);
						tl_hp.TeamLeaderSection(driver);
						
						//logout
						Thread.sleep(1000);
						//logoutPage tl_lo=new logoutPage(driver);
						tl_lo.logout(driver, "Yes");
						
						
						
						
					}
				}
			}
			
			
		}
	}

}
