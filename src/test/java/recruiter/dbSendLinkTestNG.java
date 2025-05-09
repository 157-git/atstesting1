package recruiter;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.DataBase;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerImplementation.class)
public class dbSendLinkTestNG extends baseClass{

	
	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;

	@Test(enabled = false)
	public void dbShareLink() throws IOException, InterruptedException {
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(20));
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			WebElement error = driver.findElement(By.className("loginpage-error"));
			if (error.isDisplayed()) {
				System.out.println(error.getText());
			}
			//Assert.fail("Invalid login details");
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
			
			//click on data base
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			w.until(ExpectedConditions.visibilityOf(hp.getDataBase()));
			//click on dataBase
			hp.dataBase(driver);
			
			
			DataBase db=new DataBase(driver);
			db.shareLink(driver);
			
//			//wait for alert to display
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//	        
//			//handle the alert as ok
//			//Alert popup = driver.switchTo().alert();
//	        Thread.sleep(1000);
//			alert.accept();
			
			driver.navigate().refresh();

			//click on dataBase
			hp.dataBase(driver);
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
			
			
			
		}
		
	}
	
	@Test(enabled = true)
	public void dbCopyLink() throws IOException, InterruptedException {
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(20));
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
					
			//click on data base
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			w.until(ExpectedConditions.visibilityOf(hp.getDataBase()));
			//click on dataBase
			hp.dataBase(driver);
			
			//click on send link and share
			DataBase db=new DataBase(driver);
			db.copyLink(driver);
			
			String clipboardData = "";
	        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        try {
	        	clipboardData = (String) clipboard.getData(DataFlavor.stringFlavor);
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
            System.out.println("Copied Link : " + clipboardData);
            String originalWindow = driver.getWindowHandle();
           
			
			Thread.sleep(2000);
			Robot robot;
			try {
				robot = new Robot();
				//new tab
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				// Switch to the new tab
				Set<String> Window = driver.getWindowHandles();
				System.out.println(Window);
			   for (String new_win : Window) {
				   if (!new_win.equals(originalWindow)) {
					driver.switchTo().window(new_win);
					break;
					}	
			   }
				
				//paste the value
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ENTER);
			
				} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Thread.sleep(2000);
			w.until(ExpectedConditions.visibilityOfElementLocated(By.className("applicant-form-December")));
			String form= driver.getCurrentUrl();
			System.out.println("link on new tab :"+form);
			
			if (clipboardData.equals(form)) {
				System.out.println("linked copied successfully");
				
				wdu.ScreenShot(driver, "copyLink_AF");
			} else {
				System.out.println("linked Not copied");
			}
			
			Thread.sleep(1000);
			driver.switchTo().window(originalWindow);	
			
			//click on dataBase
			Thread.sleep(1000);
			hp.dataBase(driver);
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
			
		}
		
	}

	@Test(enabled = false)
	public void createResume() throws InterruptedException, IOException {
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(20));
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
			
			//click on data base
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			//click on dataBase
			hp.dataBase(driver);
			
			//click on send link and share
			DataBase db=new DataBase(driver);
			db.createResume(driver);
			
			//click on dataBase
			hp.dataBase(driver);
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
			
		}
	}
}
