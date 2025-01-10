package teamLeader;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_TL;
import CommonUtil.listenerimplementation_TL;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerimplementation_TL.class)
public class sentProfileTestNG extends baseClass_TL{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();

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
							
						//select 1st candidate	
						WebElement row1 = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[1]/input"));
						row1.click();	
						
						//click on send button
						Thread.sleep(1000);
						WebElement send = driver.findElement(By.className("SCE-forward-btn"));
						send.click();
						Thread.sleep(1000);
						
						//wait till the email body is display 
						 WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(30));
						WebElement emailBody = driver.findElement(By.className("modal-content"));
						w.until(ExpectedConditions.visibilityOf(emailBody));
						if (emailBody.isDisplayed()) {
							
							
							//enter email)
							WebElement email = driver.findElement(By.xpath("(//input[@class=\"text-secondary form-control\"])[1]"));
							email.sendKeys("ameetsingh2000@gmail.com");
							Thread.sleep(1000);
							
							//enter carbon copy 
							WebElement cc = driver.findElement(By.xpath("(//input[@class=\"text-secondary form-control\"])[2]"));
							cc.sendKeys("");
							Thread.sleep(1000);
							
							//enter BCC
							WebElement bcc = driver.findElement(By.xpath("//input[@class=\"form-control\"]"));
							bcc.sendKeys("");
							Thread.sleep(1000);
							
							//enter subject 
							WebElement subject = driver.findElement(By.xpath("(//input[@class=\"text-secondary form-control\"])[3]"));
							subject.sendKeys("CANDIDATE DETAILS");
							Thread.sleep(1000);
							
							List<WebElement> list = driver.findElements(By.xpath("//table[@class=\"mt-4 text-secondary table table-sm table-striped table-bordered table-hover\"]/tbody/tr/td[4]"));
							int noOfCandidate=list.size();
							System.out.println("number of candidate :"+noOfCandidate);
							for (WebElement number : list) {
								System.out.println("CANDIDATE NAME :"+number.getText());
							}
							
							
							//scroll down to send email
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
							driver.findElement(By.xpath("//button[text()=\"Send Email\"]")).click();
							w.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify")));
							WebElement tostify = driver.findElement(By.xpath("//div[text()=\"Failed to send email\"]"));
							if (tostify.isDisplayed()) {
								System.out.println("unabled to send data");
							} else {
								System.out.println("data send successfully");
							}
							
							
						} else {
							System.out.println("select atleast one row");
						}
						
						System.out.println("process done ");
						
					} else {
						System.out.println("rows not present");
					}
					
					
					//logout
					Thread.sleep(1000);
					logoutPage lo=new logoutPage(driver);
					lo.logout(driver, "Yes");
					
					
				}
	}

}
