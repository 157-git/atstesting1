package recruiter;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.RecruiterhomePage;
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

		RecruiterhomePage hp = new RecruiterhomePage(driver);
		hp.home(driver);
		System.out.println("TEST");
		
		ShortListed sl = new ShortListed(driver);
		sl.Action(driver);

		//.........FETCH ALL THE FIELDS DATA OF CANDIDATE...............
		
		String candidateName = driver.findElement(By.name("candidateName")).getAttribute("value");
		System.out.println(eu.writeDataInExcel(1, 0, candidateName));;
		
		String candidateEmail=driver.findElement(By.name("candidateEmail")).getAttribute("value");
		eu.writeDataInExcel(1, 1, candidateEmail);
		
		String contactNumber=driver.findElement(By.name("contactNumber")).getAttribute("value");
		System.out.println(eu.writeDataInExcel(1, 2, contactNumber));;
		
		String whatsupNumber = driver.findElement(By.name("alternateNumber")).getAttribute("value");
		eu.writeDataInExcel(1, 3, whatsupNumber);
		
		String source = driver.findElement(By.name("sourceName")).getAttribute("value");
		eu.writeDataInExcel(1, 4, source);
		
		String jobId = driver.findElement(By.name("requirementId")).getAttribute("value");
		System.out.println(eu.writeDataInExcel(1, 5, jobId));;
		
		String company=driver.findElement(By.id("requirementCompany")).getAttribute("value");
		eu.writeDataInExcel(1, 6, company);
		
		String callingFeedback = driver.findElement(By.name("callingFeedback")).getAttribute("value");
		System.out.println(eu.writeDataInExcel(1, 7, callingFeedback));;
		
		String dob = driver.findElement(By.name("lineUp.dateOfBirth ")).getAttribute("value");
		eu.writeDataInExcel(1, 8, dob);
		
		WebElement gender = driver.findElement(By.name("lineUp.gender"));
		gender.isSelected();
		String g=gender.getAttribute("value");
		System.out.println(eu.writeDataInExcel(1, 9, g));
		
		String callSummary=driver.findElement(By.name("lineUp.feedBack")).getAttribute("value");
		eu.writeDataInExcel(1, 10, callSummary);
		
		String education=driver.findElement(By.name("qualification")).getAttribute("value");
		eu.writeDataInExcel(1, 11, education);
		
		String passout=driver.findElement(By.name("lineUp.yearOfPassing")).getAttribute("value");
		eu.writeDataInExcel(1, 12, passout);
		
		String certification=driver.findElement(By.name("lineUp.extraCertification")).getAttribute("value");
		eu.writeDataInExcel(1, 13, certification);
		
		String Currentcompany=driver.findElement(By.name("lineUp.companyName")).getAttribute("value");
		eu.writeDataInExcel(1, 14, Currentcompany);
		
		String TotalExpYear=driver.findElement(By.name("lineUp.experienceYear")).getAttribute("value");
		String TotalExpMonth=driver.findElement(By.name("lineUp.experienceMonth")).getAttribute("value");
		eu.writeDataInExcel(1, 15, TotalExpYear+" year"+","+TotalExpMonth+" month");
		//System.out.println(eu.writeDataInExcel(1, 15, TotalExpMonth));;
		
		String releventExp=driver.findElement(By.name("lineUp.relevantExperience")).getAttribute("value");
		eu.writeDataInExcel(1, 16, releventExp);
		
		String noticePeriod=driver.findElement(By.name("lineUp.noticePeriod")).getAttribute("value");
		eu.writeDataInExcel(1, 17, noticePeriod);
		
		String commRating=driver.findElement(By.name("communicationRating")).getAttribute("value");
		eu.writeDataInExcel(1, 18, commRating);
		
		String CurrentCTCYear=driver.findElement(By.name("lineUp.currentCTCLakh")).getAttribute("value");
		String CurrentCTCMonth=driver.findElement(By.name("lineUp.currentCTCThousand")).getAttribute("value");
		eu.writeDataInExcel(1, 19, CurrentCTCYear+" year"+","+CurrentCTCMonth+" month");
		
		String expectedCTCYear=driver.findElement(By.name("lineUp.expectedCTCLakh")).getAttribute("value");
		String expectedCTCMonth=driver.findElement(By.name("lineUp.expectedCTCThousand")).getAttribute("value");
		eu.writeDataInExcel(1, 20, expectedCTCYear+" year"+","+expectedCTCMonth+" month");
		
		String offerLetter=driver.findElement(By.name("lineUp.holdingAnyOffer")).getAttribute("value");
		eu.writeDataInExcel(1, 21, offerLetter);
		
		String OfferLetterMsg=driver.findElement(By.name("lineUp.offerLetterMsg")).getAttribute("value");
		eu.writeDataInExcel(1, 22, OfferLetterMsg);
		
		String messageForTL=driver.findElement(By.name("lineUp.msgForTeamLeader")).getAttribute("value");
		eu.writeDataInExcel(1, 23, messageForTL);
		
		String statusType=driver.findElement(By.name("selectYesOrNo")).getAttribute("value");
		eu.writeDataInExcel(1, 24, statusType);
		
		String finalStatus=driver.findElement(By.name("lineUp.finalStatus")).getAttribute("value");
		eu.writeDataInExcel(1, 25, finalStatus);
		
		String Interviewdate=driver.findElement(By.name("lineUp.availabilityForInterview")).getAttribute("value");
		eu.writeDataInExcel(1, 26, Interviewdate);
		
		String InterviewTime=driver.findElement(By.name("lineUp.interviewTime")).getAttribute("value");
		eu.writeDataInExcel(1, 27, InterviewTime);
		
		Thread.sleep(1000);
		
		//............UPDATE THE CANDIDATE DATA............
		
		//message for team lead
		String CANDIDATENAME = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 0);
		String CANDIDATEEMAIL = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 1);
		String CONTACTNUMBER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 2);
		String WHATSUPNUMBER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 3);
		String SOURCE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 4);
		String JOBID = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 5);
		String COMPANY = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 6);
		String CALLINGFEEDBACK = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 7);
		String DOB = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 8);
		String GENDER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 9);
		String CALLSUMMARY = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 10);
		String EDUCATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 11);
		String PASSOUT = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 12);
		String CERTIFICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 13);
		String CURRENTCOMPANY = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 14);
		String TOTALEXPYEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 15);
		String TOTALEXPMONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 15);
		String RELEVENTEXP = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 16);
		String NOTICEPERIOD = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 17);
		String COMMRATING = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 18);
		String CURRENTCTCYEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 19);
		String CURRENTCTCMONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 19);
		String EXPECTEDCTCYEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 20);
		String EXPECTEDCTCMONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 20);
		String OFFERLETTER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 21);
		String OFFERLETTERMSG = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 22);
		String MESSAGEFORTL = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 23);
		String STATUSTYPE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 24);
		String FINALSTATUS = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 25);
		String INTERVIEWDATE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 26);
		String INTERVIEWTIME = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "shortlisted", 2, 27);
		
		//............UPDATE DATA IN THE FIELD FROM EXCEL SHEET...............
		
		WebElement candidate_Name = driver.findElement(By.name("candidateName"));
		candidate_Name.clear();
		candidate_Name.sendKeys(CANDIDATENAME);
		
		WebElement candidate_Email=driver.findElement(By.name("candidateEmail"));
		candidate_Email.clear();
		candidate_Email.sendKeys(CANDIDATEEMAIL);

		WebElement contact_Number=driver.findElement(By.name("contactNumber"));
		contact_Number.clear();
		contact_Number.sendKeys(CONTACTNUMBER);

		
		WebElement whatsup_Number = driver.findElement(By.name("alternateNumber"));
		whatsup_Number.clear();
		whatsup_Number.sendKeys(WHATSUPNUMBER);
		
		WebElement Source = driver.findElement(By.name("sourceName"));
		Source.click();
	    Thread.sleep(1000);
		wdu.handleDropdown(Source, SOURCE);
		System.out.println("//.........1......");
		
		WebElement job_Id = driver.findElement(By.name("requirementId"));
		Source.click();
		Thread.sleep(1000);
		wdu.handleDropdown(job_Id, JOBID);
		System.out.println("...........2......");
		
		WebElement calling_Feedback = driver.findElement(By.name("callingFeedback"));
		calling_Feedback.click();
		Thread.sleep(1000);
		wdu.handleDropdown(calling_Feedback, CALLINGFEEDBACK);
		
		WebElement d_o_b = driver.findElement(By.name("lineUp.dateOfBirth "));
		d_o_b.click();
		d_o_b.sendKeys(DOB);
		System.out.println("//...........3............");
		
