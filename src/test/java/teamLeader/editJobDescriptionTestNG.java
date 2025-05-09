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
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_TL;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

public class editJobDescriptionTestNG extends baseClass_TL {

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public WebDriver sdriver;
	
	@Test
	public void addJobDescription() throws IOException, InterruptedException {
		
		// TODO Auto-generated constructor stub
				String USERNAME=pfu.getDataFromPropertyFile("not_usernameTL");
				String PASSWORD=pfu.getDataFromPropertyFile("not_passwordTL");
				String URL=pfu.getDataFromPropertyFile("not_urlTL");
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				
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
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"loader-container\"]")));
					wait.until(ExpectedConditions.visibilityOf(hp.getFindCandidate()));
					hp.getJobDescription().click();
					hp.getViewJobDescription().click();
					
					List<WebElement> jdcard = driver.findElements(By.className("job-listing"));
					if (!jdcard.isEmpty()) {
						driver.findElement(By.xpath("(//i[@class=\"fa-solid fa-pencil\"])[1]")).click();
						
						WebElement editOption = driver.findElement(By.className("action-joblist-options"));			
						
						if (editOption.isDisplayed()) {
							
							WebElement edit = driver.findElement(By.xpath("//div[@class=\"action-joblist-options\"]/a[1]"));
							edit.click();
							
							WebElement element = driver.findElement(By.xpath("//section[@class='job-performance']//h3[text()='Update Job Description']"));
							 

							
							//..................................?......................................
							Thread.sleep(1000);
							if (element.isDisplayed()){
								System.out.println(element.getText());
								
								WebElement yearOfPassout = driver.findElement(By.name("yearOfPassing"));
								yearOfPassout.clear();
								yearOfPassout.sendKeys("2025");	
								
								//scroll down and click on submit
								Thread.sleep(1000);
								JavascriptExecutor js = (JavascriptExecutor) driver;
								js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
								WebElement submit = driver.findElement(By.xpath("//button[text()=\"Update\"]"));
								Thread.sleep(1000);
								submit.click();
								Thread.sleep(2000);
								wdu.ScreenShot(driver, "AddJobDescription");
								
							} else {
								System.out.println("UPDATE JOB DESCRIPTION PAGE NOT DISPLAYED");
							}
							
							
							
						} else {
							System.out.println("NO OPTION DISPLAYED TO EDIT");
						}
					} else {
						System.out.println("NO JOB DESCRIPTION ARE PRESENT");
					}
					
					
					hp.getJobDescription().click();	        		 
					//logout
					Thread.sleep(1000);
					logoutPage lo=new logoutPage(driver);
					lo.logout(driver, "Yes");
					
				}
	}

}
