package recruiter;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import ObjectRepository_POM.loginPage;

@Listeners(listenerImplementation.class)
public class FC_CallingTrackerTestNG extends baseClass{
	
	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;

	@Test
	public void findCandidate ()throws IOException, InterruptedException {
		
		
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
		
		FindCandidate fc=new FindCandidate(driver);
		fc.callTrac(driver);
		fc.actionBtn(driver);
		
		String candidateName = driver.findElement(By.name("candidateName")).getAttribute("value");
		System.out.println(eu.writeDataInExcel("callingTracker",1, 0, candidateName));;
		
		String candidateEmail=driver.findElement(By.name("candidateEmail")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 1, candidateEmail);
		
		String contactNumber=driver.findElement(By.name("contactNumber")).getAttribute("value");
		System.out.println(eu.writeDataInExcel("callingTracker",1, 2, contactNumber));;
		
		String whatsupNumber = driver.findElement(By.name("alternateNumber")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 3, whatsupNumber);
		
		String source = driver.findElement(By.name("sourceName")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 4, source);
		
		String jobId = driver.findElement(By.name("requirementId")).getAttribute("value");
		System.out.println(eu.writeDataInExcel("callingTracker",1, 5, jobId));;
		
		String company=driver.findElement(By.id("requirementCompany")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 6, company);
		
		String callingFeedback = driver.findElement(By.name("callingFeedback")).getAttribute("value");
		System.out.println(eu.writeDataInExcel("callingTracker",1, 7, callingFeedback));;
		
		String dob = driver.findElement(By.name("lineUp.dateOfBirth")).getAttribute("value");
		System.out.println(eu.writeDataInExcel("callingTracker",1, 8, dob));
		
		WebElement gender = driver.findElement(By.name("lineUp.gender"));
		if(gender.isSelected()) {
			String g=gender.getAttribute("value");
			System.out.println(eu.writeDataInExcel("callingTracker",1, 9, g));
		}else {
			eu.writeDataInExcel("callingTracker",1, 9, "");
		}
		
		
		String callSummary=driver.findElement(By.name("lineUp.feedBack")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 10, callSummary);
		
		String education=driver.findElement(By.name("qualification")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 11, education);
		
		String passout=driver.findElement(By.name("lineUp.yearOfPassing")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 12, passout);
		
		String certification=driver.findElement(By.name("lineUp.extraCertification")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 13, certification);
		
		String Currentcompany=driver.findElement(By.name("lineUp.companyName")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 14, Currentcompany);
		
		String TotalExpYear=driver.findElement(By.name("lineUp.experienceYear")).getAttribute("value");
		String TotalExpMonth=driver.findElement(By.name("lineUp.experienceMonth")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 15, TotalExpYear+" year"+","+TotalExpMonth+" month");
		//System.out.println(eu.writeDataInExcel(1, 15, TotalExpMonth));;
		
		String releventExp=driver.findElement(By.name("lineUp.relevantExperience")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 16, releventExp);
		
		String noticePeriod=driver.findElement(By.name("lineUp.noticePeriod")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 17, noticePeriod);
		
		String commRating=driver.findElement(By.name("communicationRating")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 18, commRating);
		
		String CurrentCTCYear=driver.findElement(By.name("lineUp.currentCTCLakh")).getAttribute("value");
		String CurrentCTCMonth=driver.findElement(By.name("lineUp.currentCTCThousand")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 19, CurrentCTCYear+" Lakh"+","+CurrentCTCMonth+" Thousand");
		
		String expectedCTCYear=driver.findElement(By.name("lineUp.expectedCTCLakh")).getAttribute("value");
		String expectedCTCMonth=driver.findElement(By.name("lineUp.expectedCTCThousand")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 20, expectedCTCYear+" Lakh"+","+expectedCTCMonth+" Thousand");
		
		String offerLetter=driver.findElement(By.name("lineUp.holdingAnyOffer")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 21, offerLetter);
		
		String OfferLetterMsg=driver.findElement(By.name("lineUp.offerLetterMsg")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 22, OfferLetterMsg);
		
		String messageForTL=driver.findElement(By.name("lineUp.msgForTeamLeader")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 23, messageForTL);
		
		String statusType=driver.findElement(By.name("selectYesOrNo")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 24, statusType);
		
		String finalStatus=driver.findElement(By.name("lineUp.finalStatus")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 25, finalStatus);
		
		String Interviewdate=driver.findElement(By.name("lineUp.availabilityForInterview")).getAttribute("value");
		eu.writeDataInExcel("callingTracker",1, 26, Interviewdate);
		
		String InterviewTime=driver.findElement(By.cssSelector("div[class=\"ant-picker-input\"]")).getAttribute("value");    //updated
		eu.writeDataInExcel("callingTracker",1, 27, InterviewTime);
		
		Thread.sleep(1000);
		
	}
}