//		List<WebElement> Gender = driver.findElements(By.name("lineUp.gender"));
//		if (checkbox.getAttribute("value").equalsIgnoreCase(gender)) {
//		for (WebElement checkbox : Gender) {
//			if (checkbox.isSelected()) {
//				checkbox.click();
//				System.out.println("equal");
//		
//			} else if(!checkbox.isSelected()){
//				
//				checkbox.click();
//				System.out.println("not equal");
//			
//			}
//			System.out.println("gender not found");
//			break;
//		}
//		}
		
		System.out.println("//..........4........");
		
		WebElement call_Summary=driver.findElement(By.name("lineUp.feedBack"));
		call_Summary.clear();
		call_Summary.sendKeys(CALLSUMMARY);
		
		WebElement Education=driver.findElement(By.name("qualification"));
		Education.click();
		Thread.sleep(1000);
		wdu.handleDropdown(Education, EDUCATION);
		
		WebElement Passout=driver.findElement(By.name("lineUp.yearOfPassing"));
		Passout.clear();
		Passout.sendKeys(PASSOUT);
		
		WebElement Certification=driver.findElement(By.name("lineUp.extraCertification"));
		Certification.clear();
		Certification.sendKeys(CERTIFICATION);
		
		WebElement Current_company=driver.findElement(By.name("lineUp.companyName"));
		Current_company.clear();
		Current_company.sendKeys(CURRENTCOMPANY);
		
		WebElement TotalExp_Year=driver.findElement(By.name("lineUp.experienceYear"));
		WebElement TotalExp_Month=driver.findElement(By.name("lineUp.experienceMonth"));
		TotalExp_Year.clear();
		TotalExp_Month.clear();
		TotalExp_Year.sendKeys(TOTALEXPYEAR);
		TotalExp_Month.sendKeys(TOTALEXPMONTH);
		
		
		WebElement relevent_Exp=driver.findElement(By.name("lineUp.relevantExperience"));
		
		
		WebElement notice_Period=driver.findElement(By.name("lineUp.noticePeriod"));
		
		
		WebElement comm_Rating=driver.findElement(By.name("communicationRating"));
		
		
		WebElement CurrentCTC_Year=driver.findElement(By.name("lineUp.currentCTCLakh"));
		WebElement CurrentCTC_Month=driver.findElement(By.name("lineUp.currentCTCThousand"));
		
		
		WebElement expectedCTC_Year=driver.findElement(By.name("lineUp.expectedCTCLakh"));
		WebElement expectedCTC_Month=driver.findElement(By.name("lineUp.expectedCTCThousand"));
		
		
		WebElement offer_Letter=driver.findElement(By.name("lineUp.holdingAnyOffer"));
		
		
		WebElement OfferLetter_Msg=driver.findElement(By.name("lineUp.offerLetterMsg"));
		
		
		WebElement messageFor_TL=driver.findElement(By.name("lineUp.msgForTeamLeader"));
		
		
		WebElement status_Type=driver.findElement(By.name("selectYesOrNo"));
		
		
		WebElement final_Status=driver.findElement(By.name("lineUp.finalStatus"));
		
		
		WebElement Interview_date=driver.findElement(By.name("lineUp.availabilityForInterview"));
		
		WebElement Interview_Time=driver.findElement(By.name("lineUp.interviewTime"));
		

	
	
	
	
	
	}	
	
}
