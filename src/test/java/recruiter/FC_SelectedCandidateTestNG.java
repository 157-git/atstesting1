package recruiter;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.graphbuilder.curve.MultiPath;

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
import ObjectRepository_POM.TeamLeader;
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
		System.out.println("..........1.........");
		
		WebElement mailReceived = driver.findElement(By.cssSelector("select[id=\"mailReceived\"]"));
		mailReceived.click();
		Thread.sleep(500);
		wdu.handleDropdown(mailReceived, "Not Received");
		mailReceived.click();
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		driver.findElement(By.xpath("(//input[@class=\"after-file-input\"])[1]")).click();
//		Thread.sleep(2000);
//		Runtime.getRuntime().exec("C:\\Users\\hp\\Documents\\RecruiterDoc\\AadharCardDoc.exe");
		
		//String aadharCard="C:\\Users\\hp\\Documents\\Ameet_SoftwareTester 1.pdf";
		String aadharCard="C:\\Users\\hp\\Documents\\2mb.pdf";
		long startTime = System.nanoTime();
		driver.findElement(By.xpath("(//input[@type='file'])[1]")).sendKeys(aadharCard);
		long endTime = System.nanoTime();
		long timeTakenInNanoseconds = endTime - startTime;
        double timeTakenInMilliseconds = timeTakenInNanoseconds / 1_000_000.0;
        double seconds = timeTakenInMilliseconds / 1000.0;
        System.out.printf("Time taken to upload the file: %.2f seconds%n", seconds);
		pfu.fileDetails(aadharCard);
		
		
		WebElement panCard = driver.findElement(By.xpath("(//input[@type='file'])[2]"));
		panCard.sendKeys("C:\\Users\\hp\\Documents\\RecruiterDoc\\panCard.jpeg");
		
		WebElement drivingLisence = driver.findElement(By.xpath("(//input[@type='file'])[3]"));
		drivingLisence.sendKeys("C:\\Users\\hp\\Documents\\RecruiterDoc\\driving.pdf");
		
		WebElement degreeMarksheet = driver.findElement(By.xpath("(//input[@type='file'])[4]"));
		degreeMarksheet.sendKeys("C:\\Users\\hp\\Documents\\RecruiterDoc\\driving.pdf");
		
		WebElement HSCmarksheet = driver.findElement(By.xpath("(//input[@type='file'])[5]"));
		HSCmarksheet.sendKeys("C:\\Users\\hp\\Documents\\RecruiterDoc\\driving.pdf");
		
		WebElement SSCmarksheet = driver.findElement(By.xpath("(//input[@type='file'])[6]"));
		SSCmarksheet.sendKeys("C:\\Users\\hp\\Documents\\RecruiterDoc\\driving.pdf");
		
		WebElement OfferLetterReceived=driver.findElement(By.id("offerLetterReceived"));
		OfferLetterReceived.click();
		Thread.sleep(500);
		wdu.handleDropdown(OfferLetterReceived, "Yes");
		
		WebElement OfferLetterAccepted = driver.findElement(By.id("offerLetterAccepted"));
		OfferLetterAccepted.click();
		Thread.sleep(500);
		wdu.handleDropdown(OfferLetterAccepted, "Yes");
		
		WebElement JoinStatus = driver.findElement(By.id("joinStatus"));
		JoinStatus.click();
		Thread.sleep(500);
		wdu.handleDropdown(JoinStatus, "Joining");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement joinDate = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type=\"date\"]")));
		joinDate.click();
		joinDate.sendKeys("2024");
		
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//button[text()=\"Add Documents\"]")).click();
		
	}
	
	
	
	
}
