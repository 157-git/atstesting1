package recruiter;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.ShortListed;
import ObjectRepository_POM.loginPage;

@Listeners(listenerImplementation.class)
public class FC_SelectedCandidateTestNG extends baseClass{
	
	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;


	@Test
	public void selectedCandidate() throws IOException, InterruptedException {
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");
		
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);

		Thread.sleep(2000);

		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);

		RecruiterhomePage hp = new RecruiterhomePage(driver);
		hp.FinCan(driver);
		System.out.println("TEST");
		
		FindCandidate fc=new FindCandidate(driver);
		fc.selectedCand(driver);
		fc.actionBtn(driver);
		
	}
}
