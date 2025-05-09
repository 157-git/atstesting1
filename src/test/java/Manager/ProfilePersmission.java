package Manager;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_M;
import ObjectRepository_POM.Manager;
import ObjectRepository_POM.loginPage;

public class ProfilePersmission extends baseClass_M{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();

	public WebDriver sdriver;
	
	@Test(enabled = true)
	public void sentProfilePermission() throws IOException, InterruptedException {
		
		// TODO Auto-generated constructor stub
		String USERNAME_m=pfu.getDataFromPropertyFile("not_usernameM");
		String PASSWORD_m=pfu.getDataFromPropertyFile("not_passwordM");				
		String URL_m=pfu.getDataFromPropertyFile("not_urlM");
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
				
				
		Thread.sleep(2000);
		Manager manager=new Manager(driver);
		manager.managerPage(driver);
				
				Thread.sleep(2000);
				String LoginPageUrl=driver.getCurrentUrl();
				System.out.println(LoginPageUrl);
				
				//login
				loginPage lp = new loginPage(driver);
				lp.login(USERNAME_m, PASSWORD_m);

				//6-12-24 updated
				Thread.sleep(2000);
				String homePageUrl_m = driver.getCurrentUrl();
				System.out.println(homePageUrl_m);
				
						
				if (homePageUrl_m.equals(LoginPageUrl)) {
					System.out.println("login failed");
					WebElement error = driver.findElement(By.className("loginpage-error"));
					if (error.isDisplayed()) {
						System.out.println(error.getText());
					}
					//Assert.fail("Invalid login details");
				} else if(homePageUrl_m.equals(URL_m)) {
					System.out.println("login successfull");
					
					Manager m=new Manager(driver);
					wait.until(ExpectedConditions.visibilityOf(m.getManagerSection()));
					m.getManagerSection().click();
					m.getSentProfile().click();
					
					
					WebElement rows = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[2]"));
					if (rows.isDisplayed()) {
						System.out.println("rows present");
						
						driver.findElement(By.xpath("//button[text()=\"Permission Recruiter\"]")).click();
						
						//driver.findElement(By.xpath("//div[text()=\"Send Clients Permission To Recruiter\"]"));
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Send Clients Permission To Recruiter\"]")));
						
						List<WebElement> NoOfJobId = driver.findElements(By.xpath("//tr[@class=\"attendancerows\"]"));
						
						int idpresent=NoOfJobId.size();
						System.out.println("number of job id present :"+idpresent);
						
						for (int i = 1; i < 3 && i < idpresent; i++) {
							List<WebElement> checkbox = driver.findElements(By.xpath("//td[@class=\"tabledata\"]/input"));
							
							if (checkbox.get(i).isEnabled()) {
								checkbox.get(i).click();
								System.out.println("selected");
							} else {
								System.out.println(i+"Already Shared");
								
								driver.findElement(By.xpath("//span[text()=\"Cancel\"]")).click();
								
							}
						}
						
						
						
					}else {
						System.out.println("No Rows are present");
					}
				}
	}
}
