package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;

@Listeners(listenerImplementation.class)
public class FC_RejectedCandidateTestNG extends baseClass{


	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void updateRejectedCandidate() throws IOException, InterruptedException {
		
		JavascriptExecutor j=(JavascriptExecutor) driver;
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");	
		

		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		Thread.sleep(2000);
		
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		
		//click on find candidate
		RecruiterhomePage hp = new RecruiterhomePage(driver);
		hp.FinCan(driver);
		System.out.println("TEST");
		
		//click on hold candidate
		FindCandidate fc=new FindCandidate(driver);
		fc.rejectedCandidate(driver);
		
		//count the number of candidate
		List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
		//wait for the visibility of candidate
		//w.until(ExpectedConditions.visibilityOfAllElements(initalrows));
		int inititalRowsCount=initalrows.size();
	    System.out.println("recruiter inital row count : "+inititalRowsCount);
				
		Thread.sleep(1000);
		
		//if candidate with status REJECTED found 
		if(inititalRowsCount!=0) {
			
			//click on filter
			driver.findElement(By.className("rejectedcan-Filter-btn")).click();
			Thread.sleep(1000);
			WebElement status = driver.findElement(By.xpath("//button[text()=\"Final Status\"]"));
			status.click();
			Thread.sleep(500);
			//click on hold status
			WebElement currentType = driver.findElement(By.xpath("//label[contains(text(),\"rejected\")]"));
			currentType.click();
					 
			WebElement action = driver.findElement(By.xpath("(//i[@class=\"fa-regular fa-pen-to-square\"])[1]"));
			action.click();
					
					
					//column name
					eu.writeDataInExcel("Hold Candidate", 1, 0, "Candidate Name");
					eu.writeDataInExcel("Hold Candidate", 1, 1, "Candidate Email");
					eu.writeDataInExcel("Hold Candidate", 1, 2, "contact Number");
					eu.writeDataInExcel("Hold Candidate", 1, 3, "alternate Number");
					eu.writeDataInExcel("Hold Candidate", 1, 4, "source Name");
					eu.writeDataInExcel("Hold Candidate", 1, 5, "job Id");
					eu.writeDataInExcel("Hold Candidate", 1, 6, "job Designation");
					eu.writeDataInExcel("Hold Candidate", 1, 7, "requirement Company");
					eu.writeDataInExcel("Hold Candidate", 1, 8, "current Location");
					eu.writeDataInExcel("Hold Candidate", 1, 9, "full Address");
					eu.writeDataInExcel("Hold Candidate", 1, 10, "calling Feedback");
					eu.writeDataInExcel("Hold Candidate", 1, 11, "dob");
					eu.writeDataInExcel("Hold Candidate", 1, 12, "gender");
					eu.writeDataInExcel("Hold Candidate", 1, 13, "feedBack");
					eu.writeDataInExcel("Hold Candidate", 1, 14, "education");
					eu.writeDataInExcel("Hold Candidate", 1, 15, "passout");
					eu.writeDataInExcel("Hold Candidate", 1, 16, "certification");
					eu.writeDataInExcel("Hold Candidate", 1, 17, "Current Company");
					eu.writeDataInExcel("Hold Candidate", 1, 18, "Total Experience");
					eu.writeDataInExcel("Hold Candidate", 1, 19, "Relevent Experience");
					eu.writeDataInExcel("Hold Candidate", 1, 20, "Notice Period");
					eu.writeDataInExcel("Hold Candidate", 1, 21, "Communication Rating");
					eu.writeDataInExcel("Hold Candidate", 1, 22, "Current CTC");
					eu.writeDataInExcel("Hold Candidate", 1, 23, "Expected CTC");
					eu.writeDataInExcel("Hold Candidate", 1, 24, "offer Letter");
					eu.writeDataInExcel("Hold Candidate", 1, 25, "OfferLetter Msg");
					eu.writeDataInExcel("Hold Candidate", 1, 26, "messageFor TL");
					eu.writeDataInExcel("Hold Candidate", 1, 27, "statusType");
					eu.writeDataInExcel("Hold Candidate", 1, 28, "finalStatus");
					eu.writeDataInExcel("Hold Candidate", 1, 29, "Interviewdate");
					eu.writeDataInExcel("Hold Candidate", 1, 30, "InterviewTime");
					
	
					//updated
					String CandidateName=driver.findElement(By.name("candidateName")).getAttribute("value");
					String CandidateEmail=driver.findElement(By.name("candidateEmail")).getAttribute("value");
					String contactNumber=driver.findElement(By.name("contactNumber")).getAttribute("value");
					String alternateNumber=driver.findElement(By.name("alternateNumber")).getAttribute("value");
					String sourceName=driver.findElement(By.name("sourceName")).getAttribute("value");
				
					WebElement job=driver.findElement(By.name("requirementId"));
					String jobId=job.getAttribute("value");
					System.out.println(jobId);
					
					String jobDesignation=driver.findElement(By.name("jobDesignation")).getAttribute("value");
					String requirementCompany=driver.findElement(By.name("requirementCompany")).getAttribute("value");
					String currentLocation= driver.findElement(By.name("currentLocation")).getAttribute("value");
					String fullAddress=driver.findElement(By.name("fullAddress")).getAttribute("value");
					String callingFeedback=driver.findElement(By.name("callingFeedback")).getAttribute("value");
					String dob=driver.findElement(By.name("lineUp.dateOfBirth")).getAttribute("value");
					
					WebElement gender = driver.findElement(By.name("lineUp.gender"));
					if(gender.isSelected()) {
						String g=gender.getAttribute("value");
						System.out.println(eu.writeDataInExcel("Hold Candidate", 2, 12,g));
					}else {
						eu.writeDataInExcel("Hold Candidate", 2, 12,"");
					}
					
					String feedBack=driver.findElement(By.name("lineUp.feedBack")).getAttribute("value");
					String education=driver.findElement(By.name("qualification")).getAttribute("value");
					String passout=driver.findElement(By.name("lineUp.yearOfPassing")).getAttribute("value");
					String certification=driver.findElement(By.name("lineUp.extraCertification")).getAttribute("value");
					String Currentcompany=driver.findElement(By.name("lineUp.companyName")).getAttribute("value");
					
					String TotalExpYear=driver.findElement(By.name("lineUp.experienceYear")).getAttribute("value");
					String TotalExpMonth=driver.findElement(By.name("lineUp.experienceMonth")).getAttribute("value");	
					
					String releventExp=driver.findElement(By.name("lineUp.relevantExperience")).getAttribute("value");
					String noticePeriod=driver.findElement(By.name("lineUp.noticePeriod")).getAttribute("value");
					String commRating=driver.findElement(By.name("communicationRating")).getAttribute("value");
					
					String CurrentCTCYear=driver.findElement(By.name("lineUp.currentCTCLakh")).getAttribute("value");
					String CurrentCTCMonth=driver.findElement(By.name("lineUp.currentCTCThousand")).getAttribute("value");
					
					
					String expectedCTCYear=driver.findElement(By.name("lineUp.expectedCTCLakh")).getAttribute("value");
					String expectedCTCMonth=driver.findElement(By.name("lineUp.expectedCTCThousand")).getAttribute("value");
					
					String offerLetter=driver.findElement(By.name("lineUp.holdingAnyOffer")).getAttribute("value");
					String OfferLetterMsg=driver.findElement(By.name("lineUp.offerLetterMsg")).getAttribute("value");
					String messageForTL=driver.findElement(By.name("lineUp.msgForTeamLeader")).getAttribute("value");
					String statusType=driver.findElement(By.name("selectYesOrNo")).getAttribute("value");
					String finalStatus=driver.findElement(By.name("lineUp.finalStatus")).getAttribute("value");
					String Interviewdate=driver.findElement(By.name("lineUp.availabilityForInterview")).getAttribute("value");
					String InterviewTime=driver.findElement(By.cssSelector("div[class=\"ant-picker-input\"]")).getAttribute("value");
					
					
					
					//value extracted from candidate form
					eu.writeDataInExcel("Hold Candidate", 2, 0,CandidateName);
					eu.writeDataInExcel("Hold Candidate", 2, 1,CandidateEmail);
					eu.writeDataInExcel("Hold Candidate", 2, 2,contactNumber);
					eu.writeDataInExcel("Hold Candidate", 2, 3,alternateNumber);
					eu.writeDataInExcel("Hold Candidate", 2, 4,sourceName);
					eu.writeDataInExcel("Hold Candidate", 2, 5,jobId);
					eu.writeDataInExcel("Hold Candidate", 2, 6,jobDesignation);
					eu.writeDataInExcel("Hold Candidate", 2, 7,requirementCompany);
					eu.writeDataInExcel("Hold Candidate", 2, 8,currentLocation);
					eu.writeDataInExcel("Hold Candidate", 2, 9,fullAddress);
					eu.writeDataInExcel("Hold Candidate", 2, 10,callingFeedback);
					eu.writeDataInExcel("Hold Candidate", 2, 11,dob);
					eu.writeDataInExcel("Hold Candidate", 2, 13,feedBack);
					eu.writeDataInExcel("Hold Candidate", 2, 14,education);
					eu.writeDataInExcel("Hold Candidate", 2, 15,passout);
					eu.writeDataInExcel("Hold Candidate", 2, 16,certification);
					eu.writeDataInExcel("Hold Candidate", 2, 17,Currentcompany);
					eu.writeDataInExcel("Hold Candidate", 2, 18, TotalExpYear+" year"+","+TotalExpMonth+" month");
					eu.writeDataInExcel("Hold Candidate", 2, 19,releventExp);
					eu.writeDataInExcel("Hold Candidate", 2, 20,noticePeriod);
					eu.writeDataInExcel("Hold Candidate", 2, 21,commRating);
					eu.writeDataInExcel("Hold Candidate",2, 22, CurrentCTCYear+" Lakh"+","+CurrentCTCMonth+" Thousand");
					eu.writeDataInExcel("Hold Candidate",2, 23, expectedCTCYear+" Lakh"+","+expectedCTCMonth+" Thousand");
					eu.writeDataInExcel("Hold Candidate", 2, 24,offerLetter);
					eu.writeDataInExcel("Hold Candidate", 2, 25,OfferLetterMsg);
					eu.writeDataInExcel("Hold Candidate", 2, 26,messageForTL);
					eu.writeDataInExcel("Hold Candidate", 2, 27,statusType);
					eu.writeDataInExcel("Hold Candidate", 2, 28,finalStatus);
					eu.writeDataInExcel("Hold Candidate", 2, 29,Interviewdate);
					eu.writeDataInExcel("Hold Candidate", 2, 30,InterviewTime);
					
					
					WebElement cancelBtn = driver.findElement(By.id("uploadbtn2"));
					j.executeScript("arguments[0].scrollIntoView();", cancelBtn);
					Thread.sleep(500);
					
					//change status to interview schedule
					WebElement UpdatefinalStatus = driver.findElement(By.name("lineUp.finalStatus"));
					wdu.handleDropdown(UpdatefinalStatus, "Interview Schedule");
					
					//clear date
					Thread.sleep(500);
					WebElement updatedDate = driver.findElement(By.name("lineUp.availabilityForInterview"));
					updatedDate.clear();
					
					//click on update 
					Thread.sleep(500);
					WebElement update = driver.findElement(By.xpath("//button[text()=\"Update Data\"]"));
					update.click();
					
					
			}
			else 
			{
					System.out.println("No candidate are rejected");
					
					Thread.sleep(1000);
					//click on short listed
					hp.home(driver);
					
//					//search for candidate
//					Thread.sleep(1000);
//					driver.findElement(By.className("form-control")).sendKeys(CandidateName);
//					
					//click on calendar
					WebElement calendar = driver.findElement(By.cssSelector(".fa-regular.fa-calendar"));
					w.until(ExpectedConditions.visibilityOf(calendar));
					calendar.click();
					
					//select date from calendar
					WebElement date = driver.findElement(By.xpath("(//div[@class=\"highlighted-date\"])[1]"));
					w.until(ExpectedConditions.visibilityOf(date));
					date.click();
					
					//list of candidate interview scheduled
					List<WebElement> can = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
					w.until(ExpectedConditions.visibilityOfAllElements(can));
					System.out.println(can);
					
					//click on edit
					WebElement edit = driver.findElement(By.cssSelector(".fa-regular.fa-pen-to-square"));
					w.until(ExpectedConditions.visibilityOf(edit));
					edit.click();
					
					//select interview rounds
					Thread.sleep(1000);
					WebElement interviewRound = driver.findElement(By.xpath("//table[@class=\"table table-bordered\"]/tbody/tr/td[2]/select"));
					wdu.handleDropdown(interviewRound, " L1 Round");
					
					//select interview response
					Thread.sleep(1000);
					WebElement interviewResponse = driver.findElement(By.xpath("//table[@class=\"table table-bordered\"]/tbody/tr/td[3]/select"));
					wdu.handleDropdown(interviewResponse, "Rejected");
					
					//click on update
					WebElement update = driver.findElement(By.xpath("//button[text()=\"Update\"]"));
					update.click();
			}
						
			Thread.sleep(1000);
			
			//click on find candidate
			hp.FinCan(driver);
			
			//click on rejected candidate
			fc.rejectedCandidate(driver);
			
			//count the number of candidate
			List<WebElement> finalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
			int finalRowCount=finalrows.size();
			System.out.println("recruiter final row count : "+finalRowCount);
			
			int result=finalRowCount-inititalRowsCount;
			System.out.println("updated candidate :"+result);
			
	}

}
