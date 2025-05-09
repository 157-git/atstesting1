package teamLeader;

import java.io.File;
import java.io.IOException;

import org.bouncycastle.util.encoders.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import java.nio.file.Files;
import java.nio.file.Paths;

import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.baseClass_TL;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class captchaTest extends baseClass_TL{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	
	public WebDriver sdriver;
	
	@Test(enabled = true)
	public void notificationAvailable() throws IOException, InterruptedException, TesseractException {
		// TODO Auto-generated constructor stub
		String USERNAME=pfu.getDataFromPropertyFile("username1");
		String PASSWORD=pfu.getDataFromPropertyFile("password1");
		String URL=pfu.getDataFromPropertyFile("tl_url");
		
		//updated;3-1-25
		Thread.sleep(2000);
		TeamLeader tl=new TeamLeader(driver);
		tl.teamLeaderPage(driver);
		
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
		
			//WebElement captchaError=driver.findElement(By.className("error"));
			if(lp.getCaptchaError().isDisplayed()) {
				System.out.println("CAPTCHA ERROR");
				lp.getCanvas().click();
				Thread.sleep(500);
				lp.getCaptcha().clear();
				String re_captcha=lp.extractCaptchaText();
				Thread.sleep(500);
				lp.getCaptcha().sendKeys(re_captcha);
				lp.getLoginButton().click();
			}
			
			//WebElement error = driver.findElement(By.className("loginpage-error"));
			if (lp.getLoginError().isDisplayed()) {
				System.out.println("LOGIN ERROR");
				System.out.println(lp.getLoginError().getText());
			}
			
		} else if(teamleadPageUrl.equals(URL)) {
			System.out.println("login successfull");
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
		}
		
	}

}
