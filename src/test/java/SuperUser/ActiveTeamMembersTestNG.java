package SuperUser;

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
import CommonUtil.baseClass_SU;
import ObjectRepository_POM.Manager;
import ObjectRepository_POM.Superuser;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

public class ActiveTeamMembersTestNG extends baseClass_SU{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	
	public WebDriver sdriver;
	
			@Test
        	public void activeTeamMembers() throws IOException, InterruptedException {
		
			String USERNAME_su=pfu.getDataFromPropertyFile("not_usernameSU");
			String PASSWORD_su=pfu.getDataFromPropertyFile("not_passwordSU");				
			String URL_su=pfu.getDataFromPropertyFile("not_urlSU");
			
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
				
				
				superuser.getActiveMember().click();
				
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
				
				WebElement team_m=driver.findElement(By.xpath("//div[@class=\"role-buttons\"]/button[3]"));
				
				if (team_m.isDisplayed()) {
					System.out.println("manager present");
					
					team_m.click();
					
					Thread.sleep(1000);
							
					List<WebElement> statusList_tl  = driver.findElements(By.xpath("//div[@class=\"status-indicator\"]"));
					int text1 = statusList_tl .size();
					System.out.println("number of manager : "+text1);
					
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
						System.out.println("Number of manager logged in: " + loggedInCount);
						System.out.println("Number of manager logged out: " + loggedOutCount);
						
					}else {
						System.out.println("manager not present");
					}
					
				}
				
				
				Thread.sleep(1000);
				logoutPage lo=new logoutPage(driver);
				lo.logout(driver, "Yes");
				
			}
			
			
			}
	

}
