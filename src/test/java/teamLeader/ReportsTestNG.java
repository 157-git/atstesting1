package teamLeader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class ReportsTestNG extends baseClass_TL{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void reports() throws IOException, InterruptedException {
		
		
		String USERNAME=pfu.getDataFromPropertyFile("username1");
		String PASSWORD=pfu.getDataFromPropertyFile("password1");
		String URL="http://rg.157careers.in/Dashboard/432/TeamLeader";
		
		
		Thread.sleep(2000);
		TeamLeader tl=new TeamLeader(driver);
		tl.teamLeaderPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		
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
						
			Thread.sleep(2000);
			TeamLeaderHomePage hp=new TeamLeaderHomePage(driver);
			//click on report
			hp.getReports().click();
			
			//click on candidate reports
			Thread.sleep(2000);
			WebElement candidateReport = driver.findElement(By.xpath("(//div[text()=\"Candidate Report\"])[1]"));
			candidateReport.click();
			
			//click on current months
			Thread.sleep(2000);
			WebElement currentMonth = driver.findElement(By.id("CurrentMonth"));
			currentMonth.click();
			
			//number recruiters
			Thread.sleep(2000);
			List<WebElement> recruiters = driver.findElements(By.xpath("//div[@class=\"ant-spin-container\"]/ul/li"));
			for (WebElement rec : recruiters) {
				System.out.println("RECRUITER NAME : "+rec.getText());
				
			}
			
			if (!recruiters.isEmpty()) {
				
				//select the check box 
				WebElement checkbox = driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[1]"));
				checkbox.click();
				
				//click on OK
				Thread.sleep(2000);
				WebElement ok = driver.findElement(By.xpath("//span[text()=\"OK\"]"));
				ok.click();
				
				WebElement lineup_colour=driver.findElement(By.xpath("(//li[@class=\"listOfIndex\"])[3]"));				
				String colour1 = lineup_colour.getCssValue("background-color");
				System.out.println(colour1);
				
				WebElement Number_lineup = driver.findElement(By.xpath("(//td[@class=\"tabledata\"])[3]"));
				String number1 = Number_lineup.getText();
				System.out.println(number1);
				
				WebElement canvas = driver.findElement(By.cssSelector("div>canvas"));
				Actions a=new Actions(driver);
				a.moveToElement(canvas);
				a.perform();

		        

				
			}else {
				
				System.out.println("no recruiter present to show report");
				
				//click on cancel
				driver.findElement(By.xpath("//span[text()=\"Cancel\"]")).click();
			
			}
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
		}
	}

}
