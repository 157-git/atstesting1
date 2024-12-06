package recruiter;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		String URL="http://93.127.199.85/Dashboard/12/Recruiters";

		
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);

		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		
		//6-12-24 updated
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("unable to login");
			
		} else if(RecPageUrl.equals(URL)){
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			hp.home(driver);
			System.out.println("TEST");
			

			//count the number of candidate
			List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
			//wait for the visibility of candidate
			//w.until(ExpectedConditions.visibilityOfAllElements(initalrows));
			int inititalRowsCount=initalrows.size();
		    System.out.println("recruiter inital row count : "+inititalRowsCount);
					
			Thread.sleep(1000);
			
			if (inititalRowsCount!=0) {
				//click on action button
				ShortListed sl = new ShortListed(driver);
				sl.Action(driver);
				
				//.........FETCH ALL THE FIELDS DATA OF CANDIDATE...............
				
				String candidateName = driver.findElement(By.name("candidateName")).getAttribute("value");
				System.out.println(eu.writeDataInExcel("shortlisted",1, 0, candidateName));;
				
				String candidateEmail=driver.findElement(By.name("candidateEmail")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 1, candidateEmail);
				
				String contactNumber=driver.findElement(By.name("contactNumber")).getAttribute("value");
				System.out.println(eu.writeDataInExcel("shortlisted",1, 2, contactNumber));;
				
				String whatsupNumber = driver.findElement(By.name("alternateNumber")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 3, whatsupNumber);
				
				String source = driver.findElement(By.name("sourceName")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 4, source);
				
				String jobId = driver.findElement(By.name("requirementId")).getAttribute("value");
				System.out.println(eu.writeDataInExcel("shortlisted",1, 5, jobId));;
				
				String company=driver.findElement(By.id("requirementCompany")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 6, company);
				
				String callingFeedback = driver.findElement(By.name("callingFeedback")).getAttribute("value");
				System.out.println(eu.writeDataInExcel("shortlisted",1, 7, callingFeedback));;
				
				String dob = driver.findElement(By.name("lineUp.dateOfBirth")).getAttribute("value");
				System.out.println(eu.writeDataInExcel("shortlisted",1, 8, dob));
				
				WebElement gender = driver.findElement(By.name("lineUp.gender"));
				if(gender.isSelected()) {
					String g=gender.getAttribute("value");
					System.out.println(eu.writeDataInExcel("shortlisted",1, 9, g));
				}else {
					eu.writeDataInExcel("shortlisted",1, 9, "");
				}
				
				
				String callSummary=driver.findElement(By.name("lineUp.feedBack")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 10, callSummary);
				
				String education=driver.findElement(By.name("qualification")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 11, education);
				
				String passout=driver.findElement(By.name("lineUp.yearOfPassing")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 12, passout);
				
				String certification=driver.findElement(By.name("lineUp.extraCertification")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 13, certification);
				
				String Currentcompany=driver.findElement(By.name("lineUp.companyName")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 14, Currentcompany);
				
				String TotalExpYear=driver.findElement(By.name("lineUp.experienceYear")).getAttribute("value");
				String TotalExpMonth=driver.findElement(By.name("lineUp.experienceMonth")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 15, TotalExpYear+" year"+","+TotalExpMonth+" month");
				//System.out.println(eu.writeDataInExcel(1, 15, TotalExpMonth));;
				
				String releventExp=driver.findElement(By.name("lineUp.relevantExperience")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 16, releventExp);
				
				String noticePeriod=driver.findElement(By.name("lineUp.noticePeriod")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 17, noticePeriod);
				
				String commRating=driver.findElement(By.name("communicationRating")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 18, commRating);
				
				String CurrentCTCYear=driver.findElement(By.name("lineUp.currentCTCLakh")).getAttribute("value");
				String CurrentCTCMonth=driver.findElement(By.name("lineUp.currentCTCThousand")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 19, CurrentCTCYear+" Lakh"+","+CurrentCTCMonth+" Thousand");
				
				String expectedCTCYear=driver.findElement(By.name("lineUp.expectedCTCLakh")).getAttribute("value");
				String expectedCTCMonth=driver.findElement(By.name("lineUp.expectedCTCThousand")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 20, expectedCTCYear+" Lakh"+","+expectedCTCMonth+" Thousand");
				
				String offerLetter=driver.findElement(By.name("lineUp.holdingAnyOffer")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 21, offerLetter);
				
				String OfferLetterMsg=driver.findElement(By.name("lineUp.offerLetterMsg")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 22, OfferLetterMsg);
				
				String messageForTL=driver.findElement(By.name("lineUp.msgForTeamLeader")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 23, messageForTL);
				
				String statusType=driver.findElement(By.name("selectYesOrNo")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 24, statusType);
				
				String finalStatus=driver.findElement(By.name("lineUp.finalStatus")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 25, finalStatus);
				
				String Interviewdate=driver.findElement(By.name("lineUp.availabilityForInterview")).getAttribute("value");
				eu.writeDataInExcel("shortlisted",1, 26, Interviewdate);
				
				String InterviewTime=driver.findElement(By.cssSelector("div[class=\"ant-picker-input\"]")).getAttribute("value");    //updated
				eu.writeDataInExcel("shortlisted",1, 27, InterviewTime);
				
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
				
				WebElement d_o_b = driver.findElement(By.name("lineUp.dateOfBirth"));
				d_o_b.click();
				d_o_b.sendKeys(DOB);
				System.out.println("//...........3............");
				
				Thread.sleep(1000);
				if (GENDER.equals("Male")) {
					WebElement gen2 = driver.findElement(By.xpath("(//input[@name=\"lineUp.gender\"])[1]"));
					gen2.click();
				} else {
					WebElement gen1 = driver.findElement(By.xpath("(//input[@name=\"lineUp.gender\"])[2]"));
					gen1.click();
				}
				
				System.out.println("//..........4........");
				
				WebElement call_Summary=driver.findElement(By.name("lineUp.feedBack"));
				call_Summary.clear();
				call_Summary.sendKeys(CALLSUMMARY);
				
				WebElement Education=driver.findElement(By.name("qualification"));
				Education.clear();
				//Education.click();
				Thread.sleep(1000);
				//wdu.handleDropdown(Education, EDUCATION);     //updated
				Education.sendKeys(EDUCATION);
				
				
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
				
				String TY=TOTALEXPYEAR.split(",")[0];
				TotalExp_Year.sendKeys(TY);
				
				TotalExp_Month.clear();
				String TM="";
				if (TOTALEXPMONTH.contains(",")) {
					TM=TOTALEXPMONTH.substring(TOTALEXPMONTH.indexOf(",")+1).trim();
				}
				TotalExp_Month.sendKeys(TM);
				
				
				WebElement relevent_Exp=driver.findElement(By.name("lineUp.relevantExperience"));
				relevent_Exp.clear();
				relevent_Exp.sendKeys(RELEVENTEXP);
				
				WebElement notice_Period=driver.findElement(By.name("lineUp.noticePeriod"));
				notice_Period.clear();
				notice_Period.sendKeys(NOTICEPERIOD);
				
				
				WebElement comm_Rating=driver.findElement(By.name("communicationRating"));
				comm_Rating.clear();
				comm_Rating.sendKeys(COMMRATING);
				
				
				WebElement CurrentCTC_Year=driver.findElement(By.name("lineUp.currentCTCLakh"));
				WebElement CurrentCTC_Month=driver.findElement(By.name("lineUp.currentCTCThousand"));
				
				CurrentCTC_Year.clear();
				String SY=CURRENTCTCYEAR.split(",")[0];
				CurrentCTC_Year.sendKeys(SY);
				
				CurrentCTC_Month.clear();
				String SM="";
				if (CURRENTCTCMONTH.contains(",")) {
					SM=CURRENTCTCMONTH.substring(CURRENTCTCMONTH.indexOf(",")+1).trim();
				}
				CurrentCTC_Month.sendKeys(SM);
				
				
				WebElement expectedCTC_Year=driver.findElement(By.name("lineUp.expectedCTCLakh"));
				WebElement expectedCTC_Month=driver.findElement(By.name("lineUp.expectedCTCThousand"));
				
				expectedCTC_Year.clear();
				String EY=EXPECTEDCTCYEAR.split(",")[0];
				expectedCTC_Year.sendKeys(EY);
				
				expectedCTC_Month.clear();
				String EM="";
				if (EXPECTEDCTCMONTH.contains(",")) {
					EM=EXPECTEDCTCMONTH.substring(EXPECTEDCTCMONTH.indexOf(",")+1).trim();
				}
				expectedCTC_Month.sendKeys(EM);
				
				WebElement offer_Letter=driver.findElement(By.cssSelector("select[name=\"lineUp.holdingAnyOffer\"]"));
				offer_Letter.click();
				Thread.sleep(1000);
				wdu.handleDropdown(offer_Letter, OFFERLETTER);
				
				WebElement OfferLetter_Msg=driver.findElement(By.name("lineUp.offerLetterMsg"));
				OfferLetter_Msg.clear();
				OfferLetter_Msg.sendKeys(OFFERLETTERMSG);
				
				WebElement messageFor_TL=driver.findElement(By.name("lineUp.msgForTeamLeader"));
				messageFor_TL.clear();
				messageFor_TL.sendKeys(MESSAGEFORTL);
				
				WebElement status_Type=driver.findElement(By.name("selectYesOrNo"));
				status_Type.click();
				Thread.sleep(1000);
				wdu.handleDropdown(status_Type, STATUSTYPE);
				
				if (STATUSTYPE.equals("Intrested")) {
					WebElement final_Status=driver.findElement(By.name("lineUp.finalStatus"));
					final_Status.click();
				
					wdu.handleDropdown(final_Status, FINALSTATUS);
					
//					WebElement Interview_date=driver.findElement(By.name("lineUp.availabilityForInterview"));
//					Interview_date.clear();
//					Interview_date.sendKeys(INTERVIEWDATE);
//					
//					WebElement Interview_Time=driver.findElement(By.cssSelector("div[class=\"ant-picker-input\"]"));
//					Interview_Time.clear();
//					Interview_Time.sendKeys(INTERVIEWTIME);
				}
				

				
				
				List<WebElement> error = driver.findElements(By.className("error-message"));
				boolean anyDisplayed = false;
				for (WebElement msg : error) {
					if (msg.isDisplayed()) {
						anyDisplayed=true;
						System.out.println("error displayed");
						break;
					}
				}
				JavascriptExecutor js = (JavascriptExecutor) driver;
				
				if (anyDisplayed) {
					WebElement help = driver.findElement(By.className("update-tracker-popup-open-btn"));
					js.executeScript("arguments[0].scrollIntoView();",help );
					Thread.sleep(1000);
					wdu.ScreenShot(driver, "SC_updated");
					Assert.assertFalse(anyDisplayed, "All Required Field Value with Valid Data are required");
				} else {
					WebElement update_data = driver.findElement(By.xpath("//button[text()=\"Update Data\"]"));
					update_data.click();
					Thread.sleep(1300);
					wdu.ScreenShot(driver, "SC_updated");
				}
				
			} else {

				System.out.println("candidate not found");
			}
			
		}
	
	}	
	
}
