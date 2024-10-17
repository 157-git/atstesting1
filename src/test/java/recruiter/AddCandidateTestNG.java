package recruiter;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.AddCandidate;

@Listeners(listenerImplementation.class)
public class AddCandidateTestNG extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void AddCandidate() throws IOException, InterruptedException {
		//get data from property file
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);

		Thread.sleep(2000);

		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		
		
		RecruiterhomePage hp=new RecruiterhomePage(driver);
		hp.addCan(driver);
		
		//get data from excel file
		String CANDIDATE_NAME =eu.getDataFromExcel("AddCandidate", 0, 1);
		String CANDIDATE_EMAIL =eu.getDataFromExcel("AddCandidate", 1, 1);
	

	    AddCandidate ac=new AddCandidate(driver);
	    ac.CandidateInfo(CANDIDATE_NAME,CANDIDATE_EMAIL);
		
	    Thread.sleep(1000);
	    WebElement sourceName = driver.findElement(By.name("sourceName"));
	    String SOURCE=eu.getDataFromExcel("AddCandidate", 2,1);
	    Thread.sleep(1000);
	    wdu.handleDropdown(sourceName, SOURCE);
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement status = driver.findElement(By.name("selectYesOrNo"));
	    js.executeScript("arguments[0].scrollIntoView();", status);
	    
	    Thread.sleep(1000);
	    WebElement statusType= driver.findElement(By.name("selectYesOrNo"));
	    String STATUS=eu.getDataFromExcel("AddCandidate", 3, 1);
	    Thread.sleep(1000);
	    wdu.handleDropdown(statusType, STATUS);
	}
}
