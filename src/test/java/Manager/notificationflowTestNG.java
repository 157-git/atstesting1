package Manager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_M;
import CommonUtil.listnerImplementation_M;
import ObjectRepository_POM.Manager;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listnerImplementation_M.class)
public class notificationflowTestNG extends baseClass_M{

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
				
				Manager m=new Manager(driver);
				m.getManagerSection().click();
				Thread.sleep(1000);		
				m.getUpdateResponse().click();
				
				WebElement name = driver.findElement(By.xpath("(//table[@class=\"attendance-table\"])/tbody/tr[1]/td[3]"));
				String can_name = name.getText();
				System.out.println("CANDIDATE ID : "+can_name);
				
				WebElement name1 = driver.findElement(By.xpath("(//table[@class=\"attendance-table\"])/tbody/tr[1]/td[18]"));
				String rec_name = name1.getText();
				System.out.println("RECRUITER NAME : "+rec_name);
				
				Thread.sleep(1000);
				WebElement update = driver.findElement(By.xpath("(//button[text()=\"Update\"])[1]"));
				update.click();
				
				Thread.sleep(1000);
				WebElement interviewRound = driver.findElement(By.name("interviewRound"));	
				//select interview round
				wdu.handleDropdown(interviewRound, "Hr Round");
				
				Thread.sleep(1000);
				WebElement comments = driver.findElement(By.name("commentForTl"));	
				comments.sendKeys("msg for team lead");
				
				Thread.sleep(1000);
				WebElement responseUpdate = driver.findElement(By.name("responseUpdatedDate"));
				Calendar calendar = Calendar.getInstance();
		        calendar.add(Calendar.DAY_OF_YEAR, 0);  
		        Date todaydate = calendar.getTime();
		        // Format the new date as yyyy-MM-dd
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedDate = dateFormat.format(todaydate);
				// Use JavaScript to set the current date
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].value='" + formattedDate + "';", responseUpdate);
		        
		        Thread.sleep(1000);
		        WebElement interviewDate = driver.findElement(By.name("nextInterviewDate"));	
		        Calendar calendar1 = Calendar.getInstance();
		        calendar1.add(Calendar.DAY_OF_YEAR, 3);  // Add 2 days to today's date
		        Date futureDate = calendar1.getTime();
		        // Format the new date as yyyy-MM-dd
		        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedDate1 = dateFormat1.format(futureDate);
		        js.executeScript("arguments[0].value='" + formattedDate1 + "';", interviewDate);
		        
		        Thread.sleep(1000);
		        WebElement updateRes = driver.findElement(By.xpath("//button[text()=\"Update\"]"));	
		        updateRes.click();
		        
				//logout
				Thread.sleep(1000);
				logoutPage lo=new logoutPage(driver);
				lo.logout(driver, "Yes 009");
				
				Thread.sleep(2000);
				driver.navigate().back();
				System.out.println("back-1");
				Thread.sleep(2000);
				driver.navigate().back();
				System.out.println("back-2");
				Thread.sleep(2000);
				driver.navigate().back();
				
				//.....................login as team leader to check notification.........................
				String USERNAME_tl=pfu.getDataFromPropertyFile("not_usernameTL");
				String PASSWORD_tl=pfu.getDataFromPropertyFile("not_passwordTL");				
				String URL_tl=pfu.getDataFromPropertyFile("not_urlTL");				
				
				Thread.sleep(2000);
				TeamLeader tl=new TeamLeader(driver);
				tl.teamLeaderlogin(driver);
				
				Thread.sleep(2000);
				String LoginPageUrl_tl=driver.getCurrentUrl();
				System.out.println(LoginPageUrl_tl);
				
				//login
				loginPage lp_tl = new loginPage(driver);
				lp_tl.login(USERNAME_tl, PASSWORD_tl);

				
				Thread.sleep(2000);
				String teamleadPageUrl_tl=driver.getCurrentUrl();
				System.out.println(teamleadPageUrl_tl);
				
						
				if (teamleadPageUrl_tl.equals(LoginPageUrl_tl)) {

					WebElement error_msg = driver.findElement(By.className("loginpage-error"));
					Assert.assertTrue(error_msg.isDisplayed(), "error msg not displayed");
					System.out.println("Login failed: " + error_msg.getText());
					
				} else if(teamleadPageUrl_tl.equals(URL_tl)) {
					System.out.println("login successfull");
					
					Thread.sleep(1000);
					WebElement notificationIcon = driver.findElement(By.cssSelector(".ant-badge.css-nqoqt9"));
					notificationIcon.click();
					
					List<WebElement> notification = driver.findElements(By.xpath("//div[@class=\"motificationSubCont1\"]/p"));
					for (WebElement noti : notification) {
						
						if (noti.getText().contains(can_name) && noti.getText().contains(rec_name) && !noti.getText().isEmpty()) {
							System.out.println("notification PRESENT");
							Thread.sleep(2000);
							wdu.ScreenShot(driver, "TeamLeadNotification");
						} else {
							System.out.println("notification ABSENT");
						}	
						
					}
					
					//logout
					Thread.sleep(2000);
					lo.logout(driver, "Yes");
					
				}
				
				Thread.sleep(2000);
				driver.navigate().back();
				System.out.println("back-1");
				Thread.sleep(2000);
				driver.navigate().back();
				System.out.println("back-2");
				Thread.sleep(2000);
				
				
				String USERNAME=pfu.getDataFromPropertyFile("not_username");
				String PASSWORD=pfu.getDataFromPropertyFile("not_password");
				String URL=pfu.getDataFromPropertyFile("not_url");
				
				RecruiterGear r = new RecruiterGear(driver);
				r.RecruiterLogin(driver);
				
				Thread.sleep(2000);
				String LoginPageUrl=driver.getCurrentUrl();
				System.out.println(LoginPageUrl);
				
				loginPage lp = new loginPage(driver);
				lp.login(USERNAME, PASSWORD);
				
				Thread.sleep(2000);
				String RecPageUrl=driver.getCurrentUrl();
				System.out.println(RecPageUrl);
				
				if (RecPageUrl.equals(LoginPageUrl)) {
					WebElement error_msg = driver.findElement(By.className("loginpage-error"));
					Assert.assertTrue(error_msg.isDisplayed(), "error msg not displayed");
					System.out.println("Login failed: " + error_msg.getText());
				} else if(RecPageUrl.equals(URL)){
					System.out.println("LOGIN successfull");
					
					Thread.sleep(2000);
					WebElement notificationIcon = driver.findElement(By.cssSelector(".ant-badge.css-nqoqt9"));
					notificationIcon.click();
					
					//put the fixed text value present in every notification(recruiter Name) or id
					Thread.sleep(2000);
					List<WebElement> notification = driver.findElements(By.xpath("//div[@class=\"motificationSubCont1\"]/p"));
					for (WebElement noti : notification) {
						
					if (noti.getText().contains(can_name) && noti.getText().contains(rec_name) && !noti.getText().isEmpty()) {
						System.out.println("notification PRESENT");
						Thread.sleep(2000);
						wdu.ScreenShot(driver, "superuserNotification");
					} else {
						System.out.println("notification ABSENT");
					}	
					
				}
					
					//logout
					Thread.sleep(1000);
					lo.logout(driver, "Yes");
					
					
				}
			}
			
		}
	

}
