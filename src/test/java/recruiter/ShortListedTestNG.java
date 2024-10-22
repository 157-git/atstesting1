package recruiter;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.ShortListed;
import ObjectRepository_POM.loginPage;

@Listeners(listenerImplementation.class)
public class ShortListedTestNG extends baseClass {

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;

	
	@Test
	public void shortListed() throws IOException, InterruptedException {
		
		
//		wdu.maximize(driver);
//		wdu.implicitWait(driver);

		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");
		

		
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);

		Thread.sleep(2000);

		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);

		RecruiterhomePage hp = new RecruiterhomePage(driver);
		hp.home(driver);
		System.out.println("TEST");
		
		ShortListed sl = new ShortListed(driver);
		sl.Action(driver);

		String candidateName = driver.findElement(By.name("candidateName")).getAttribute("value");
		eu.writeDataInExcel(1, 0, candidateName);
		
		String candidateEmail=driver.findElement(By.name("candidateEmail")).getAttribute("value");
		eu.writeDataInExcel(1, 1, candidateEmail);
		
		String contactNumber=driver.findElement(By.name("contactNumber")).getAttribute("value");
		eu.writeDataInExcel(1, 2, contactNumber);
		
		String whatsupNumber = driver.findElement(By.name("alternateNumber")).getAttribute("value");
		eu.writeDataInExcel(1, 3, whatsupNumber);
		
		String source = driver.findElement(By.name("sourceName")).getAttribute("value");
		System.out.println("hiiii :"+eu.writeDataInExcel(1, 4, source));
		
		String jobId = driver.findElement(By.name("requirementId")).getAttribute("value");
		eu.writeDataInExcel(1, 5, jobId);
	}

	
	
	
	
}
