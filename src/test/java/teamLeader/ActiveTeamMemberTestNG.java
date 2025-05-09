package teamLeader;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_M;
import CommonUtil.baseClass_TL;
import ObjectRepository_POM.Manager;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

public class ActiveTeamMemberTestNG extends baseClass_TL{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	
	public WebDriver sdriver;
	
			@Test
        	public void activeRecruiters() throws IOException, InterruptedException {
		
				String USERNAME=pfu.getDataFromPropertyFile("not_usernameTL");
				String PASSWORD=pfu.getDataFromPropertyFile("not_passwordTL");
				String URL=pfu.getDataFromPropertyFile("not_urlTL");
				
				
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
					WebElement error = driver.findElement(By.className("loginpage-error"));
					if (error.isDisplayed()) {
						System.out.println(error.getText());
					}
					//Assert.fail("Invalid login details");
				} else if(teamleadPageUrl.equals(URL)) {
					System.out.println("login successfull");
					
					TeamLeaderHomePage hp=new TeamLeaderHomePage(driver);
					hp.getActiveTeamMember().click();
				
				List<WebElement> statusList_r  = driver.findElements(By.xpath("//div[@class=\"status-indicator\"]"));
				int text = statusList_r .size();
				System.out.println("number of recruiters : "+text);
			
				if(!statusList_r.isEmpty()) {
					
					int loggedInCount = 0;
					int loggedOutCount = 0;
					
					for (WebElement status  : statusList_r) {
						String statusText=status.getText().trim();					
						if (statusText.equalsIgnoreCase("Login")){
							
							loggedInCount++;
						} else {
							loggedOutCount++;
						}
					}
					System.out.println("Number of recruiters logged in: " + loggedInCount);
					System.out.println("Number of recruiters logged out: " + loggedOutCount);
				}
				
				//WebElement teamLeaderbox = driver.findElement(By.className("scroll-containerAttendance"));
				
				Thread.sleep(1000);
				logoutPage lo=new logoutPage(driver);
				lo.logout(driver, "Yes");
				
			}
			
			
			}

}
