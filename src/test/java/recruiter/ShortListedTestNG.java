package recruiter;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.RechomePage;
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

		RechomePage hp = new RechomePage(driver);
		hp.home(driver);
		System.out.println("TEST");
		
		ShortListed sl = new ShortListed(driver);
		sl.Action(driver);

	}

	
	
	
	
}
