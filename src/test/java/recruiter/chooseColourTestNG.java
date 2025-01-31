package recruiter;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;

public class chooseColourTestNG extends baseClass{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public WebDriver sdriver;
	
	@Test
	public void chooseColour() throws IOException, InterruptedException {
		
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		
		Thread.sleep(2000);
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		//6-12-24 updated
		Thread.sleep(1000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		//.............LOGIN................ 
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			if (lp.getLoginError().isDisplayed()) {
				System.out.println(lp.getLoginError().getText());
			}
			//Assert.fail("Invalid login details");
		} else if(RecPageUrl.equals(URL)) {
			
			System.out.println("login successfull");
			
			//click on add candidate
			RecruiterhomePage hp=new RecruiterhomePage(driver);
			hp.chooseColour(driver);
			
			WebElement colourBox = driver.findElement(By.className("modal-body"));
			if (colourBox.isDisplayed()) {
				List<WebElement> grid = driver.findElements(By.className("colorpickerthemes"));
				for (WebElement colour : grid) {
					String c = colour.getCssValue("background-color");
					System.out.println("COLOUR : "+c);
				}
				
				Thread.sleep(2000);
				WebElement one = driver.findElement(By.xpath("(//div[@class=\"colorpickerthemes\"])[1]"));
				String firstColour=one.getCssValue("background-color");
				one.click();
				System.out.println("FIRST COLOUR : "+firstColour);
				
				Thread.sleep(1000);
				WebElement headerColor = driver.findElement(By.className("daily-timeanddate"));
				String header = headerColor.getCssValue("background-color");
				System.out.println("HEADER COLOUR : "+header);
				
				if (firstColour.equals(header)) {
					System.out.println("COLOUR SUCCESSFULLY UPDATED");
				} else {
					System.out.println("COLOUR NOT UPDATED");
				}
				
			} else {
				System.out.println("choose colour box is not displayed");
				Assert.fail("NO OPTION TO SELECT COLOUR");
			}
		}
		
	}

}
