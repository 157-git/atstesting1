package Manager;

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
import ObjectRepository_POM.Manager;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

public class ActiveTeamMemberTestNG extends baseClass_M{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	
	public WebDriver sdriver;
	
			@Test
        	public void notificationflowTestNG() throws IOException, InterruptedException {
		
			String USERNAME_m=pfu.getDataFromPropertyFile("not_usernameM");
			String PASSWORD_m=pfu.getDataFromPropertyFile("not_passwordM");				
			String URL_m=pfu.getDataFromPropertyFile("not_urlM");
			
			Thread.sleep(2000);
			Manager manager=new Manager(driver);
			manager.managerPage(driver);
			
			Thread.sleep(2000);
			String loginPageUrl_m = driver.getCurrentUrl();
			System.out.println(loginPageUrl_m);
			
			//login
			Thread.sleep(2000);
			loginPage lp_m = new loginPage(driver);
			lp_m.login(USERNAME_m, PASSWORD_m);
			
			Thread.sleep(2000);
			String homePageUrl_m = driver.getCurrentUrl();
			System.out.println(homePageUrl_m);
			
			if (homePageUrl_m.equals(loginPageUrl_m)) {
				
				WebElement error_msg = driver.findElement(By.className("loginpage-error"));
				Assert.assertTrue(error_msg.isDisplayed(), "error msg not displayed");
				System.out.println("Login failed : " + error_msg.getText());
				
			} else if(homePageUrl_m.equals(URL_m)) {
				System.out.println("login successfull");
				
				
				manager.getActiveTeamMember().click();
				
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
				
				
				WebElement team=driver.findElement(By.xpath("//div[@class=\"role-buttons\"]/button[2]"));
				
				if (team.isDisplayed()) {
					System.out.println("team leader present");
					
					team.click();
					
					Thread.sleep(1000);
							
					List<WebElement> statusList_tl  = driver.findElements(By.xpath("//div[@class=\"status-indicator\"]"));
					int text1 = statusList_tl .size();
					System.out.println("number of teamLeader : "+text1);
					
					if(!statusList_tl.isEmpty()) {
						int loggedInCount = 0;
						int loggedOutCount = 0;
						
						for (WebElement status  : statusList_tl) {
							String statusText=status.getText().trim();					
							if (statusText.equalsIgnoreCase("Login")){
								
								loggedInCount++;
							} else {
								loggedOutCount++;
							}
						}
						System.out.println("Number of teamLeader logged in: " + loggedInCount);
						System.out.println("Number of teamLeader logged out: " + loggedOutCount);
						
					}else {
						System.out.println("Team leader not present");
					}
					
				} 
				//WebElement teamLeaderbox = driver.findElement(By.className("scroll-containerAttendance"));
				
				Thread.sleep(1000);
				logoutPage lo=new logoutPage(driver);
				lo.logout(driver, "Yes");
				
			}
			
			
			}

}
