package teamLeader;

import java.io.IOException;
import java.time.Duration;

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
import CommonUtil.baseClass_TL;
import CommonUtil.listenerimplementation_TL;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;

@Listeners(listenerimplementation_TL.class)
public class sentProfileTestNG extends baseClass_TL{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public WebDriver sdriver;
	
	@Test
	public void addJobDescription() throws IOException, InterruptedException {
		
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
					WebElement error = driver.findElement(By.className("loginpage-error"));
					if (error.isDisplayed()) {
						System.out.println(error.getText());
					}
					//Assert.fail("Invalid login details");
				} else if(teamleadPageUrl.equals(URL)) {
					System.out.println("login successfull");
					
					TeamLeaderHomePage hp=new TeamLeaderHomePage(driver);
					Thread.sleep(1000);
					hp.sentProfile(driver);
					
					WebElement rows = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[2]"));
					if (rows.isDisplayed()) {
						System.out.println("rows present to share");
						
						//click on share button
						WebElement share = driver.findElement(By.className("SCE-share-btn"));
						share.click();
						
						//click on selectAll check box
						Thread.sleep(1000);
//						WebElement selectAll = driver.findElement(By.name("selectAll"));
//						selectAll.click();
						WebElement row1 = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[1]/input"));
						row1.click();
						
						//click on send button
						Thread.sleep(1000);
						WebElement send = driver.findElement(By.className("SCE-forward-btn"));
						send.click();
						
						 WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement emailBody = driver.findElement(By.className("modal-content"));
						w.until(ExpectedConditions.visibilityOf(emailBody));
						if (emailBody.isDisplayed()) {
							
							WebElement email = driver.findElement(By.xpath("(//input[@class=\"text-secondary form-control\"])[1]"));
							email.sendKeys("ameetsingh2000@gmail.com");
							
						} else {
							System.out.println("select atleast one row");
						}
						
						
						
					} else {
						System.out.println("rows not present");
					}
					
					
				}
	}

}
