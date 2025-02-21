package videos;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import ObjectRepository_POM.DataBase;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;

public class UploadExcel extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	
	 @Test
     public void uploadExcelDataHover() throws IOException, InterruptedException {
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");
		//WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(20));
		String URL=pfu.getDataFromPropertyFile("rec_url");
		
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
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
			Actions a=new Actions(driver);
			
			Thread.sleep(2000);
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			//hover and click on database
			WebElement uploadfile = driver.findElement(By.xpath("//span[text()=\"Upload Files\"]"));
			a.moveToElement(uploadfile).perform();	
			hp.getDataBase().click();
			
			Thread.sleep(2000);
			DataBase db=new DataBase(driver);
			//hover and click on uploadFiles
			a.moveToElement(db.getUploadFiles()).perform(); 
			db.getUploadFiles().click();
			
			Thread.sleep(1000);
			//select file to upload
			a.moveToElement(db.getChooseExcel());
			db.getChooseExcel().sendKeys("C:\\Users\\hp\\Downloads\\Calling_Tracker_Format (1).xlsx");	
			Thread.sleep(1000);
			if (db.getSheetCheckbox().isDisplayed()) {
				db.getSheetCheckbox().click();
			}
			a.moveToElement(db.getExcelUpload()).click().perform();
		}

   }
}
