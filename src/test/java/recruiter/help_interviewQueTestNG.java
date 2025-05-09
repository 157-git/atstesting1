package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

public class help_interviewQueTestNG extends baseClass{
	
	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	
	@Test
	public void interviewQueTestNG() throws InterruptedException, IOException {
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();
		
		Thread.sleep(2000);
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		//6-12-24 updated
		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		//.............LOGIN................
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			WebElement error = driver.findElement(By.className("loginpage-error"));
			if (error.isDisplayed()) {
				System.out.println(error.getText());
			}
			//Assert.fail("Invalid login details");
		} else if(RecPageUrl.equals(URL)) {
			
			System.out.println("login successfull");
			
			String JOBID =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","InterViewQuestion", 1, 0);
			String INTERVIEWROUND =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","InterViewQuestion", 1, 1);
			String QUESTION =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","InterViewQuestion", 1, 2);
			String REFERENCE =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","InterViewQuestion", 1, 3);
			
			
			List<WebElement> alerts = driver.findElements(By.className("ant-modal-content"));
			if (!alerts.isEmpty() && alerts.get(0).isDisplayed()) {
			    WebElement ok = driver.findElement(By.xpath("//div[@class=\"ant-modal-footer\"]/button[2]"));
			    ok.click();
			}
			
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-container")));
			hp.getHelp().click();
			Thread.sleep(1000);
			hp.getInterViewQuestion().click();			
			
			WebElement page = driver.findElement(By.cssSelector(".container.newcontainerclassforinterviewform"));
			
			if (page.isDisplayed()) {
				//click on job id
				WebElement jobId = driver.findElement(By.xpath("(//select[@class=\"newinputforinterviewquestions\"])[1]"));
				jobId.click();
				Thread.sleep(1000);
				
				//select 1 job id
				WebElement firstJobId = driver.findElement(By.xpath("(//select[@class=\"newinputforinterviewquestions\"])[1]"));
				firstJobId.click();
				wdu.handleDropdown(firstJobId, JOBID);
				
				//select interview round
				Thread.sleep(1000);
				WebElement interviewQuestion = driver.findElement(By.xpath("(//select[@class=\"newinputforinterviewquestions\"])[2]"));
				interviewQuestion.click();
				wdu.handleDropdown(interviewQuestion, INTERVIEWROUND);
				
				//enter a question
				Thread.sleep(1000);
				WebElement question=driver.findElement(By.xpath("//div[@class=\"form-group-Queations\"]/input"));
				question.sendKeys(QUESTION);
				
				Thread.sleep(1000);
				WebElement reference = driver.findElement(By.xpath("//div[@class=\"form-group-reference\"]/input"));
				reference.sendKeys(REFERENCE);
				
				driver.findElement(By.xpath("//button[text()=\"Add Question\"]")).click();
				
				driver.findElement(By.xpath("//button[text()=\"Submit\"]")).click();
				
				WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"Toastify__toast-body\"]/div[2]")));
        		String msg = toastmsg.getText();
        		System.out.println(msg);
        		Thread.sleep(1000);
               // msg = msg.toLowerCase();
                
                if (msg.contains("Interview Question Added Successfully...")) {
//					System.out.println("interview question added successfully");
					wdu.ScreenShot(driver, "interviewQuestion");
				} else {
					 softAssert.assertTrue(false, "ERROR: FAILED TO ADD INTERVIEW QUESTION");
				}
				
				
			} else {
				
				System.out.println("page not displayed");
				
			}
			
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
			
			softAssert.assertAll("ASSERTION OCCURED");
		}
		
	}

}
